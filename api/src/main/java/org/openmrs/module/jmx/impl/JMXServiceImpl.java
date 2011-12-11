/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.jmx.impl;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.jmx.Constants;
import org.openmrs.module.jmx.JMXService;
import org.openmrs.module.jmx.util.ContextProvider;
import org.openmrs.module.jmx.util.Utils;

/**
 * Implementation of JMX service
 */
public class JMXServiceImpl extends BaseOpenmrsService implements JMXService {

	protected Log log = LogFactory.getLog(JMXServiceImpl.class);
	
	/**
	 * @see org.openmrs.module.jmx.JMXService#registerBean(String, Object)
	 */
	@Override
	public void registerBean(String folder, String name, Object bean) {
		try {
			ObjectName objName = getObjectName(folder, name);
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			if (beanServer.isRegistered(objName))
				beanServer.unregisterMBean(objName);
			
			beanServer.registerMBean(bean, objName);
			
			log.info("Registered MBean: " + objName.toString());
			
		} catch (MalformedObjectNameException e) {
			log.error("Invalid MBean name", e);
		} catch (Exception e) {
			log.error("Unable to register MBean", e);
		}
	}
	
	/**
	 * @see org.openmrs.module.jmx.JMXService#unregisterBean(String)
	 */
	@Override
	public void unregisterBean(String folder, String name) {
		try {
			ObjectName objName = getObjectName(folder, name);
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			if (beanServer.isRegistered(objName))
				beanServer.unregisterMBean(objName);
			
			log.info("Unregistered MBean: " + objName.toString());
			
		} catch (MalformedObjectNameException e) {
			log.error("Invalid MBean name", e);
		} catch (Exception e) {
			log.error("Unable to unregister MBean", e);
		}
	}
	
	/**
	 * Generates a qualified object name for a management bean
	 * @param name the name
	 * @param path the path
	 * @return the object name
	 * @throws MalformedObjectNameException
	 */
	private ObjectName getObjectName(String name, String path) throws MalformedObjectNameException {
		// Server name is based on the context path, minus the preceding slash
		ServletContext ctx = ContextProvider.getServletContext();
		
		Map<String, String> components = new HashMap<String, String>();
		components.put("name", name);
		
		// Host is based on the context root to keep different OpenMRS instances separate
		if (ctx != null) {
			String ctxPath = ctx.getContextPath();
			components.put("host", ctxPath.charAt(0) == '/' ? ctxPath.substring(1) : ctxPath);
		}
		
		// Append folder name to path
		if (path != null && path.length() > 0)
			components.put("path", path);
		
		// Build qualified name from domain and components		
		return new ObjectName(Constants.MBEAN_DOMAIN + ":" + Utils.nameValueList(components));
	}
}

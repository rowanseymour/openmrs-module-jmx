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

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.jmx.JMXService;
import org.openmrs.module.jmx.util.ContextProvider;
import org.openmrs.module.jmx.util.MBeanNamingStrategy;

/**
 * Implementation of JMX service
 */
public class JMXServiceImpl extends BaseOpenmrsService implements JMXService {

	protected Log log = LogFactory.getLog(JMXServiceImpl.class);
	
	/**
	 * @see org.openmrs.module.jmx.JMXService#registerBean(String, Object)
	 */
	@Override
	public void registerBean(String name, Object bean) {
		MBeanNamingStrategy naming = (MBeanNamingStrategy)ContextProvider.getApplicationContext().getBean("jmxBeanNamingStrategy");
		
		try {
			ObjectName objName = naming.getObjectName(bean, name);
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
	public void unregisterBean(String name) {
		MBeanNamingStrategy naming = (MBeanNamingStrategy)ContextProvider.getApplicationContext().getBean("jmxBeanNamingStrategy");
		
		try {
			ObjectName objName = naming.getObjectName(null, name);
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
}
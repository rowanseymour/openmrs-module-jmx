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

package org.openmrs.module.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

/**
 * Our module activator - registers the OpenMRS MBean
 */
public class JMXActivator extends BaseModuleActivator {
	
	protected Log log = LogFactory.getLog(JMXActivator.class);
	
	/**
	 * @see org.openmrs.module.BaseModuleActivator#started()
	 */
	@Override
	public void started() {
		log.info("Starting JMX Module");
		 
		try {
			// TODO Use implementation id to generate name
			ObjectName beanName = new ObjectName(Constants.MBEAN_NAME);
			OpenMRSServer serverBean = new OpenMRSServer(); 
			
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			beanServer.registerMBean(serverBean, beanName);
			
			log.info("Registered OpenMRS MBean");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see org.openmrs.module.BaseModuleActivator#stopped()
	 */
	@Override
	public void stopped() {
		log.info("Shutting down JMX Module");
		
		try {
			ObjectName beanName = new ObjectName(Constants.MBEAN_NAME);
				
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			beanServer.unregisterMBean(beanName);
				
			log.info("Unregistered OpenMRS MBean");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

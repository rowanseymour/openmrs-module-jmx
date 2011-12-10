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

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.jmx.impl.CoreMXBeanImpl;
import org.openmrs.module.jmx.impl.ModuleMXBeanImpl;

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
		
		// Register core bean
		JMXService svc = Context.getService(JMXService.class);
		CoreMXBean bean = new CoreMXBeanImpl();
		svc.registerBean(Constants.CORE_BEAN_NAME, null, bean);
		
		ModuleMXBean modBean1 = new ModuleMXBeanImpl();
		svc.registerBean("Modules", "usagestatistics", modBean1);
		
		ModuleMXBean modBean2 = new ModuleMXBeanImpl();
		svc.registerBean("Modules", "logic", modBean2);
		
		log.info("Registered management beans");
	}

	/**
	 * @see org.openmrs.module.BaseModuleActivator#stopped()
	 */
	@Override
	public void stopped() {
		log.info("Shutting down JMX Module");
		
		// Unregister core bean
		JMXService svc = Context.getService(JMXService.class);
		svc.unregisterBean(Constants.CORE_BEAN_NAME, null);
		
		svc.unregisterBean("Modules", "usagestatistics");
		svc.unregisterBean("Modules", "logic");	
				
		log.info("Unregistered management beans");
	}
}

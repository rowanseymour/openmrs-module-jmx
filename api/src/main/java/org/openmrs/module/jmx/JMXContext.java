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

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.jmx.impl.CoreMXBeanImpl;
import org.openmrs.module.jmx.impl.ModuleMXBeanImpl;

public class JMXContext {
	
	protected static List<String> moduleBeanIds = new ArrayList<String>();
	
	/**
	 * Creates and registers the management beans defined by this module
	 */
	public static void registerBeans() {
		// Register core bean
		JMXService svc = Context.getService(JMXService.class);
		CoreMXBean bean = new CoreMXBeanImpl();
		svc.registerBean(Constants.CORE_BEAN_NAME, null, bean);
		
		// Register module beans
		for (Module module : ModuleFactory.getLoadedModules()) {
			ModuleMXBean modBean1 = new ModuleMXBeanImpl(module.getModuleId());
			svc.registerBean("Modules", module.getModuleId(), modBean1);
			moduleBeanIds.add(module.getModuleId());
		}
	}
	
	/**
	 * Unregisters the management beans defined by this module
	 */
	public static void unregisterBeans() {
		// Unregister core bean
		JMXService svc = Context.getService(JMXService.class);
		svc.unregisterBean(Constants.CORE_BEAN_NAME, null);
		
		// Unregister module beans
		for (String moduleId : moduleBeanIds) {
			svc.unregisterBean("Modules", moduleId);
		}
		moduleBeanIds.clear();
	}
}

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.jmx.mbean.CoreMBean;
import org.openmrs.module.jmx.mbean.ModuleMBean;
import org.openmrs.module.jmx.mbean.TaskMBean;
import org.openmrs.module.jmx.mbean.impl.ModuleMBeanImpl;
import org.openmrs.module.jmx.mbean.impl.TaskMBeanImpl;
import org.openmrs.module.jmx.util.ContextProvider;
import org.openmrs.scheduler.TaskDefinition;

public class JMXContext {
	
	protected Log log = LogFactory.getLog(JMXContext.class);
	
	/**
	 * Keep lists of the bean names that we register so we can unregister them
	 */
	protected static List<String> moduleBeanNames = new ArrayList<String>();
	protected static List<String> taskBeanNames = new ArrayList<String>();
	
	/**
	 * Creates and registers the management beans defined by this module
	 */
	public static void registerBeans() {
		// TODO Figure out why module is always started twice requiring this
		moduleBeanNames.clear();
		taskBeanNames.clear();
		
		// Register core bean
		JMXService svc = Context.getService(JMXService.class);
		CoreMBean bean = (CoreMBean)ContextProvider.getApplicationContext().getBean("jmxCoreMBean");
		svc.registerBean(Constants.MBEAN_NAME_CORE, null, bean);
		
		// Register module beans
		for (Module module : ModuleFactory.getLoadedModules()) {
			ModuleMBean modBean = new ModuleMBeanImpl(module.getModuleId());
			svc.registerBean(Constants.MBEAN_NAME_MODULES, module.getModuleId(), modBean);
			moduleBeanNames.add(module.getModuleId());
		}
		
		// Register scheduled task beans
		for (TaskDefinition taskDef : Context.getSchedulerService().getRegisteredTasks()) {
			TaskMBean taskBean = new TaskMBeanImpl(taskDef.getId());
			String taskName = taskDef.getName().replace(" ", "");
			svc.registerBean(Constants.MBEAN_NAME_TASKS, taskName, taskBean);
			taskBeanNames.add(taskName);
		}
	}
	
	/**
	 * Unregisters the management beans defined by this module
	 */
	public static void unregisterBeans() {
		// Unregister core bean
		JMXService svc = Context.getService(JMXService.class);
		svc.unregisterBean(Constants.MBEAN_NAME_CORE, null);
		
		// Unregister module beans
		for (String beanName : moduleBeanNames) 
			svc.unregisterBean(Constants.MBEAN_NAME_MODULES, beanName);
		
		// Unregister module beans
		for (String beanName : taskBeanNames) 
			svc.unregisterBean(Constants.MBEAN_NAME_TASKS, beanName);
		
		moduleBeanNames.clear();
		taskBeanNames.clear();
	}
}

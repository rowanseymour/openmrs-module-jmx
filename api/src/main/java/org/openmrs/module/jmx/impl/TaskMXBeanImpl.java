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

import org.openmrs.api.context.Context;
import org.openmrs.module.jmx.TaskMXBean;
import org.openmrs.util.PrivilegeConstants;

/**
 * Management bean implementation for a scheduled task
 */
public class TaskMXBeanImpl implements TaskMXBean {

	protected int taskId;
	
	/**
	 * Constructs a scheduled task management bean
	 * @param taskId the task id
	 */
	public TaskMXBeanImpl(int taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * @see org.openmrs.module.jmx.TaskMXBean#getName()
	 */
	@Override
	public String getName() {
		Context.openSession();
		Context.addProxyPrivilege(PrivilegeConstants.MANAGE_SCHEDULER);
		String result = Context.getSchedulerService().getTask(taskId).getName();
		Context.closeSession();
		return result;
	}

	/**
	 * @see org.openmrs.module.jmx.TaskMXBean#getTaskClass()
	 */
	@Override
	public String getTaskClass() {
		Context.openSession();
		Context.addProxyPrivilege(PrivilegeConstants.MANAGE_SCHEDULER);
		String result = Context.getSchedulerService().getTask(taskId).getTaskClass();
		Context.closeSession();
		return result;
	}
	
	/**
	 * @see org.openmrs.module.jmx.TaskMXBean#isStarted()
	 */
	@Override
	public boolean isStarted() {
		Context.openSession();
		Context.addProxyPrivilege(PrivilegeConstants.MANAGE_SCHEDULER);
		boolean result = Context.getSchedulerService().getTask(taskId).getStarted();
		Context.closeSession();
		return result;
	}
}

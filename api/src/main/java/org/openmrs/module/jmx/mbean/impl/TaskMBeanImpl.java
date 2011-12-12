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

package org.openmrs.module.jmx.mbean.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.jmx.mbean.TaskMBean;

/**
 * Management bean implementation for a scheduled task
 */
public class TaskMBeanImpl implements TaskMBean {

	protected Log log = LogFactory.getLog(TaskMBeanImpl.class);
	protected int taskId;
	
	/**
	 * Constructs a scheduled task management bean
	 * @param taskId the task id
	 */
	public TaskMBeanImpl(int taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.TaskMBean#getName()
	 */
	@Override
	public String getName() {
		return Context.getSchedulerService().getTask(taskId).getName();
	}

	/**
	 * @see org.openmrs.module.jmx.mbean.TaskMBean#getTaskClass()
	 */
	@Override
	public String getTaskClass() {
		return Context.getSchedulerService().getTask(taskId).getTaskClass();
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.TaskMBean#isStarted()
	 */
	@Override
	public boolean isStarted() {
		return Context.getSchedulerService().getTask(taskId).getStarted();
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.TaskMBean#getNextExecutionTime()
	 */
	@Override
	public Date getLastExecutionTime() {
		return Context.getSchedulerService().getTask(taskId).getLastExecutionTime();
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.TaskMBean#getNextExecutionTime()
	 */
	@Override
	public Date getNextExecutionTime() {
		return Context.getSchedulerService().getTask(taskId).getNextExecutionTime();
	}
}

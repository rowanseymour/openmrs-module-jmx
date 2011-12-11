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

/**
 * Management bean interface for a scheduled task
 */
public interface TaskMXBean {
	
	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Gets the class name
	 * @return the class name
	 */
	public String getTaskClass();
	
	/**
	 * Is the task started
	 * @return true if started
	 */
	public boolean isStarted();
}

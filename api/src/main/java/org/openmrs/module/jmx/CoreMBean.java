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

import javax.management.MXBean;

/**
 * OpenMRS core JMX bean interface
 */
@MXBean
public interface CoreMBean {
	
	/**
	 * Gets the version name of OpenMRS
	 * @return the version
	 */
	public String getVersion();
	
	/**
	 * Gets name of OpenMRS database
	 * @return the database name
	 */
	public String getDatabaseName();
	
	/**
	 * Gets the mail server in the format [host]:[port]
	 * @return the mail server
	 */
	public String getMailServer();
	
	/**
	 * Gets an array of started modules (ids and versions)
	 * @return the array
	 */
	public String[] getStartedModules();
	
	/**
	 * Gets an array of stopped modules (ids and versions)
	 * @return the array
	 */
	public String[] getStoppedModules();
}

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
 * Management bean interface for an OpenMRS module
 */
@MXBean
public interface ModuleMXBean {
	
	/**
	 * Gets the version of the module
	 * @return the version name
	 */
	public String getVersion();
	
	/**
	 * Gets if the module is started
	 * @return if module is started
	 */
	public boolean isStarted();
}
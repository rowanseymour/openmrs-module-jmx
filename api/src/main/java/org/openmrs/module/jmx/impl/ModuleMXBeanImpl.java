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

import org.openmrs.module.ModuleFactory;
import org.openmrs.module.jmx.ModuleMXBean;

/**
 * Management bean implementation for an OpenMRS module
 */
public class ModuleMXBeanImpl implements ModuleMXBean {
	
	protected String moduleId;
	
	/**
	 * Constructs a module management bean
	 * @param moduleId the module id
	 */
	public ModuleMXBeanImpl(String moduleId) {
		this.moduleId = moduleId;
	}
	
	/**
	 * @see org.openmrs.module.jmx.ModuleMXBean#getName()
	 */
	@Override
	public String getName() {
		return ModuleFactory.getModuleById(moduleId).getName();
	}

	/**
	 * @see org.openmrs.module.jmx.ModuleMXBean#getVersion()
	 */
	@Override
	public String getVersion() {
		return ModuleFactory.getModuleById(moduleId).getVersion();
	}

	/**
	 * @see org.openmrs.module.jmx.ModuleMXBean#isStarted()
	 */
	@Override
	public boolean isStarted() {
		return ModuleFactory.getModuleById(moduleId).isStarted();
	}
}

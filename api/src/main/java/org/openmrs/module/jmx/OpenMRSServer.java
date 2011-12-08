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
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;
import org.openmrs.util.PrivilegeConstants;

/**
 * JMX management bean implementation
 */
public class OpenMRSServer implements OpenMRSServerMBean {

	protected Log log = LogFactory.getLog(OpenMRSServer.class);
	
	@Override
	public String getVersion() {
		Context.openSession();
		Context.addProxyPrivilege(PrivilegeConstants.VIEW_ADMIN_FUNCTIONS);
		Map<String, String> sysVars = Context.getAdministrationService().getSystemVariables();
		Context.removeProxyPrivilege(PrivilegeConstants.VIEW_ADMIN_FUNCTIONS);
		Context.closeSession();
		return sysVars.get("OPENMRS_VERSION");
	}

	@Override
	public String[] getRunningModules() {
		List<String> modules = new ArrayList<String>();
		for (Module module : ModuleFactory.getStartedModules())
			modules.add(module.getModuleId() + " (" + module.getVersion() + ")");
		return modules.toArray(new String[] {});
	}
}

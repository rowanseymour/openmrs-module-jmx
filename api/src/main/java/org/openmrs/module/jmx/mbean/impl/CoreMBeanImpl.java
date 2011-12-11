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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.jmx.mbean.CoreMBean;
import org.openmrs.util.PrivilegeConstants;

/**
 * Management bean implementation for OpenMRS core
 */
public class CoreMBeanImpl implements CoreMBean {

	protected Log log = LogFactory.getLog(CoreMBeanImpl.class);
	
	/**
	 * @see org.openmrs.module.jmx.mbean.CoreMBean#getVersion()
	 */
	@Override
	public String getVersion() {
		return getSystemVariable("OPENMRS_VERSION");
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.CoreMBean#getDatabaseName()
	 */
	@Override
	public String getDatabaseName() {
		return getSystemVariable("DATABASE_NAME");
	}
	
	/**
	 * @see org.openmrs.module.jmx.mbean.CoreMBean#getDatabaseName()
	 */
	@Override
	public String getMailServer() {
		return getGlobalProperty("mail.smtp_host") + ":" + getGlobalProperty("mail.smtp_port");
	}

	/**
	 * Gets the value of a system variable
	 * @param key the variable key
	 * @return the variable value
	 */
	private String getSystemVariable(String key) {
		Context.openSession();
		Context.addProxyPrivilege(PrivilegeConstants.VIEW_ADMIN_FUNCTIONS);
		Map<String, String> sysVars = Context.getAdministrationService().getSystemVariables();
		Context.closeSession();
		return sysVars.get(key);
	}
	
	/**
	 * Gets the value of a global property
	 * @param key the property key
	 * @return the property value
	 */
	private String getGlobalProperty(String key) {
		Context.openSession();
		String value = Context.getAdministrationService().getGlobalProperty(key);
		Context.closeSession();
		return value;
	}
}

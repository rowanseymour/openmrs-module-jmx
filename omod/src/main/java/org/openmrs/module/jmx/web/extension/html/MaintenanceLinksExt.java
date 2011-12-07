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

package org.openmrs.module.jmx.web.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.jmx.Constants;

/**
 * Adds a link to the maintenance menu
 */
public class MaintenanceLinksExt extends Extension {

	public Map<String, String> getLinks() {
		Map<String, String> links = new HashMap<String, String>();
		links.put("module/" + Constants.MODULE_ID + "/manage.form", Constants.MODULE_ID + ".admin.link");
		return links;
	}

	@Override
	public MEDIA_TYPE getMediaType() {
		return MEDIA_TYPE.html;
	}	
	
	public String getRequiredPrivilege() {
		return Constants.PRIV_MANAGE_JMX;
	}
}

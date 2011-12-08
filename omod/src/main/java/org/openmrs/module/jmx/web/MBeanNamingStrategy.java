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

package org.openmrs.module.jmx.web;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.openmrs.module.jmx.web.util.ContextProvider;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;

/**
 * Used to dynamically name the server MBeans based on context path. This means
 * multiple OpenMRS intances can be monitored at the same time
 */
public class MBeanNamingStrategy implements ObjectNamingStrategy {

	/**
	 * @see org.springframework.jmx.export.naming.ObjectNamingStrategy#getObjectName(Object, String)
	 */
	@Override
	public ObjectName getObjectName(Object bean, String key) throws MalformedObjectNameException {
		if ("server".equals(key)) {
			// Server name is based on the context path, minus the preceding slash
			String ctxPath = ContextProvider.getServletContext().getContextPath();
			if (ctxPath.charAt(0) == '/')
				ctxPath = ctxPath.substring(1);
			
			return new ObjectName("OpenMRS:type=Servers,name=" + ctxPath);
		}
		return new ObjectName("OpenMRS:name=Unknown");
	}
}

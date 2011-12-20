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

package org.openmrs.module.jmx.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.module.jmx.JMXContext;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Interceptor on the administration service to catch modules being loaded/unloaded
 */
public class AdministrationServiceInterceptor implements AfterReturningAdvice {

	protected Log log = LogFactory.getLog(AdministrationServiceInterceptor.class);
	
	/**
	 * @see org.springframework.aop.AfterReturningAdvice#afterReturning(Object, Method, Object[], Object)
	 */
	@Override
	public void afterReturning(Object returnVal, Method method, Object[] args, Object target) throws Throwable {
		if (method.getName().equals("saveGlobalProperty")) {
			GlobalProperty property = (GlobalProperty)args[0];
			
			// Reload management beans as module may have been loaded/unloaded
			if (property.getProperty().endsWith(".started")) {
				JMXContext.refresh(false, false, true);
			
				log.info("Refreshed management beans due to module changes");
			}
		}
	}
}

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
import org.openmrs.api.context.Context;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * Advice class which creates a new OpenMRS session before a method is
 * called and gives the session the requested privileges
 */
public class OpenSessionAdvice implements MethodBeforeAdvice {

	protected Log log = LogFactory.getLog(OpenSessionAdvice.class);
	
	/**
	 * @see org.springframework.aop.MethodBeforeAdvice#before(Method, Object[], Object)
	 */
	@Override
	public void before(Method method, Object[] params, Object target) throws Throwable {
		ProxySession annotation = method.getAnnotation(ProxySession.class);
		
		if (annotation != null) {
			// Create new OpenMRS session
			Context.openSession();
			
			// Add requested privileges as proxy privileges
			for (String privilege : annotation.value()) 
				Context.addProxyPrivilege(privilege);
		}
	}
}

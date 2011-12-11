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

import org.openmrs.api.context.Context;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice class which closes the current OpenMRS session after a method
 * has been called
 */
public class CloseSessionAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object result, Method method, Object[] params, Object target) throws Throwable {
		ProxySession annotation = method.getAnnotation(ProxySession.class);
		
		if (annotation != null)
			Context.closeSession();
	}
}

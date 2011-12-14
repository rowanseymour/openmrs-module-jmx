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

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;

/**
 * Interceptor to wrap a method call in a new OpenMRS session with
 * the requested privileges
 */
public class WithSessionInterceptor implements MethodInterceptor {

	protected Log log = LogFactory.getLog(WithSessionInterceptor.class);
	
	/**
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		WithSession annotation = invocation.getMethod().getAnnotation(WithSession.class);
		
		if (annotation != null) {
			// Create new OpenMRS session
			Context.openSession();
			
			// Add requested privileges as proxy privileges
			for (String privilege : annotation.value()) 
				Context.addProxyPrivilege(privilege);
		}
		
		// Invoke method...
		Object result = invocation.proceed();
		
		// Close session
		if (annotation != null)
			Context.closeSession();

		return result;
	}
}

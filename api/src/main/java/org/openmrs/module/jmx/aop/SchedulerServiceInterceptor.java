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
import org.openmrs.module.jmx.JMXContext;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Interceptor on the scheduler service to catch new tasks being added and existing ones being deleted
 */
public class SchedulerServiceInterceptor implements AfterReturningAdvice {

	protected Log log = LogFactory.getLog(SchedulerServiceInterceptor.class);
	
	/**
	 * @see org.springframework.aop.AfterReturningAdvice#afterReturning(Object, Method, Object[], Object)
	 */
	@Override
	public void afterReturning(Object returnVal, Method method, Object[] args, Object target) throws Throwable {
		// Reload management beans as scheduled tasks have changed
		if (method.getName().equals("scheduleTask") || method.getName().equals("deleteTask")) {
			JMXContext.refresh(false, true, false);
			
			log.info("Refreshed management beans due to scheduled task changes");
		}
	}
}

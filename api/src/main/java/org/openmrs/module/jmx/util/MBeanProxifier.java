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

package org.openmrs.module.jmx.util;

import javax.management.MXBean;

import org.aopalliance.intercept.Interceptor;
import org.openmrs.module.jmx.aop.WithSessionInterceptor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Utility class to proxify MBeans so that they use the <code>WithSessionInterceptor</code>
 */
public class MBeanProxifier {
	protected static Interceptor interceptor = new WithSessionInterceptor();
	
	/**
	 * Gets a proxied MBean
	 * @param mbean the MBean
	 * @return the proxied MBean
	 */
	public static Object getProxy(Object mbean) {
		Class<?> clazz = getMBeanInterface(mbean);
		if (clazz == null)
			throw new IllegalArgumentException("Object does not implement an MXBean interface");
			
		ProxyFactory factory = new ProxyFactory(clazz, interceptor);
		factory.setTarget(mbean);
		return factory.getProxy();
	}
	
	/**
	 * Finds the MBean interface of the given object by searching for the MXBean annotation
	 * @param mbean the object
	 * @return the interface
	 */
	public static Class<?> getMBeanInterface(Object mbean) {
		Class<?>[] interfaces = mbean.getClass().getInterfaces();
		for (Class<?> intf : interfaces) {
			MXBean annotation = intf.getAnnotation(MXBean.class);
			if (annotation != null && annotation.value())
				return intf;
		}
		return null;
	}
}

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

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for ContextProvider class
 */
public class MBeanProxifierTest {
	
	protected Log log = LogFactory.getLog(MBeanProxifierTest.class);

	@Test
	public void getMBeanInterface() {
		ExampleMBean bean = new ExampleMBeanImpl();
		Assert.assertEquals(ExampleMBean.class, MBeanProxifier.getMBeanInterface(bean));
	}
	
	@Test
	public void getProxy() {
		ExampleMBean bean = new ExampleMBeanImpl();
		Object proxy = MBeanProxifier.getProxy(bean);
		
		Assert.assertNotNull(proxy);
		Assert.assertTrue(proxy instanceof ExampleMBean);
	}
}

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

package org.openmrs.module.jmx.impl;

import java.lang.management.ManagementFactory;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import junit.framework.Assert;

import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.jmx.JMXService;
import org.openmrs.module.jmx.util.ExampleMBean;
import org.openmrs.module.jmx.util.ExampleMBeanImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Test case for JMXContext class
 */
public class JMXServiceTest extends BaseModuleContextSensitiveTest {

	@Test
	public void registerMBean() throws Exception {
		ExampleMBean bean = new ExampleMBeanImpl();
		
		// Register bean
		JMXService svc = Context.getService(JMXService.class);
		svc.registerMBean("testname", "testpath", bean);
		
		// Check in bean server that it exists
		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanInfo beanInfo = beanServer.getMBeanInfo(new ObjectName("OpenMRS:name=testname,path=testpath"));
		MBeanAttributeInfo[] attributes = beanInfo.getAttributes();
		Assert.assertEquals(1, attributes.length);
		Assert.assertEquals("X", attributes[0].getName());
	}
	
	@Test
	public void unregisterMBean() throws Exception {	
		// Unregister bean
		JMXService svc = Context.getService(JMXService.class);
		svc.unregisterMBean("testname", "testpath");
		
		// Check in bean server that bean has been removed
		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
		Assert.assertFalse(beanServer.isRegistered(new ObjectName("OpenMRS:name=testname,path=testpath")));
	}
}

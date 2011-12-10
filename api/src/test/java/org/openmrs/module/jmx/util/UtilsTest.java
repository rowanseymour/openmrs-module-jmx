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

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test case for Utils class
 */
public class UtilsTest {

	@Test
	public void nameValueList() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("name", "Bob");
		Assert.assertEquals("name=Bob", Utils.nameValueList(map));
		map.put("age", "30");
		Assert.assertEquals("age=30,name=Bob", Utils.nameValueList(map));
		map.put("job", "IT");
		Assert.assertEquals("age=30,job=IT,name=Bob", Utils.nameValueList(map));
	}
}

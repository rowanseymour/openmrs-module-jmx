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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * General utility methods
 */
public class Utils {
	/**
	 * Generates a comma separated list of name value pairs from a map
	 * @param map the map of names and values
	 * @return the comma separated list
	 */
	public static String nameValueList(Map<String, String> map) {
		if (map.size() == 0)
			return "";
		
		List<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		StringBuilder sb = new StringBuilder();
		sb.append(entries.get(0).getKey() + "=" + entries.get(0).getValue());
		for (int e = 1; e < entries.size(); ++e) {
			sb.append(",");
			sb.append(entries.get(e).getKey() + "=" + entries.get(e).getValue());
		}
		
		return sb.toString();
	}
}

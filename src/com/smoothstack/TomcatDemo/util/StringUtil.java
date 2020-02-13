package com.smoothstack.TomcatDemo.util;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtil {

	
	public static Map<String, List<String>> getQuery(String queryString) {
	    if (queryString == null || "".equals(queryString)) {
	        return Collections.emptyMap();
	    }
	    return Arrays.stream(queryString.split("&"))
	            .map(StringUtil::splitQueryParameter)
	            .collect(Collectors.groupingBy(
	            		SimpleImmutableEntry::getKey, 
	            		LinkedHashMap::new, 
	            		Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
	}

	public static SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
	    final int idx = it.indexOf("=");
	    final String key = idx > 0 ? it.substring(0, idx) : it;
	    final String value = idx > 0 && it.length() > idx + 1 ? it.substring(idx + 1) : null;
	    return new SimpleImmutableEntry<>(key, value);
	}
}

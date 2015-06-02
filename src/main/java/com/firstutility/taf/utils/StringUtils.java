package com.firstutility.taf.utils;

public class StringUtils {
	
	public static boolean isNull(String str) {
		return str == null;
	}
	
	public static boolean isEmpty(String str) {
		return str.isEmpty();
	}
	
	public static boolean isNullOrEmpty(String str) {
		return isEmpty(str) || isNull(str);
	}

	public static boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}
}

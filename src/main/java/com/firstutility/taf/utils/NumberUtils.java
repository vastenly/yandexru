package com.firstutility.taf.utils;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	
	public static boolean isNull(Integer i) {
		return i == null;
	}
	
	public static boolean isNotNull(Integer i) {
		return !isNull(i);
	}

}

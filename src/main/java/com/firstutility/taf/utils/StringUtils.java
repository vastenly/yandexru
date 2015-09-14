package com.firstutility.taf.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static boolean isNull(String str) {
		return str == null;
	}
	
	public static boolean isNotNull(String str) {
		return !isNull(str);
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
	
	/**
	 * <p>Checks if a String is not empty, not null and not whitespace only.</p>
	 * @param str
	 * @return true/false flag
	 */
	public static boolean isNotBlank(String str) {
		return org.apache.commons.lang3.StringUtils.isNotBlank(str);
	}

	/**
	 * @param str
	 * @param regex
	 * @param replacement
	 * @return resulting <tt>String</tt>
	 */
	public static String replaceLast(String str, String regex, String replacement) {
		return str.replaceAll("["+regex+"$]", replacement);
	}
	
	/**
	 * @param integer
	 * @param strLength
	 * @param leadingPrefix
	 * @return resulting <tt>String</tt>
	 */
	public static String parseToString(int integer, int strLength, String leadingPrefix) {
		return String.format("%".concat(String.format("%s%s", leadingPrefix, strLength)).concat("d"), integer);
	}

}

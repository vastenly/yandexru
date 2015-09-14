package com.firstutility.taf.utils;

import java.util.Properties;

import org.apache.log4j.Logger;

public class SystemUtils extends org.apache.commons.lang3.SystemUtils {
	
	private static final Logger log = Logger.getLogger(SystemUtils.class);
	
	public static void addToSystemVariables(Object key, Object value) {
		log.info("[SystemUtils] Add [" +key+"="+value+ "] property into System Variables.");
		System.getProperties().put(key, value);
	}

	public static void addToSystemVariables(Properties properties) {
		log.info("[SystemUtils] Add properties into System Variables.");
		System.getProperties().putAll(properties);
	}
}

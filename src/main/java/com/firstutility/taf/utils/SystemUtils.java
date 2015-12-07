package com.firstutility.taf.utils;

import static com.firstutility.taf.utils.StringUtils.isNullOrEmpty;

import java.util.Properties;
import org.apache.log4j.Logger;
import com.firstutility.taf.utils.exceptions.UnknownPropertyException;

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
	
	public static String getProperty(String key) {
		if (isNullOrEmpty(key))
			throw new IllegalArgumentException("[SystemUtils] Defined property key is NULL or empty!");
		String value = java.lang.System.getProperty(key);
		if (value == null)
			throw new UnknownPropertyException("[SystemUtils] Unable to find property [" +key+ "] value in System Variables.");
		return value;
	}
}

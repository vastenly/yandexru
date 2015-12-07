package com.firstutility.taf.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectUtils {
	
	private static final Logger log = Logger.getLogger(ObjectUtils.class);
	
	public static JSONObject toJSONObject(Object obj, Map<String, String> map) {
		JSONObject jsonObject = new JSONObject();
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i=0; i<fields.length; i++) {
			try {
				if (fields[i].getType().isAssignableFrom(String.class)) {
					fields[i].setAccessible(true);
		
					log.debug(fields[i].getName());
					log.debug((String) fields[i].get(obj));

					jsonObject.put(fields[i].getName(), (String) fields[i].get(obj));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		for (Entry<String, String> entry : map.entrySet()) {
			try {
				jsonObject.put(entry.getKey(), entry.getValue());
				log.debug(entry.getKey() +":"+ entry.getValue());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		log.debug(jsonObject.toString());
		return jsonObject;
	}

}

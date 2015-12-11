package com.firstutility.taf;

import static com.firstutility.taf.utils.StringUtils.isNullOrEmpty;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.firstutility.taf.utils.file.props.PropertyFileReader;
import com.firstutility.taf.utils.file.props.PropertyFileWriter;

public class Resources {
	
	private static final Logger log = Logger.getLogger(Resources.class);
	
	private static Properties properties;

	public static String getProperty(PropertyFile propertyFile, String key) {
		log.debug("[Resources] Init [" +String.valueOf(propertyFile)+ "] properties.");
		if ((propertyFile).getFilePath() != null) {
			PropertyFileReader pfr = new PropertyFileReader();
			log.debug("[Resources] Load [" +propertyFile.getFilePath()+ "] property file.");
			properties = pfr.loadPropFile(propertyFile.getFilePath()).getProperties();
			log.info("[Resources] Get property value for [" +key+ "] property key.");
			return pfr.getValue(key);
		} else if ((propertyFile).getBundlePath() != null) {
			ResourceBundle rb = null;
			log.debug("[Resources] Load [" +propertyFile.getBundlePath()+ "] resource bundle.");
			rb = ResourceBundle.getBundle( propertyFile.getBundlePath() );
			log.info("[Resources] Get property value for [" +key+ "] property key.");
			return rb.getString(key);
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * @param filePath
	 * @param key
	 * @return value
	 */
	public static String getProperty(String filePath, String key) {
		if (isNullOrEmpty(filePath))
			throw new IllegalArgumentException("[Resources] Defined file path is NULL or empty!");
		if (isNullOrEmpty(key))
			throw new IllegalArgumentException("[Resources] Defined property key is NULL or empty!");
		PropertyFileReader pfr = new PropertyFileReader();
		properties = pfr.loadPropFile(filePath).getProperties();
		return properties.getProperty(key);
	}
	
//	public PropertyFileWriter setProperty(PropertyFile propertyFile, String key, String value) {
//		if (!(key).isEmpty() && !(value).isEmpty()) {
//			if (PropertyFileWriter.getProperties() == null) {
//				Properties properties = new Properties();
//				new PropertyFileWriter().setProperties(properties);
//				PropertyFileWriter.setValue(key, value);
//			} else {
//				Properties properties = PropertyFileWriter.getProperties();
//				new PropertyFileWriter().setProperties(properties);
//				PropertyFileWriter.setValue(key, value);
//			}
//			return new PropertyFileWriter();
//		} else {
//			log.error("Property 'key' and/or 'value' you entered have an EMPTY values!");
//			return null;
//		}
//	}
//	
//	public void storeExistingProperties(PropertyFile propertyFile) {
//		new PropertyFileReader().loadPropFile( propertyFile.getFilePath() );
//		Properties properties = PropertyFileReader.getProperties();
//		new PropertyFileWriter().setProperties(properties);
//	}

	public static Properties loadProperties(String filePath) {
		if (isNullOrEmpty(filePath))
			throw new IllegalArgumentException("[Resources] Defined file path is NULL or empty!");
		log.info("[Resources] Load properties from [" +filePath+ "] property file.");
		PropertyFileReader pfr = new PropertyFileReader();
		pfr.loadPropFile(filePath);
		return pfr.getProperties();
	}
	
	public static void cleanPropertiesHash() { 
		Properties properties = new Properties();
		new PropertyFileWriter().setProperties(properties);
	}

	public static Properties getProperties() {
		return properties;
	}
}

package com.firstutility.taf;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import static com.firstutility.taf.utils.StringUtils.*;
import com.firstutility.taf.utils.file.props.PropertyFileReader;
import com.firstutility.taf.utils.file.props.PropertyFileWriter;

public class Resources {
	
	private static final Logger log = Logger.getLogger(Resources.class);
	
	private static Properties properties;

	public static String getProperty(PropertyFile propertyFile, String key) {
		log.debug("[Resources] Init [" +String.valueOf(propertyFile)+ "] properties.");
		if ((propertyFile).getFilePath() != null) {
			PropertyFileReader propFileReader = new PropertyFileReader();
			log.debug("[Resources] Load [" +propertyFile.getFilePath()+ "] property file.");
			propFileReader.loadPropFile( propertyFile.getFilePath() );
			setProperties(propFileReader.getProperties());
			log.info("[Resources] Get property value for [" +key+ "] property key.");
			return propFileReader.getValue(key);
		} else if ((propertyFile).getBundlePath() != null) {
			ResourceBundle resource = null;
			log.debug("[Resources] Load [" +propertyFile.getBundlePath()+ "] resource bundle.");
			resource = ResourceBundle.getBundle( propertyFile.getBundlePath() );
			log.info("[Resources] Get property value for [" +key+ "] property key.");
			return resource.getString(key);
		}
		throw new IllegalArgumentException();
	}
	
	public static String getProperty(String filePath, String key) {
		if (!filePath.isEmpty()) {
			PropertyFileReader propFileReader = new PropertyFileReader();
			propFileReader.loadPropFile( filePath );
			setProperties(propFileReader.getProperties());
			return propFileReader.getValue(key);
		}
		throw new IllegalArgumentException();
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
		pfr.getProperties();
		return pfr.getProperties();
	}
	
	public static void cleanPropertiesHash() { 
		Properties properties = new Properties();
		new PropertyFileWriter().setProperties(properties);
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		Resources.properties = properties;
	}
}

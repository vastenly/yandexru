package com.firstutility.taf.utils.props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	private Properties properties;

	public PropertiesFileReader loadPropFile(String propsFilePath) {
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(propsFilePath);
			properties = new Properties();
			properties.load(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

}

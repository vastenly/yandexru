package com.firstutility.taf.utils.props;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileWriter {

	private Properties properties;
	
	public Properties writeToFile(String propsFilePath) {
		FileOutputStream outputFile = null;
		try {
			outputFile = new FileOutputStream(propsFilePath);
			properties.store(outputFile, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

}

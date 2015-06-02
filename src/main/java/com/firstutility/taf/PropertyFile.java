package com.firstutility.taf;

public enum PropertyFile {

	AUTOMATIONSSH_SETTINGS ( "src/main/resources/automationssh.properties", "" )
	;
	
	private String filePath;
	private String bundlePath;
	
	private PropertyFile(String filePath, String bundlePath) 
	{
		this.filePath = filePath;
		this.bundlePath = bundlePath;
	}
	
	public String getFilePath() {
		if (filePath == null || filePath.isEmpty()) {
			return null;
		}
		return filePath;
	}
	
	public String getBundlePath() {
		if (bundlePath == null || bundlePath.isEmpty()) {
			return null;
		}
		return bundlePath; 
	}
}

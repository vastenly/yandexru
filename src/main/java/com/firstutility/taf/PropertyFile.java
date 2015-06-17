package com.firstutility.taf;

public enum PropertyFile {

	AUTOMATION_SSH_SETTINGS ( "src/main/resources/automation-ssh.properties", "" ),
	FUSE_SSH_SETTINGS ( "src/main/resources/fuse-ssh.properties", "" ),
	STAGING_SSH_SETTINGS ( "src/main/resources/staging-ssh.properties", "" ),
	QA_SSH_SETTINGS ( "src/main/resources/qa-ssh.properties", "" )
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

package com.firstutility.taf.core.ui.exceptions;

public class BrowserAlertNotFoundException extends RuntimeException {
	
	/**
	 * Browser alert not found errors wrapper
	 */
	private static final long serialVersionUID = -3853551280636018717L;
	
	public BrowserAlertNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}

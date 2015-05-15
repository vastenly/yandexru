package com.firstutility.taf.core.ui.exceptions;

public class ElementWaitTimeoutException extends RuntimeException {

	/**
	 * Element wait timeout errors wrapper
	 */
	private static final long serialVersionUID = 7194627576367554682L;
	
	public ElementWaitTimeoutException(String errorMessage) {
		super(errorMessage);
	}
}

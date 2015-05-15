package com.firstutility.taf.core.ui.exceptions;

public class ElementNotFoundException extends RuntimeException {
	
	/**
	 * Element not found errors wrapper
	 */
	private static final long serialVersionUID = -5765022027857952044L;
	
	public ElementNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}

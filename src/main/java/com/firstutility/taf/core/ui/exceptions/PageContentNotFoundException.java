package com.firstutility.taf.core.ui.exceptions;

public class PageContentNotFoundException extends RuntimeException {
	
	/**
	 * Page content not found errors wrapper
	 */
	private static final long serialVersionUID = 7194627576367554682L;
	
	public PageContentNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}

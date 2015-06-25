package com.firstutility.taf.utils.exceptions;

public class InvalidIPv4AddressException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 8081512091994760898L;

	public InvalidIPv4AddressException( String errorMessage ) {
		super( errorMessage );
	}

}

package com.firstutility.taf.core.utils.exceptions;

public class InvalidIPv6AddressException extends IllegalArgumentException {

	private static final long serialVersionUID = -8589119417546000446L;

	public InvalidIPv6AddressException( String errorMessage ) {
		super( errorMessage );
	}
}

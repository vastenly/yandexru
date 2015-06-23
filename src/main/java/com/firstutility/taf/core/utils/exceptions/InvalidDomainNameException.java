package com.firstutility.taf.core.utils.exceptions;

public class InvalidDomainNameException extends IllegalArgumentException {

	private static final long serialVersionUID = -246552804787388723L;
	
	public InvalidDomainNameException( String errorMessage ) {
		super( errorMessage );
	}
}

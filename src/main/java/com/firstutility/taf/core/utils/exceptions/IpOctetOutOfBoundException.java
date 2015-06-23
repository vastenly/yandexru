package com.firstutility.taf.core.utils.exceptions;

public class IpOctetOutOfBoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 7583116765935711476L;
	
	public IpOctetOutOfBoundException( String errorMessage ) {
		super( errorMessage );
	}
}

package com.firstutility.taf.utils.exceptions;

public class TcpUdpPortOutOfBoundException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 3860478191248322512L;

	public TcpUdpPortOutOfBoundException( String errorMessage ) {
		super( errorMessage );
	}

}

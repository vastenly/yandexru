package com.firstutility.taf.utils.exceptions;

public class UnknownPropertyException extends RuntimeException {

	private static final long serialVersionUID = 4273619876040401219L;

	public UnknownPropertyException() {
        super();
    }
    
    public UnknownPropertyException(final String message) {
        super(message);
    }
}

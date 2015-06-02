package com.firstutility.taf.connect.db;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = -11620373366026604L;

	public DatabaseException(String msg) {
		super(msg);
	}

	public DatabaseException(String msg, Exception e) {
		super(msg, e);
	}
}
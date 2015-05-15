package com.firstutility.taf.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Database {
	
	private final Logger log = Logger.getLogger(Database.class);
	
	private Connection connection;
	
	private String name;
	
	public Database(String name, String dbUrl, String dbUser, String dbPassword, String dbDriverClassName) {
		this.setName(name);
		try {
			Class.forName(dbDriverClassName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection( dbUrl, dbUser, dbPassword );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("Connection established");
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			validateConnection();
			validateQuery(query);
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			log.info("Executing query - " + query);
			rs =  stmt.executeQuery(query);
		} catch (SQLException e ) {
			e.printStackTrace();
		} catch (DatabaseException e ) {
			e.printStackTrace();
		}
		return rs;
	}
	
	private void validateConnection() throws DatabaseException {
		if (connection == null) {
			String errMsg = "Connection to database has not been established.";
			log.error(errMsg);
			throw new DatabaseException(errMsg);
		}
	}

	private void validateQuery(String query) throws DatabaseException {
		if (query == null || query.isEmpty()) {
			String errMsg = "Query cannot be null";
			log.error(errMsg);
			throw new DatabaseException(errMsg);
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

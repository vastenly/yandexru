package com.firstutility.taf.connect.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Database {
	
	private static final Logger log = Logger.getLogger(Database.class);
	
	private String dbNname;
	private Connection connection;
	
	public Database(String dbName, String dbUrl, String dbUser, String dbPassword, String dbDriverClassName) {
		this.setDbName(dbName);
		try {
			Class.forName(dbDriverClassName);
			connection = DriverManager.getConnection( dbUrl, dbUser, dbPassword );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("[Database] Connection to [" +dbName+ "] database has established.");
	}
	
	public ResultSet executeQuery(String query) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			validateConnection();
			validateQuery(query);
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			log.debug("[Database] Executing query: \n" + query);
			rs =  stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	private void validateConnection() {
		if (connection == null) {
			String errMsg = "[Database] Connection to database [" +getDbName()+ "] database has not been established.";
			log.error(errMsg);
			throw new DatabaseException(errMsg);
		}
	}

	private void validateQuery(String query) {
		if (query == null || query.isEmpty()) {
			String errMsg = "[Database] Query cann't be NULL!";
			log.error(errMsg);
			throw new DatabaseException(errMsg);
		}
	}
	
	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getDbName() {
		return dbNname;
	}

	public void setDbName(String dbNname) {
		this.dbNname = dbNname;
	}
}

package com.firstutility.taf.core.database;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public enum DatabaseConnection {
	
	AFMS () {
		
		@Override
		protected void initiateConnection() {
			DB_URL = System.getProperty("afms.database.url");
			DB_USERNAME = System.getProperty("afms.database.user");
			DB_PASSWORD = System.getProperty("afms.database.password");
			DB_DRIVER_CLASS_NAME = System.getProperty("afms.database.driver");
		}
		
	},
	EDM_CUSTOMER () {

		@Override
		protected void initiateConnection() {
			DB_URL = System.getProperty("edmcustomer.database.url");
			DB_USERNAME = System.getProperty("edmcustomer.database.user");
			DB_PASSWORD = System.getProperty("edmcustomer.database.password");
			DB_DRIVER_CLASS_NAME = System.getProperty("edmcustomer.database.driver");
		}
		
	},
	REACH () {

		@Override
		protected void initiateConnection() {
			DB_URL = System.getProperty("reach.database.url");
			DB_USERNAME = System.getProperty("reach.database.user");
			DB_PASSWORD = System.getProperty("reach.database.password");
			DB_DRIVER_CLASS_NAME = System.getProperty("reach.database.driver");
		}
		
		
	},
	REACH_MPAN () {

		@Override
		protected void initiateConnection() {
			DB_URL = System.getProperty("reach.mpan.database.url");
			DB_USERNAME = System.getProperty("reach.mpan.database.user");
			DB_PASSWORD = System.getProperty("reach.mpan.database.password");
			DB_DRIVER_CLASS_NAME = System.getProperty("reach.mpan.database.driver");
		}
	},
	LOGNET () {

		@Override
		protected void initiateConnection() {
			DB_URL = "jdbc:oracle:thin:@lognet-ora-ec2.auto.impello.co.uk:1521:WTAUTO";
			DB_USERNAME = "reporting";
			DB_PASSWORD = "reporting";
			DB_DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
		}
	};
	
	private final Logger log = Logger.getLogger(DatabaseConnection.class);
	
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static String DB_DRIVER_CLASS_NAME;
	
	private static Map<DatabaseConnection, Database> dbPool = new HashMap<DatabaseConnection, Database>();
	
	protected abstract void initiateConnection();
	
	public Database connect() {
		if(!dbPool.containsKey(this)) {
			initiateConnection();
			log.info ("Establishing database connection to " + name());
			dbPool.put(this, new Database(toString(), DB_URL, DB_USERNAME, DB_PASSWORD, DB_DRIVER_CLASS_NAME));
		}
		return dbPool.get(this);
	}
	
	public static void closeAll() {
		for(Database db : dbPool.values()) {
			db.closeConnection();
		}
		dbPool.clear();
	}
}
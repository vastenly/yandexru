package com.firstutility.taf.connect.db;

public class SQLQuery {

	private String sqlQuery;
	
	public SQLQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
	public String getSqlQuery() {
		return sqlQuery;
	}

	public String setSqlQueryVariable(Object variable) {
		return String.format( getSqlQuery(), variable );
	}
	
	public String setSqlQueryVariables(Object... variables) {
		return String.format( getSqlQuery(), variables );
	}

}

package com.firstutility.taf.core.enums;

public enum SortingType {
	ASCENDING ( "ASC"),
	DESCENDING( "DESC");

	private String shortName;
		
	private SortingType(String shortName) {
		this.shortName = shortName;
	}
	
	public String getShortName() {
		return shortName;
	}
}





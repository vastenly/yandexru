package com.firstutility.taf.core.utils.string;

import sun.net.util.IPAddressUtil;

public class StringValidator {

	public static boolean isIPv4Address(String ipAddress) {
		if( ipAddress.length() == 0 )
			return false;
		int number;
		String octets[] = ipAddress.split("\\.");
		if ( octets.length != 4 )
			return false;
		for (int i=0; i<4; i++) {
			number = Integer.parseInt( octets[i] );
			if ( !isIPOctet(number) )
				return false;
		}			
		return true; 
	}
	
	public static boolean isIPv6Address(String ipAddress) {
		return IPAddressUtil.isIPv6LiteralAddress(ipAddress);
	}
	
	public static boolean isDomainName(String address) {
		if ( address.length() == 0 )
			return false;
		int dotIndex = address.indexOf(".");
		if ( dotIndex == -1 )
			return false;
		String currentDomainPart = "";
		while (dotIndex != -1) {
			if (dotIndex+1 == address.length())
				return false;
			currentDomainPart = address.substring(0, dotIndex); 
			if (currentDomainPart.length() < 1)
				return false;
			if (containsSpecialSymbols(currentDomainPart))
				return false;
			address = address.substring(dotIndex+1);
			dotIndex = address.indexOf(".");
		}
		return containsSpecialSymbols(address);
	}
	
	public static boolean containsSpecialSymbols(String str) {
		//Check against following special symbols: !@#$%^&*()_+|-=\{}[]:";'<>?,./   -- need feedback from ?
		if( str.indexOf("!")!=-1 || str.indexOf("@")!=-1 || str.indexOf("#")!=-1 ||  str.indexOf("$")!=-1 || str.indexOf("%")!=-1 || 
				str.indexOf("^")!=-1 || str.indexOf("&")!=-1 || str.indexOf("*")!=-1 || str.indexOf("(")!=-1 || str.indexOf(")")!=-1 || 
				str.indexOf("_")!=-1 || str.indexOf("+")!=-1 || str.indexOf("|")!=-1 || str.indexOf("-")!=-1 || str.indexOf("=")!=-1 || 
				str.indexOf("\\")!=-1 || str.indexOf("{")!=-1 || str.indexOf("}")!=-1 || str.indexOf("[")!=-1 || str.indexOf("]")!=-1 ||
				str.indexOf(":")!=-1 || str.indexOf("\"")!=-1 || str.indexOf(";")!=-1 || str.indexOf("'")!=-1 || str.indexOf("<")!=-1 ||
				str.indexOf(">")!=-1 || str.indexOf("?")!=-1 || str.indexOf(",")!=-1 || str.indexOf(".")!=-1 || str.indexOf("/")!=-1 )
			return false;
		return true;
	}
	
	public static boolean isTcpUdpPort(int port) {
		return ( port>=0 && port<=65535 );
	}
	
	public static boolean isIPOctet(int number) {
		return ( number>=0 && number<=255 );
	}
}
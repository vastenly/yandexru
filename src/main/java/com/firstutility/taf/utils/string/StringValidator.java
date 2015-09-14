package com.firstutility.taf.utils.string;

import java.util.regex.Pattern;

import com.firstutility.taf.core.enums.InetAddressType;

import sun.net.util.IPAddressUtil;

public class StringValidator {

	public static boolean isIPv4Address(String ipAddress) {
		if (ipAddress.length() == 0)
			return false;
		int number;
		String octets[] = ipAddress.split("\\.");
		if ( octets.length != 4 )
			return false;
		for (int i=0; i<4; i++) {
			number = Integer.parseInt( octets[i] );
			if (!isIPOctet(number))
				return false;
		}			
		return true; 
	}
	
	public static boolean isIPv6Address(String ipAddress) {
		return IPAddressUtil.isIPv6LiteralAddress(ipAddress);
	}
	
	public static boolean isDomainName(String domainName) {
		String domainNamePattern = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(domainNamePattern);
		return pattern.matcher(domainName).find();
	}
	
	public static InetAddressType getInetAddressType(String inetAddress) {
		if (isDomainName(inetAddress))
			return InetAddressType.DOMAIN_NAME;
		if (isIPv4Address(inetAddress))
			return InetAddressType.IPv4;
		if (isIPv6Address(inetAddress))
			return InetAddressType.IPv6;
		throw new IllegalArgumentException("Ivalid inet address provided [" +inetAddress+ "]!");
	 }
	
	public static boolean containsSpecialSymbols(String str) {
		//Check against following special symbols: !@#$%^&*()_+|-=\{}[]:";'<>?,./   -- need feedback from ?
		if (str.indexOf("!")!=-1 || str.indexOf("@")!=-1 || str.indexOf("#")!=-1 ||  str.indexOf("$")!=-1 || str.indexOf("%")!=-1 || 
				str.indexOf("^")!=-1 || str.indexOf("&")!=-1 || str.indexOf("*")!=-1 || str.indexOf("(")!=-1 || str.indexOf(")")!=-1 || 
				str.indexOf("_")!=-1 || str.indexOf("+")!=-1 || str.indexOf("|")!=-1 || str.indexOf("-")!=-1 || str.indexOf("=")!=-1 || 
				str.indexOf("\\")!=-1 || str.indexOf("{")!=-1 || str.indexOf("}")!=-1 || str.indexOf("[")!=-1 || str.indexOf("]")!=-1 ||
				str.indexOf(":")!=-1 || str.indexOf("\"")!=-1 || str.indexOf(";")!=-1 || str.indexOf("'")!=-1 || str.indexOf("<")!=-1 ||
				str.indexOf(">")!=-1 || str.indexOf("?")!=-1 || str.indexOf(",")!=-1 || str.indexOf(".")!=-1 || str.indexOf("/")!=-1)
			return false;
		return true;
	}
	
	public static boolean isTcpUdpPort(int port) {
		return (port>=0 && port<=65535);
	}
	
	public static boolean isIPOctet(int number) {
		return (number>=0 && number<=255);
	}
}
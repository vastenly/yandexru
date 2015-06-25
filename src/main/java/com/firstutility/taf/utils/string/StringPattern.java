package com.firstutility.taf.utils.string;

import com.firstutility.taf.core.enums.InetAddressType;
import com.firstutility.taf.core.enums.Protocol;
import com.firstutility.taf.utils.exceptions.InvalidDomainNameException;
import com.firstutility.taf.utils.exceptions.InvalidIPv4AddressException;
import com.firstutility.taf.utils.exceptions.InvalidIPv6AddressException;
import com.firstutility.taf.utils.exceptions.IpOctetOutOfBoundException;
import com.firstutility.taf.utils.exceptions.TcpUdpPortOutOfBoundException;

public class StringPattern {

	public static String empty() {
		return "";
	}
	
	//Note: valid using IPv6 Addresses in URL example http://[2001:0db8:0:0:0:0:0:765d]:8080/
	public static String uri(InetAddressType inetAddressType, String address) {
		if( inetAddressType == InetAddressType.IPv4 )
			if( !StringValidator.isIPv4Address(address) )
				throw new InvalidIPv4AddressException("[StringPattern] IPv4 address is incorrect!");
		if( inetAddressType == InetAddressType.IPv6 )
			if( !StringValidator.isIPv6Address(address) )
				throw new InvalidIPv6AddressException("[StringPattern] IPv6 address is incorrect!");
		if( inetAddressType==InetAddressType.DOMAIN_NAME )
			if( !StringValidator.isDomainName(address) )
				throw new IpOctetOutOfBoundException("[StringPattern] Domain name is incorrect!");	
		String inetAddress = null;
		if( inetAddressType==InetAddressType.IPv4 || inetAddressType==InetAddressType.DOMAIN_NAME )
			inetAddress = address;
		if( inetAddressType==InetAddressType.IPv6 )
			inetAddress = "[" + address + "]";
		return inetAddress;
	}
	
	public static String uri(InetAddressType inetAddressType, String address, int port) {
		if( !StringValidator.isTcpUdpPort(port) )
			throw new TcpUdpPortOutOfBoundException("[StringPattern] Port is incorrect!");
		return uri(inetAddressType, address) + ":" + port;
	}
	
	public static String url(Protocol protocol, InetAddressType inetAddressType, String address) {
		String protocolName = "";
		if (protocol == Protocol.ANY)
			protocolName = Protocol.getRandomName();
		else 
			protocolName = protocol.toString();
		return protocolName + "://" + uri(inetAddressType, address); 
	}
	
	public static String url(Protocol protocol, InetAddressType inetAddressType, String address, int port) {
		if (!StringValidator.isTcpUdpPort(port))
			throw new TcpUdpPortOutOfBoundException("[StringPattern] Port is incorrect!");
		return url(protocol, inetAddressType, address) + ":" + port;
	}
	
	public static String email(String emailLocalPart, String emailDomainPart) {
		if (!StringValidator.isDomainName(emailDomainPart))
			throw new InvalidDomainNameException("[StringPattern] Email domain part is invalid!");
		return emailLocalPart + "@" + emailDomainPart;
	}
}
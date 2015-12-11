package com.firstutility.taf.utils.string;

import static com.firstutility.taf.utils.NumberUtils.isNull;
import static com.firstutility.taf.utils.string.StringValidator.getInetAddressType;
import static com.firstutility.taf.utils.string.StringValidator.isDomainName;
import static com.firstutility.taf.utils.string.StringValidator.isIPv4Address;
import static com.firstutility.taf.utils.string.StringValidator.isIPv6Address;
import static com.firstutility.taf.utils.string.StringValidator.isTcpUdpPort;
import static com.firstutility.taf.utils.StringUtils.isBlank;

import com.firstutility.taf.core.enums.InetAddressType;
import com.firstutility.taf.core.enums.Protocol;
import com.firstutility.taf.utils.exceptions.InvalidDomainNameException;
import com.firstutility.taf.utils.exceptions.InvalidIPv4AddressException;
import com.firstutility.taf.utils.exceptions.InvalidIPv6AddressException;
import com.firstutility.taf.utils.exceptions.IpOctetOutOfBoundException;
import com.firstutility.taf.utils.exceptions.TcpUdpPortOutOfBoundException;

public class StringPattern {

	//Note: valid using IPv6 Addresses in URL example http://[2001:0db8:0:0:0:0:0:765d]:8080/
	
	/**
	 * @param inetAddressType
	 * @param host
	 * @return validated URI string
	 */
	public static String uri(InetAddressType inetAddressType, String host) {
		if (inetAddressType == InetAddressType.IPv4)
			if (!isIPv4Address(host))
				throw new InvalidIPv4AddressException("[StringPattern] IPv4 address is NOT correct!");
		if (inetAddressType == InetAddressType.IPv6)
			if (!isIPv6Address(host))
				throw new InvalidIPv6AddressException("[StringPattern] IPv6 address is NOT correct!");
		if (inetAddressType==InetAddressType.DOMAIN_NAME)
			if (!isDomainName(host))
				throw new IpOctetOutOfBoundException("[StringPattern] Domain name is NOT correct!");	
		String inetAddress = null;
		if (inetAddressType==InetAddressType.IPv4 || inetAddressType==InetAddressType.DOMAIN_NAME)
			inetAddress = host;
		if (inetAddressType==InetAddressType.IPv6)
			inetAddress = "[" + host + "]";
		return inetAddress;
	}
	
	/**
	 * @param inetAddressType
	 * @param host
	 * @param port
	 * @return validated URI string with port (simple URI string in case of port is null)
	 */
	public static String uri(InetAddressType inetAddressType, String host, Integer port) {
		if (isNull(port))
			return uri(inetAddressType, host);
		if (!isTcpUdpPort(port))
			throw new TcpUdpPortOutOfBoundException("[StringPattern] Port is NOT correct!");
		return uri(inetAddressType, host) + ":" + port;
	}
	
	/**
	 * @param protocol
	 * @param inetAddressType
	 * @param host
	 * @return validated URL string
	 */
	public static String url(Protocol protocol, InetAddressType inetAddressType, String host) {
		String protocolName = "";
		if (protocol == Protocol.ANY)
			protocolName = Protocol.getRandomName();
		else 
			protocolName = protocol.toString();
		return protocolName + "://" + uri(inetAddressType, host); 
	}

	/**
	 * @param protocol
	 * @param inetAddressType
	 * @param host
	 * @param port
	 * @return validated URL string with port (simple URL string in case of port is null)
	 */
	public static String url(Protocol protocol, InetAddressType inetAddressType, String host, Integer port) {
		if (isNull(port))
			return url(protocol, inetAddressType, host);
		if (!isTcpUdpPort(port))
			throw new TcpUdpPortOutOfBoundException("[StringPattern] Port number is NOT correct!");
		return url(protocol, inetAddressType, host) + ":" + port;
	}
	
	/**
	 * @param protocol
	 * @param inetAddressType
	 * @param host
	 * @param port
	 * @return validated URL string with port (simple URL string in case of port is blank value)
	 */
	public static String url(Protocol protocol, InetAddressType inetAddressType, String host, String port) {
		if (isBlank(port))
			return url(protocol, inetAddressType, host);
		if (!isTcpUdpPort(Integer.parseInt(port)))
			throw new TcpUdpPortOutOfBoundException("[StringPattern] Port number is NOT correct!");
		return url(protocol, inetAddressType, host) + ":" + port;
	}
	
	/**
	 * @param protocol
	 * @param host
	 * @return validated URL string
	 */
	public static String url(String protocol, String host) {
		Protocol $protocol = Protocol.valueOf(protocol.toUpperCase());
		return url($protocol, getInetAddressType(host), host); 
	}
	
	/**
	 * @param protocol
	 * @param host
	 * @param port
	 * @return validated URL string with port (simple URL string in case of port is null)
	 */
	public static String url(String protocol, String host, Integer port) {
		Protocol $protocol = Protocol.valueOf(protocol.toUpperCase());
		return url($protocol, getInetAddressType(host), host, port); 
	}
	
	/**
	 * @param protocol
	 * @param host
	 * @param port
	 * @return validated URL string with port (simple URL string in case of port is blank value)
	 */
	public static String url(String protocol, String host, String port) {
		Protocol $protocol = Protocol.valueOf(protocol.toUpperCase());
		return url($protocol, getInetAddressType(host), host, port); 
	}
	
	public static String email(String emailLocalPart, String emailDomainPart) {
		if (!isDomainName(emailDomainPart))
			throw new InvalidDomainNameException("[StringPattern] Email domain part is NOT valid!");
		return emailLocalPart + "@" + emailDomainPart;
	}
}
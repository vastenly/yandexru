package com.firstutility.taf.core.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Note: important to keep "ANY" as last member, because randomProtocol() chooses value between first and last-1 in this enum 
public enum Protocol {
	
	HTTP,
	HTTPS,
	FTP, 
	FTPS,
	SFTP,
	FTPES,
	ANY;
	
	private static final List<Protocol> values = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int size = values.size();
	private static final Random random = new Random();
	
	public static String getRandomName() {
		return values.get(random.nextInt(size-1)).toString().toLowerCase();
	}
}
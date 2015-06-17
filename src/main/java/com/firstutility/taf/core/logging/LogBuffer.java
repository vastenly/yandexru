package com.firstutility.taf.core.logging;

import java.util.ArrayList;
import java.util.List;

public class LogBuffer {
	
	private List<String> logbuffer;
	
	public LogBuffer() {
		this.logbuffer = new ArrayList<String>();
	}

	public synchronized List<String> asList() {
		return logbuffer;
	}

	public void print() {

		synchronized (logbuffer) {
		
			org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(LogBuffer.class);

			for (String logMessage : logbuffer) {
				if (logMessage.startsWith("[info]")) {
					if (!logMessage.endsWith("[info]")) {
						log4j.info(logMessage.split("\\[info\\]")[1]);
					} else {
						log4j.info("");
					}
				}
				if (logMessage.startsWith("[debug]")) {
					if(!logMessage.endsWith("[debug]")){
						log4j.debug(logMessage.split("\\[info\\]")[1]);
					} else {
						log4j.debug("");
					}
				}
				if (logMessage.startsWith("[error]")) {
					log4j.error(logMessage.split("\\[error\\]")[1]);
				}
			}
			logbuffer.clear();
		}
	}
}

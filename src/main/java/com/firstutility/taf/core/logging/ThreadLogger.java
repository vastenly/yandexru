package com.firstutility.taf.core.logging;

public class ThreadLogger implements Logger {

	private static ThreadLocal<LogBuffer> logbuffer;
	
	public static void initLogger() {
		logbuffer = new ThreadLocal<LogBuffer>() {
			@Override
			protected LogBuffer initialValue() {
				return new LogBuffer();
			}
		};
	}
	
	public static ThreadLogger getLogger() {
		return new ThreadLogger();
	}

	@Override
	public synchronized void info(Object logMessage) {
		logbuffer.get().asList().add("[info]" + logMessage);
	}

	@Override
	public synchronized void debug(Object logMessage) {
		logbuffer.get().asList().add("[debug]" + logMessage);
	}
	
	@Override
	public void warn(Object logMessage) {
		logbuffer.get().asList().add("[warn]" + logMessage);
	}

	@Override
	public synchronized void error(Object logMessage) {
		logbuffer.get().asList().add("[error]" + logMessage);
	}
	
	public LogBuffer getLogs() {
		return logbuffer.get();
	}

}

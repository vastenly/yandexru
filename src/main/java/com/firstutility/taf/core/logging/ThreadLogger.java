package com.firstutility.taf.core.logging;

public class ThreadLogger implements Logger {

	private static ThreadLocal<LogBuffer> logbufferTlv;
	
	public static void initLogger() {
		logbufferTlv = new ThreadLocal<LogBuffer>() {
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
		logbufferTlv.get().asList().add("[info]" + logMessage);
	}

	@Override
	public synchronized void debug(Object logMessage) {
		logbufferTlv.get().asList().add("[debug]" + logMessage);
	}
	
	@Override
	public void warn(Object logMessage) {
		logbufferTlv.get().asList().add("[warn]" + logMessage);
	}

	@Override
	public synchronized void error(Object logMessage) {
		logbufferTlv.get().asList().add("[error]" + logMessage);
	}
	
	public LogBuffer getLogs() {
		return logbufferTlv.get();
	}

}

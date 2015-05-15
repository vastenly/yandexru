package com.firstutility.taf.core.selenium.browser;

public class BrowserConstants {
	
	// drivers paths
	public static final String CHROME_WINDOWS_PATH = "drivers\\chromedriver.exe";
	public static final String CHROME_LINUX_PATH = "drivers/chromedriver";
	public static final String IE_PATH = "drivers\\IEDriverServer.exe";
	public static final String PHANTOM_JS_WINDOWS_PATH = "drivers\\phantomjs_1.9.7-windows_x86_64.exe";
	public static final String PHANTOM_JS_LINUX_PATH = "drivers/phantomjs_1.9.7-linux_x86_64";
	public static final String CHROME_ADBLOCK_PATH = "drivers/Gundlach-AdBlock_2_6_37.crx";
	//drivers options
	public static final String CHROME_IGNORE_FLAG_CMD_ARGS = "test-type";
	public static final String CHROME_WORK_PROFILE = "--user-data-dir=";
	public static final String CHROME_DRIVER_BINARY_PROPERTY = "chrome.binary";
	public static final String CHROME_LINUX_BINARY_PATH ="/usr/bin/google-chrome-stable";
	public static final String REMOTE_DRIVER_URL = "http/127.0.0.1:4444/wd/hub";
	//test properties
	public static final String TEST_PROPERTIES_PATH = "test.properties";
	public static final String LOAD_TIMEOUT_PROPERTY = "browser.loadTimeout";
	public static final String POLLING_INTERVAL_PROPERTY = "browser.pollingInterval";
	public static final String SCREENSHOT_LOCATION_PROPERTY = "test.screenshots.location";
	// JS constants
	public static final String JS_LIB_PATH = "js/jsLib.js";
	public static final String INIT_JS_TEMPLATE = "if(!document.getElementById('dynamicScripts')){"
			+ "var s=window.document.createElement('script');"
			+ "s.setAttribute('id','dynamicScripts');"
			+ "s.innerHTML='%s';"
			+ "window.document.head.appendChild(s);}";
	// other constants
	public static final String USER_DIR_PROPERTY = "user.dir";
}

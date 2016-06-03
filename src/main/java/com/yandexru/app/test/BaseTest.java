package com.yandexru.app.test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.log4j.Logger;

import com.godeltech.taf.core.selenium.browser.BrowserType;
import com.godeltech.taf.core.ui.Browser;
import com.godeltech.taf.utils.prop.ConfigProperties;
import com.yandexru.app.Application;

public class BaseTest {
	
	protected static Browser browser;
	public Application app = new Application();
	protected static final Logger log = Logger.getLogger(BaseTest.class);
	
	public BaseTest() {
		
	}
	
	@BeforeTest
	public void before() {
		browser = new Browser(BrowserType.CHROME_WINDOWS);
		browser.openUrl(ConfigProperties.getProperty("main.url"));
		browser.switchToNewOpenedWindow();
		browser.windowMaximize();
		browser.waitForTimeout(Integer.parseInt(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS); 
	}
	
	@AfterTest
	public void after() {
		browser.close();
	}	
}

package com.firstutility.taf.tools.bdd.cucumber;

import org.apache.log4j.Level;
import org.junit.Assert;

import com.firstutility.taf.core.logging.Logger;
import com.firstutility.taf.core.logging.ThreadLogger;
import com.firstutility.taf.core.selenium.browser.BrowserType;
import com.firstutility.taf.core.ui.Browser;

import cucumber.api.Scenario;

public class CucumberBaseTest extends Assert {

	private static ThreadLocal<Browser> browserThreadLocal;
	protected Browser browser;

	protected static final Logger log = ThreadLogger.getLogger();
	
	public CucumberBaseTest(final BrowserType browserType) {
		if (browserThreadLocal == null) {
			browserThreadLocal = new ThreadLocal<Browser>() {
				@Override
				protected Browser initialValue() {
					return new Browser(browserType);
				}
			};
		}
		browser = browserThreadLocal.get();
	}
	
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	
	public void beforeScenario(Scenario scenario) {
		CucumberReportUtils.parseScenario(scenario);
		log.info("================================================");
		log.info("Scenario: " + CucumberReportUtils.getScenarioOutlineName());
		log.info("================================================");
		log.info("Customer: " + CucumberReportUtils.getScenarioName());
		log.info("================================================");
	}
	
	public void afterScenario(Scenario scenario) {
		browser.ifIsAlertPresent().printAlertText(Level.ERROR).acceptUnexpectedAlert();
		CucumberReportUtils.makeScreenshotOnFail(browser, scenario);
		printLog();
	}

	private void printLog() {
		((ThreadLogger) log).getLogs().print();
	}

}

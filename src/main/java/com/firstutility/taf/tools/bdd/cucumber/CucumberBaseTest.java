package com.firstutility.taf.tools.bdd.cucumber;

import org.junit.Assert;

import com.firstutility.taf.core.logging.Logger;
import com.firstutility.taf.core.logging.ThreadLogger;
import com.firstutility.taf.core.ui.Browser;
import com.firstutility.taf.tools.bdd.cucumber.CucumberReportUtils;

import cucumber.api.Scenario;

public class CucumberBaseTest extends Assert {

	private Browser browser;
	protected static final Logger log = ThreadLogger.getLogger();
	
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
		CucumberReportUtils.makeScreenshotOnFail(browser, scenario);
		printLog();
	}

	private void printLog() {
		((ThreadLogger) log).getLogs().print();
	}

}

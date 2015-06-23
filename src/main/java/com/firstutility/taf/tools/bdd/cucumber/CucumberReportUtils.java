package com.firstutility.taf.tools.bdd.cucumber;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.firstutility.taf.core.ui.Browser;

import cucumber.api.Scenario;
import cucumber.runtime.junit.ExecutionUnitRunner;
import cucumber.runtime.junit.JUnitReporter;

public class CucumberReportUtils {
	
	private static String scenarioOutlineName;
	private static String scenarioName;

	public synchronized static String getScenarioOutlineName() {
		return scenarioOutlineName;
	}
	
	public synchronized static String getScenarioName() {
		return scenarioName;
	}
	
	public synchronized static void parseScenario(Scenario scenario) {
		try {
			Field f = scenario.getClass().getDeclaredField("reporter");
	        f.setAccessible(true);
	        JUnitReporter reporter = (JUnitReporter) f.get(scenario);
	        
	        Field executionRunnerField = reporter.getClass().getDeclaredField("executionUnitRunner");
	        executionRunnerField.setAccessible(true);
	        ExecutionUnitRunner executionUnitRunner = (ExecutionUnitRunner) executionRunnerField.get(reporter);

	        Field descriptionField = executionUnitRunner.getClass().getDeclaredField("description");
	        descriptionField.setAccessible(true);
	        final Description description = (Description) descriptionField.get(executionUnitRunner);
	        
	        scenarioName = description.getDisplayName();
	        
	        Field uniqueIdField = description.getClass().getDeclaredField("fUniqueId");
	        uniqueIdField.setAccessible(true);
	        final gherkin.formatter.model.Scenario uniqueId = (gherkin.formatter.model.Scenario) uniqueIdField.get(description);
	        
	        scenarioOutlineName = uniqueId.getName();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}		
	
	public synchronized static void makeScreenshotOnFail(Browser browser, Scenario scenario) {
	    if (scenario.isFailed()) {
	    	final byte[] screenshot = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
		    File scrFile = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    
		    scenario.embed(screenshot, "image/png");
		    String fileName = (getScenarioOutlineName() + getScenarioName()).replaceAll("\\s|:|\\|", "_");
		    try {
		    	FileUtils.copyFile(scrFile, new File("screenshots" + File.separator + fileName + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

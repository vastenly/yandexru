package com.firstutility.taf.core.ui.controls;

import org.openqa.selenium.WebElement;

import com.firstutility.taf.core.ui.EditableElement;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class Input extends EditableElement {
	
	public Input(String locator) {
		super(locator);
	}

	public Input(String locator, String name) {
		super(locator, name);
	}
	
	public void setValue(String value) {
		WebElement input = driver.findElement(lh.getByType(locator));
		JavascriptLibrary javascript = new JavascriptLibrary();
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keydown");
		input.sendKeys(value);
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keyup");
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "blur");
	}
	
	public void setValue(int value) {
		WebElement input = driver.findElement(lh.getByType(locator));
		JavascriptLibrary javascript = new JavascriptLibrary();
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keydown");
		input.sendKeys(String.valueOf(value));
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keyup");
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "blur");
	}
	
	public void setValue(double value) {
		WebElement input = driver.findElement(lh.getByType(locator));
		JavascriptLibrary javascript = new JavascriptLibrary();
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keydown");
		input.sendKeys(String.valueOf(value));
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "keyup");
		javascript.callEmbeddedSelenium(driver, "triggerEvent", input, "blur");
	}

	/**
	 * Submit the specified form. 
	 * This is particularly useful for forms without submit buttons 
	 */
	public void submit() {		
		driver.findElement(lh.getByType(locator)).submit();
	}
	
	public void clear() {
		driver.findElement(lh.getByType(locator)).clear();
	}
	
	public String getType() {		
		return getSpecificAttributeValue("type");		
	}
	
	@Override
	public Input setLocatorStringVariable(String stringVariable) {
		return new Input(String.format(getLocator(), stringVariable));
	}

	@Override
	public Input setLocatorStringVariables(Object... stringVariables) {
		return new Input(String.format(getLocator(), stringVariables));
	}

}

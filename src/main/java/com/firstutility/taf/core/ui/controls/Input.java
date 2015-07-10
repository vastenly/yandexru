package com.firstutility.taf.core.ui.controls;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.firstutility.taf.core.ui.EditableElement;
import com.firstutility.taf.core.ui.exceptions.ElementNotFoundException;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class Input extends EditableElement {
	
	public Input(String locator) {
		super(locator);
	}

	public Input(String locator, String name) {
		super(locator, name);
	}
	
	public void jsSetValue(String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value +"')", driver.findElement(lh.getByType(locator)));
	}
	
	public void jQuerySetValue(String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement input = driver.findElement(lh.getByType(locator));
		if (input.getAttribute("id") != null)
			js.executeScript("$(\"" + input.getAttribute("id") + "\").val( "+ value +" );");
		else if (input.getAttribute("name") != null)
			js.executeScript("$(\"" + input.getAttribute("name") + "\").val( "+ value +" );");
		else
			throw new ElementNotFoundException("[Input] Could not find attributes id or name on Input with locator " + getLocator() + "]");
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

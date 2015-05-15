package com.firstutility.taf.core.ui.controls;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.firstutility.taf.core.ui.EditableElement;
import com.firstutility.taf.utils.random.RandomNumeric;

public class Select extends EditableElement {

	public Select(String locator) {
		super(locator);
	}

	public Select(String locator, String name) {
		super(locator, name);
	}
	
	/**
	 * Checking is something already selected in the select-menu
	 * @return true - if something already selected
 	 *         false - if nothing selected
	 */
	public boolean isSelected() {
		try {
			if (driver.findElement(lh.getByType(locator)).isSelected()) {
				return true;
			}
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return false;
	}
	
	/**
	 * Getting selected option @label
	 */
	public String getSelectedOption() {
		return new org.openqa.selenium.support.ui.Select(driver.findElement(lh.getByType(locator))).getFirstSelectedOption().getText();
	}
	
	/**
	 * Getting selected option @value
	 */
	public String getSelectedValue() {
		return new org.openqa.selenium.support.ui.Select(driver.findElement(lh.getByType(locator))).getFirstSelectedOption().getAttribute("value");
	}

	/**
	 * Selecting specific option by their @textLabel
	 */
	public void selectOption(final String textLabel) {
		new org.openqa.selenium.support.ui.Select(driver.findElement(lh.getByType(locator))).selectByVisibleText(textLabel);
	}
	
	/**
	 * Selecting specific option by their @value
	 */
	public void selectValue(final String attrValue) {
		new org.openqa.selenium.support.ui.Select(driver.findElement(lh.getByType(locator))).selectByValue(attrValue);
	}

	/**
	 * Getting all visible options in the selectbox
	 * @return List<String> - all visible options
	 */
	public List<String> getAllOptionsList() {		
		List<WebElement> options = new org.openqa.selenium.support.ui.Select(driver.findElement(lh.getByType(locator))).getOptions();
		List<String> optionsList = new ArrayList<String>();
		for (WebElement option : options) {
			optionsList.add(option.getText());
		}
		return optionsList;
	}
	
	public String selectRandom() {
		List<String> possibleOptions = getAllOptionsList();
		String option = possibleOptions.get(RandomNumeric.getRandomInt(0, possibleOptions.size()));
		if (option.equals("*") || option.endsWith("-----")) {
			option = selectRandom();
		}
		selectOption(option);
		return option;
	}
	
	public String selectRandomExcept(String exception) {
		List<String> possibleOptions = getAllOptionsList();
		String option = possibleOptions.get(RandomNumeric.getRandomInt(0, possibleOptions.size()));
		if (option.equals("*") || option.endsWith("-----") || option.contains(exception)) {
			option = selectRandomExcept(exception);
		}
		selectOption(option);
		return option;
	}

	
	@Override
	public Select setLocatorStringVariable(String stringVariable) {
		return new Select(String.format(getLocator(), stringVariable));
	}

	@Override
	public Select setLocatorStringVariables(Object... stringVariables) {
		return new Select(String.format(getLocator(), stringVariables));
	}
}

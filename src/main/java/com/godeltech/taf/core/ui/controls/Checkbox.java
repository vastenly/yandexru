package com.godeltech.taf.core.ui.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;

import com.godeltech.taf.core.ui.EditableElement;

public class Checkbox extends EditableElement {
	
	private static final Logger log = Logger.getLogger(Checkbox.class);

	public Checkbox(String locator) {
		super(locator);
	}

	public Checkbox(String locator, String name) {
		super(locator, name);
	}

	
	@Override
	public Checkbox setLocatorStringVariable(String stringVariable) {
		return new Checkbox(String.format(getLocator(), stringVariable));
	}

	@Override
	public Checkbox setLocatorStringVariables(Object... stringVariables) {
		return new Checkbox(String.format(getLocator(), stringVariables));
	}
}

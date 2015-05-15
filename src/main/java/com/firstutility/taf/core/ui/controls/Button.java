package com.firstutility.taf.core.ui.controls;

import com.firstutility.taf.core.ui.EditableElement;

public class Button extends EditableElement {

	public Button(String locator) {
		super(locator);
	}

	public Button(String locator, String name) {
		super(locator, name);
	}

	@Override
	public Button setLocatorStringVariable(String stringVariable) {
		return new Button(String.format(getLocator(), stringVariable));
	}

	@Override
	public Button setLocatorStringVariables(Object... stringVariables) {
		return new Button(String.format(getLocator(), stringVariables));
	}
}

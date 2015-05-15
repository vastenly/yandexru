package com.firstutility.taf.core.ui.controls;

import com.firstutility.taf.core.ui.Element;

public class Tab extends Element {

	public Tab(String locator) {
		super(locator);
	}
	
	public Tab(String locator, String name) {
		super(locator, name);
	}
	
	@Override
	public Tab setLocatorStringVariable(String stringVariable) {
		return new Tab(String.format(getLocator(), stringVariable));
	}

	@Override
	public Tab setLocatorStringVariables(Object... stringVariables) {
		return new Tab(String.format(getLocator(), stringVariables));
	}
}

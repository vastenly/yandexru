package com.godeltech.taf.core.ui.controls;

import com.godeltech.taf.core.ui.Element;

public class Link extends Element {
	
	public Link(String locator) {
		super(locator);
	}

	public Link(String locator, String name) {
		super(locator, name);
	}
	
	@Override
	public Link setLocatorStringVariable(String stringVariable) {
		return new Link(String.format(getLocator(), stringVariable));
	}

	@Override
	public Link setLocatorStringVariables(Object... stringVariables) {
		return new Link(String.format(getLocator(), stringVariables));
	}

}

package com.firstutility.taf.core.ui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;

public class EditableElement extends Element {

	public EditableElement(String locator) {
		super(locator);
	}
	
	public EditableElement(String locator, String name) {
		super(locator, name);
	}

	public boolean isEditable() {
		try {
			if (driver.findElement(lh.getByType(locator)).isEnabled()) {
				return true;
			}
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return false;
	}
	
	public boolean isEnabled() {
		if (isVisible() && isEditable()) {
			return true;
		}
		return false;
	}
	
	public boolean waitForToBeEditable() {
		int timeout = 10;
		for (int second = 0; ; second++) {
			if (second >= timeout) {
				break;
			}
			try {
				if (isEditable()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}	
		return false;
	}
	
	public boolean waitForToBeEditable(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout) {
				break;
			}
			try {
				if (isEditable()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}
		return false;
	}

	public boolean waitForToBeNotEditable() {
		int timeout = 10;
		for (int second = 0; ; second++) {
			if (second >= timeout) {
				break;
			}
			try {
				if (!isVisible()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}	
		return false;
	}
	
	public boolean waitForToBeNotEditable(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout) {
				break;
			}
			try {
				if (!isVisible()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}	
		return false;
	}
}

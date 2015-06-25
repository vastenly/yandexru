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

	public boolean isEnabled() {
		try {
			if (driver.findElement(lh.getByType(locator)).isEnabled())
				return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return false;
	}
	
	public boolean isEditable() {
		if (isVisible() && isEditable())
			return true;
		return false;
	}
	
	public boolean waitForToBeEnabled() {
		int timeout = 10;
		return waitForToBeEnabled(timeout);
	}
	
	public boolean waitForToBeEnabled(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout)
				break;
			try {
				if (isEnabled())
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}
		return false;
	}
	
	public boolean waitForToBeEditable() {
		int timeout = 10;
		return waitForToBeEditable(timeout);
	}
	
	public boolean waitForToBeEditable(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout)
				break;
			try {
				if (isEditable())
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}
		return false;
	}
	
	public boolean waitForToBeNotEnabled() {
		int timeout = 10;
		return waitForToBeNotEnabled(timeout);
	}

	public boolean waitForToBeNotEnabled(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout)
				break;
			try {
				if (!isEnabled())
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}	
		return false;
	}
	
	public boolean waitForToBeNotEditable() {
		int timeout = 10;
		return waitForToBeNotEditable(timeout);
	}
	
	public boolean waitForToBeNotEditable(int timeout) {
		for (int second = 0; ; second++) {
			if (second >= timeout)
				break;
			try {
				if (!isEditable())
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			WaitUtils.timeout(1, TimeUnit.SECONDS);
		}	
		return false;
	}
}

package com.firstutility.taf.core.ui.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;

import com.firstutility.taf.core.ui.EditableElement;

public class Checkbox extends EditableElement {
	
	private static final Logger log = Logger.getLogger(Checkbox.class);

	public Checkbox(String locator) {
		super(locator);
	}

	public Checkbox(String locator, String name) {
		super(locator, name);
	}

	/**
	 * Checks that Checkbox is checked or not
	 * @return current Checkbox state - true/false
	 */
	public boolean isChecked() {
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
	 * Sets Checkbox into checked state
	 */
	public void setChecked() {
		try {
			if ( isEnabled() ) {
				if ( !isChecked() ) {
					click();
				}
			}
		} catch (RuntimeException e) {
			log.error(e);
			throw new InvalidElementStateException("Checkbox is disabled. You can NOT set it.");
		}
	}

	/**
	 * Sets Checkbox into unchecked state
	 */
	public void setUnchecked() {
		try {
			if ( isEnabled() ) {
				if ( isChecked() ) {
					click();
				}
			}
		} catch (RuntimeException e) {
			log.error(e);
			throw new InvalidElementStateException("Checkbox is disabled. You can NOT set it.");
		}
	}

	/**
	 * Sets Checkbox into provided state
	 * @param state boolean - true/false (checked/unchecked)
	 */
	public void setTo(boolean state) {
		if (state) {
			setChecked();
		} else {
			setUnchecked();
		}
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

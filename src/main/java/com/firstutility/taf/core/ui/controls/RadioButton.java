package com.firstutility.taf.core.ui.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;

import com.firstutility.taf.core.ui.EditableElement;

public class RadioButton extends EditableElement {
	
	private static final Logger log = Logger.getLogger(RadioButton.class);

	public RadioButton(String locator){
		super(locator);
	}
	
	/**
	 * Checks that RadioButton is selected or not
	 * @return current RadioButton state - true/false
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
	
	public void select() {
		try {
			if ( isEnabled() ) {
				if ( !isSelected() ) {
					click();
				}
			}
	    } catch (RuntimeException e) {
	    	log.error(e);
	    	throw new InvalidElementStateException("RadioButton is disabled. You can NOT set it.");
	    }
	}

	@Override
	public RadioButton setLocatorStringVariable(String stringVariable) {
		return new RadioButton(String.format(getLocator(), stringVariable));
	}

	@Override
	public RadioButton setLocatorStringVariables(Object... stringVariables) {
		return new RadioButton(String.format(getLocator(), stringVariables));
	}
}

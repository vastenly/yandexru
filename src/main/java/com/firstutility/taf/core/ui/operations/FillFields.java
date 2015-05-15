package com.firstutility.taf.core.ui.operations;

import java.util.Map;
import java.util.Map.Entry;

import com.firstutility.taf.core.ui.Element;
import com.firstutility.taf.core.ui.controls.Checkbox;
import com.firstutility.taf.core.ui.controls.Input;
import com.firstutility.taf.core.ui.controls.Select;

public class FillFields {
	
	private Map<Element, Object> dataSet;
	
	public FillFields(Map<Element, Object> dataSet) {
		this.dataSet = dataSet;
	}

	public void fillFields() {
		for (Entry<Element, Object> entry : dataSet.entrySet()) {		
			if (entry.getKey() instanceof Input) {
				((Input) entry.getKey()).clear();
				if (entry.getValue() instanceof Integer) {
					((Input) entry.getKey()).setValue(String.valueOf(entry.getValue()));
				}
				if (entry.getValue() instanceof Double) {
					((Input) entry.getKey()).setValue(String.valueOf(entry.getValue()));
				}
				if (entry.getValue() instanceof String) {
					((Input) entry.getKey()).setValue((String) entry.getValue());
				}
			}
			if (entry.getKey() instanceof Select) {
				if (entry.getValue() instanceof Integer) {
					((Select) entry.getKey()).selectOption(String.valueOf(entry.getValue()));
				}
				if (entry.getValue() instanceof String) {
					((Select) entry.getKey()).selectOption((String) entry.getValue());
				}
			}
			if (entry.getKey() instanceof Checkbox) {
				if (entry.getValue() instanceof Boolean) {
					((Checkbox) entry.getKey()).setTo((Boolean) entry.getValue());
				}
			}
		}
	}
}

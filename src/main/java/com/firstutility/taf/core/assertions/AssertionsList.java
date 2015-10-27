package com.firstutility.taf.core.assertions;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class AssertionsList extends Assert {
	
	private static final Logger log = Logger.getLogger(AssertionsList.class);
	
	protected boolean isValidStatement = true;
	
	protected Object checkIf(boolean isValidStatement, Object obj) {
		if (isValidStatement) {
			this.isValidStatement = true;
		}
		this.isValidStatement = false;
		return obj;
	}
	
}

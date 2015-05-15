package com.firstutility.taf.core.selenium.wd;

import org.openqa.selenium.By;

public class LocatorHandler {
	
    public enum LocatorType 
    {
    	XPATH,
        CLASS_NAME,
        CSS_SELECTOR,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
        ID,
        NAME,
        TAG_NAME,
        DOM
    }

    private final LocatorType getLocatorType(String locator) throws NoSuchMethodException {
        if (locator.startsWith("xpath=") || locator.startsWith("//") || locator.startsWith("/html")) {
            return LocatorType.XPATH;
        } else if (locator.startsWith("css=") || locator.startsWith("#") || locator.startsWith(".")) {
            return LocatorType.CSS_SELECTOR;
        } else if (locator.startsWith("id=")) {
            return LocatorType.ID;
        } else if (locator.startsWith("name=")) {
            return LocatorType.NAME;
        } else if (locator.startsWith("class=")) {
            return LocatorType.CLASS_NAME;
        } else if (locator.startsWith("tag=")) {
            return LocatorType.TAG_NAME;
        } else if (locator.startsWith("link=")) {
            return LocatorType.LINK_TEXT;
        } else if (locator.startsWith("dom")) {
            return LocatorType.DOM;
        } else {
        	throw new NoSuchMethodException("Equivalent selenium By.* method for [" +locator+ "] locator NOT found!");
        }
    }
    
    public By getByType(String locator) {
        try {
			switch (getLocatorType(locator)) {
			    case XPATH:
			        if (locator.startsWith("xpath=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.xpath(locator);
			    case CSS_SELECTOR:
			        if (locator.startsWith("css=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.cssSelector(locator);
			    case ID:
			        if (locator.startsWith("id=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.id(locator);
			    case NAME:
			        if (locator.startsWith("name=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.name(locator);
			    case CLASS_NAME:
			        if (locator.startsWith("class=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.className(locator);
			    case TAG_NAME:
			        if (locator.startsWith("tag=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.tagName(locator);
			    case LINK_TEXT:
			        if (locator.startsWith("link=")) {
			            locator = locator.split("=", 2)[1];
			        }
			        return By.linkText(locator);
			    default:
			        if (locator.startsWith("dom:name=")) {
			            locator = locator.split("=", 2)[1];
			            return By.xpath("//form[@name='" + locator + "']");
			        } else if (locator.startsWith("dom:index=")) {
			            locator = locator.split("=", 2)[1];
			            return By.xpath("(//form)[" + locator + "]");
			        }
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
        return null; 
    }

}

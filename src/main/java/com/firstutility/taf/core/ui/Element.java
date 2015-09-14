package com.firstutility.taf.core.ui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.firstutility.taf.core.selenium.wd.LocatorHandler;
import com.firstutility.taf.core.selenium.wd.TestRunner;
import com.firstutility.taf.core.ui.exceptions.ElementWaitTimeoutException;

public class Element extends TestRunner {
	
	private static final Logger log = Logger.getLogger(Element.class);
	
	protected LocatorHandler lh = new LocatorHandler();
	
	protected String locator;
	protected String name;
	
	public Element(String locator) {
		super();
		this.locator = locator;
	}

	public Element(String locator, String name) {
		this.locator = locator;
		this.name = name;
	}
	
	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	public String getLocator() {
		return locator;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public String getName() {
	    return name;
	}
	
	public boolean isPresent() {
		try {
			if (driver.findElements(lh.getByType(locator)).size() > 0) {
				return true;
			}
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return false;
	}

	public boolean isVisible() {
		try {
			if (driver.findElement(lh.getByType(locator)).isDisplayed()) {
				return true;
			}
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return false;
	}

	public boolean isDisplayed() {
	    return isPresent() && isVisible();
	}
	
	public boolean waitForToBePresent() {
		WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(lh.getByType(locator))) != null;
	    } catch (NoSuchElementException e) {
	        return false;
	    } catch (TimeoutException e) {
	    	log.error(e);
	    	throw new ElementWaitTimeoutException("[Element] Element located by [" +getLocator()+ "] is NOT present after 10 second(s) expectation.");
	    }
	}
	
	public boolean waitForToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(lh.getByType(locator))) != null;
	    } catch (NoSuchElementException e) {
	        return false;
	    } catch (TimeoutException e) {
	    	log.error(e);
	    	throw new ElementWaitTimeoutException("[Element] Element located by [" +getLocator()+ "] is NOT present after " +timeout+ " second(s) expectation.");
	    }
	}
	
	public boolean waitForToBeNotPresent() {
		WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
		return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(lh.getByType(locator))));
	}
	
	public boolean waitForToBeNotPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
		return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(lh.getByType(locator))));
	}
	
	public boolean waitForToBeVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lh.getByType(locator))) != null;
	    } catch (NoSuchElementException e) {
	        return false;
	    } catch (TimeoutException e) {
	    	log.error(e);
	    	throw new ElementWaitTimeoutException("[Element] Element located by [" +getLocator()+ "] is NOT visible after 10 second(s) expectation.");
	    }
	}
		
	public boolean waitForToBeVisible(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lh.getByType(locator))) != null;
	    } catch (NoSuchElementException e) {
	        return false;
	    } catch (TimeoutException e) {
	    	log.error(e);
	    	throw new ElementWaitTimeoutException("[Element] Element located by [" +getLocator()+ "] is NOT visible after " +timeout+ " second(s) expectation.");
	    }
	}
	
	public boolean waitForToBeNotVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(lh.getByType(locator)));
	}
	
	public boolean waitForToBeNotVisible(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(lh.getByType(locator)));
	}
	
	public boolean waitForToBeDisplayed() {
		return waitForToBePresent() && waitForToBeVisible();
	}
	
	public boolean waitForToBeDisplayed(int timeout) {
		return waitForToBePresent(timeout) && waitForToBeVisible(timeout);
	}
	
	public boolean waitForToBeNotDisplayed() {
		return waitForToBeNotPresent() && waitForToBeNotVisible();
	}
	
//1
	
	public void click() {
		if (isDisplayed()) {
			driver.findElement(lh.getByType(locator)).click();
			return;
		}
		throw new IllegalStateException("[Element] Element [" +locator+ "] is NOT present in DOM or is NOT visible!");
	}
	
	public void jsClick() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(lh.getByType(locator)));
	}
	
	public void doubleClick() {
		Actions builder = new Actions(driver);
		builder.doubleClick(driver.findElement(lh.getByType(locator))).build().perform();
	}
	
	public void multipleClick() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (int i=0; i<3; i++) {
			click();
		}
	}
	
	public String getTagName() {
		String value = driver.findElement(lh.getByType(locator)).getTagName();
		return value;
	}
	
	public String getValue() {
		String value = driver.findElement(lh.getByType(locator)).getAttribute("value");
		return value;
	}

	public String getSpecificAttributeValue(final String attribute) {
		String value = driver.findElement(lh.getByType(locator)).getAttribute(attribute);
		return value;
	}

	public String getText() {
		return driver.findElement(lh.getByType(locator)).getText();
	}
	
	public boolean hasText(String text) {
		return getText().equals(text);
	}
	
	public boolean containsText(String text) {
		return getText().contains(text);
	}
	
	public void hideElement(){
		WebElement element = driver.findElement(lh.getByType(locator));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", element);
	}
	
	public int getCount() {
		return driver.findElements(lh.getByType(locator)).size();
	}

	public int getIndexByText(Element element) {
		List<WebElement> elements = driver.findElements(lh.getByType(locator));
		for (int i=0; i<elements.size(); i++) {
			if (elements.get(i).getText().equals(element.getText())) {
				return i+1;
			}
		}
		return 0;
	}
	
	public int getElementsCount() {
		return driver.findElements(lh.getByType(locator)).size();
	}
	
	public List<WebElement> getElements(){
		return driver.findElements(lh.getByType(locator));
	}

    public int getIndexByText(String text) {
        List<WebElement> elements = driver.findElements(lh.getByType(locator));
        for (int i=0; i<elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return i+1;
            }
        }
        return 0;
    }
    
    

//2
	
	/**
	 * @param stringVariable
	 * @return new T(String.format(getLocator(), stringVariable));
	 */
	public Element setLocatorStringVariable(String stringVariable) {
		return new Element(String.format(getLocator(), stringVariable));
	}

	/**
	 * @param stringVariables
	 * @return new T(String.format(getLocator(), ...stringVariables));
	 */
	public Element setLocatorStringVariables(Object... stringVariables) {
		return new Element(String.format(getLocator(), stringVariables));
	}
	
	public static class WaitUtils {
		
		public static void timeout(int timeout, TimeUnit timeUnit) {
			try {
				switch (timeUnit) {
				case MILLISECONDS:
					Thread.sleep(timeout);
					break;
				case SECONDS:
					Thread.sleep(timeout*1000);
					break;
				case MINUTES:
					Thread.sleep(timeout*1000*60);
					break;
				default:
					break;
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Element mouseOver() {
		Locatable hoverItem = (Locatable) driver.findElement(lh.getByType(locator));
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates()); 
		return this;
	}
	
	public void highlight(){
		
	}
	
	public Element mouseHover() {
		Actions action = new Actions(driver);
		Actions hover = action.moveToElement(driver.findElement(lh.getByType(locator)));
		hover.perform();
		return this;
	}
	
	public Element pressKey(Keys keys) {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		keyboard.pressKey(keys);
		return this;
	}
}

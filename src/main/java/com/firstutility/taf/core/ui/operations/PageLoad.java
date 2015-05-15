package com.firstutility.taf.core.ui.operations;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.firstutility.taf.core.annotations.PageLoadIndicator;
import com.firstutility.taf.core.ui.Element;

public class PageLoad extends Assert {
	
	private static final Logger log = Logger.getLogger(PageLoad.class);
	private WebDriver driver;
	private static int timeout;
	
	public PageLoad(WebDriver driver) {
		this.driver = driver;
	}
	
	@SuppressWarnings("static-access")
	public PageLoad(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
	}
	
	public static class By {
		
		public static By indicatedElements(Class<?> pageUiClass) {
			for (Field field : pageUiClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(PageLoadIndicator.class)) {
					field.setAccessible(true);
					Element element = null;
					try {
						log.debug("@PageLoadIndicator [Class: " + field.getDeclaringClass().getName() + " > Element: " + field.getName() +"].");
						element = (Element) field.get(pageUiClass.newInstance());
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (timeout !=0) {
						assertTrue(element.waitForToBeDisplayed(timeout));
					} else {
						assertTrue(element.waitForToBeDisplayed());
					}
					
				}
			}
			return null;
		}
	}
		
	public void noWaitFullLoad() {
		((JavascriptExecutor) driver).executeScript("window.stop();");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
}

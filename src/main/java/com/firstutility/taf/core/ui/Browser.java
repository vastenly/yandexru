package com.firstutility.taf.core.ui;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.firstutility.taf.core.selenium.browser.BrowserType;
import com.firstutility.taf.core.selenium.wd.TestRunner;
import com.firstutility.taf.core.ui.exceptions.BrowserAlertNotFoundException;
import com.firstutility.taf.utils.random.RandomString;

public class Browser extends TestRunner {
	
	public Browser(BrowserType browserType) {
		super(browserType);
	}

	private static final Logger log = Logger.getLogger(Browser.class);
	
	/** 
	 * Browser interaction methods
	 */
	
	public void openUrl(String url) {
		log.info("[Browser] Navigate to page [" + url + "].");
		driver.navigate().to(url);
	}
	
	public void back() {
		log.info("[Browser] Go one page back.");
		driver.navigate().back();
	}
	
	public void forward() {
		log.info("[Browser] Go one page forward.");
		driver.navigate().forward();
	}
	
	public void windowFocus() {
		log.info("[Browser] Switch focus to browser window.");
		driver.switchTo().window(driver.getWindowHandle());
	}
	
	public void windowMaximize() {
		log.info("[Browser] Maximize browser window.");
		driver.manage().window().maximize();
	}
	
	public void switchToNewOpenedWindow() {
		log.info("[Browser] Switch to new opened window.");
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}
	
	private Alert alert;
	
	public void waitForTimeout(int timeout, TimeUnit timeUnit) {
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
	
	public boolean isAlertPresent() {
		try {
			waitForTimeout(10, TimeUnit.SECONDS);
			alert = driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}	
	}
	
	public void closeUnexpectedAlert() {
		if (isAlertPresent()) {
			alert.dismiss();
		}
	}
	
	public String acceptAlert() {
		try {
			// Check the presence of alert
			if(isAlertPresent()){
			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			// if present consume the alert
			log.info("[Browser] Accept browser alert popup.");
			alert.accept();
			driver.switchTo().defaultContent();
			return alertMessage;
			}
			return null;
		} catch (NoAlertPresentException e) {
			// Alert not present
			log.error(e);
			throw new BrowserAlertNotFoundException("[Browser] Expected browser alert/popup NOT found!");
		}
	}
	
	public void captureScreen() {
		byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		File screenShotFile = new File("d:/", "screenshot-" + RandomString.getRandomAlphanumeric(12) + ".png");
		try {
			FileUtils.writeByteArrayToFile(screenShotFile, screenshotBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
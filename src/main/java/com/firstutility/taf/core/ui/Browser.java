package com.firstutility.taf.core.ui;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
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
	
	private Alert alert;
	
	/**
	 * Usage - browser.checkIf(browser.isAlertPresent()).printAlertText(log, LogLevel.INFO).acceptUnexpectedAlert(); 
	 * @param isValidStatement - is*() statement to check
	 * @return Browser instance
	 */
	public Browser checkIf(boolean isValidStatement) {
		return (Browser) checkIf(isValidStatement, this);
	}
	
	//isAlertPresent() -- boolean
	//verifyIsAlertPresent() -- assertTrue(boolean) 
	//ifIsAlertPresent() -- boolean variable + return this;

	/**
	 * Usage - browser.ifIsAlertPresent().printAlertText(log, LogLevel.INFO).acceptUnexpectedAlert(); 
	 * @return Browser instance
	 */
	public Browser ifIsAlertPresent() {
		if (isAlertPresent()) {
			isValidStatement = true;
			return this;
		}
		isValidStatement = false;
		return this;
	}
	
	public Browser printAlertText(Level logLevel) {
		if (!isValidStatement) 
			return this;
		if (logLevel == Level.INFO)
			log.info("[Browser] Browser alert text: "+getAlertText());
		if (logLevel == Level.DEBUG)
			log.debug("[Browser] Browser alert text: "+getAlertText());
		if (logLevel == Level.ERROR)
			log.error("[Browser] Browser alert text: "+getAlertText());
		return this;
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

	public String getAlertText() {
		String alertText = null;
		if (isAlertPresent()) {
			alertText = alert.getText();
		}
		return alertText;
	}
	
	/**
     * Accept currently active modal dialog for this particular driver instance.
     * @return A current Browser instance.
     * @throws BrowserAlertNotFoundException if the dialog cannot be found.
     */
	public Browser acceptAlert() {
		if (!isValidStatement) 
			return this;
		if (isAlertPresent()) {
			log.info("[Browser] Accept browser alert popup.");
			alert.accept();
		} else {
			throw new BrowserAlertNotFoundException("[Browser] Expected browser alert NOT found!");
		}
		return this;
	}
	
	/**
     * Accept currently active modal dialog (if such present) for this particular driver instance.
     * @return A current Browser instance.
     */
	public Browser acceptUnexpectedAlert() {
		if (!isValidStatement) 
			return this;
		if (isAlertPresent()) {
			log.info("[Browser] Accept unexpected browser alert popup.");
			alert.accept();
		}
		return this;
	}
	
	/**
     * Close currently active modal dialog for this particular driver instance.
     * @return A current Browser instance.
     * @throws BrowserAlertNotFoundException if the dialog cannot be found.
     */
	public Browser closeAlert() {
		if (!isValidStatement) 
			return this;
		if (isAlertPresent()) {
			log.info("[Browser] Dismiss browser alert popup.");
			alert.dismiss();
		} else {
			throw new BrowserAlertNotFoundException("[Browser] Expected browser alert NOT found!");
		}
		return this;
	}

	/**
     * Close currently active modal dialog (if such present) for this particular driver instance.
     * @return A current Browser instance.
     */
	public Browser closeUnexpectedAlert() {
		if (!isValidStatement) 
			return this;
		if (isAlertPresent()) {
			log.info("[Browser] Dismiss unexpected browser alert popup.");
			alert.dismiss();
		}
		return this;
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

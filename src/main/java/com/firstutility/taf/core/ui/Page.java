package com.firstutility.taf.core.ui;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;

import com.firstutility.taf.core.selenium.wd.TestRunner;
import com.firstutility.taf.core.ui.exceptions.PageContentNotFoundException;
import com.firstutility.taf.core.ui.operations.PageLoad;
import com.firstutility.taf.core.ui.operations.PageLoad.By;

public class Page extends TestRunner {
	
	private static final Logger log = Logger.getLogger(Page.class);
	
	/** 
	 * Page interaction methods
	 */
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public <T> T getUIControl(Class<T> returnPage) throws InstantiationException, IllegalAccessException {
		return returnPage.newInstance();
	}

    public <T> T getUIControl(String frameName, Class<T> returnPage) throws InstantiationException, IllegalAccessException {
        switchToDefaultContent();
        switchToFrame(frameName);
        return returnPage.newInstance();
    }
	
	public void refresh() {
		log.info("[Page] Refresh page.");
		driver.navigate().refresh();
	}
	
	public boolean containsText(String text) {
		if (driver.getPageSource().contains(text)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForPageToLoad(int timeInSeconds){
		driver.manage().timeouts().pageLoadTimeout(timeInSeconds, TimeUnit.SECONDS);
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
	
	public void selectWindow(String iframeName) {
		driver.switchTo().window(iframeName);
	}

	public void switchToFrame(Element iframe) {
		driver.switchTo().frame(iframe.getLocator());
	}
	
	public void switchToFrame(int iframeIndex) {
		driver.switchTo().frame(iframeIndex);
	}

	public void switchToFrame(String iframeName) {
		log.debug("[Page] Switch interaction to frame [" + iframeName + "].");
		try {
			driver.switchTo().frame(iframeName);
		} catch (NoSuchFrameException e) {
			log.error(e);
			throw new PageContentNotFoundException("[Page] Iframe block with name [" +iframeName+ "] NOT found!");
		}
		
	}
	
	public void switchToDefaultContent() {
		log.debug("[Page] Switch interaction to default content.");
		driver.switchTo().defaultContent();
	}
	
	public void disableFrame(String frameId){
		Element frame = new Element("id=" + frameId);
		if (frame.isDisplayed()) {
			frame.hideElement();
		}
	}
	
	public PageLoad load(By by) {
		return new PageLoad(driver);
	}
	
	public PageLoad load(By by, int timeoutInSeconds) {
		return new PageLoad(driver, timeoutInSeconds);
	}
	
}

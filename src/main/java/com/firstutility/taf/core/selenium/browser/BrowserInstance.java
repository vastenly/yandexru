package com.firstutility.taf.core.selenium.browser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserInstance {
	
	private static ThreadLocal<WebDriver> driver;
	
	@SuppressWarnings("static-access")
	public BrowserInstance(BrowserType browserType) throws IOException {
		try {
			this.driver = getDriver(browserType);
            driver.get().manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
            driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized WebDriver getDriver() {
		return driver.get();
	}
	
	public synchronized ThreadLocal<WebDriver> getDriver(BrowserType browserType) throws URISyntaxException, IOException {
		switch (browserType) {
		case CHROME_WINDOWS:
			return new ThreadLocal<WebDriver>() {
				protected ChromeDriver initialValue() {
					return (ChromeDriver) getChromeDriver(BrowserConstants.CHROME_WINDOWS_PATH);
				}
			}; 
		case REMOTE_CHROME_LINUX:
			try {
				ChromeOptions options = new ChromeOptions();
				options.setBinary(BrowserConstants.CHROME_LINUX_BINARY_PATH);
				options.addArguments(BrowserConstants.CHROME_IGNORE_FLAG_CMD_ARGS);
				final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				final URL url = new URL(BrowserConstants.REMOTE_DRIVER_URL);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				return new ThreadLocal<WebDriver>() {
					protected RemoteWebDriver initialValue() {
						return new RemoteWebDriver(url,capabilities);
					}
				}; 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		case CHROME_LINUX:
			return new ThreadLocal<WebDriver>() {
				protected ChromeDriver initialValue() {
					return (ChromeDriver) getChromeDriver(BrowserConstants.CHROME_LINUX_PATH);
				}
			}; 
		case FIREFOX:
			return new ThreadLocal<WebDriver>() {
				protected FirefoxDriver initialValue() {
					return new FirefoxDriver();
				}
			}; 
		case IE:
			return new ThreadLocal<WebDriver>() {
				protected InternetExplorerDriver initialValue() {
					return (InternetExplorerDriver) getIEDriver(BrowserConstants.IE_PATH);
				}
			};
		case SAFARI:
			return new ThreadLocal<WebDriver>() {
				protected SafariDriver initialValue() {
					return new SafariDriver();
				}
			}; 
		default:
			return new ThreadLocal<WebDriver>() {
				protected FirefoxDriver initialValue() {
					return new FirefoxDriver();
				}
			}; 
		}
	}

/*	private synchronized WebDriver getPhantomJSDriver(String driverPath) {
		File phantomJsFile = new File(System.getProperty(BrowserConstants.USER_DIR_PROPERTY) + File.separator + driverPath);
		PhantomJSDriverService service = new PhantomJSDriverService.Builder().usingPhantomJSExecutable(phantomJsFile).usingAnyFreePort().build();
		return new PhantomJSDriver(service, new DesiredCapabilities());
	}*/

	private synchronized WebDriver getChromeDriver(String driverPath) {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
	
		ChromeOptions options = new ChromeOptions();
		options.addArguments(BrowserConstants.CHROME_IGNORE_FLAG_CMD_ARGS);
				
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		if(driverPath.equals(BrowserConstants.CHROME_WINDOWS_PATH)) {
			capabilities.setCapability(BrowserConstants.CHROME_DRIVER_BINARY_PROPERTY, driverPath);
		}  else {
			options.setBinary(BrowserConstants.CHROME_LINUX_BINARY_PATH);
			options.addArguments(BrowserConstants.CHROME_WORK_PROFILE + RandomStringUtils.randomAlphabetic(12));
		}
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);
	}

	private synchronized WebDriver getIEDriver(String driverPath) {
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,driverPath);
		return new InternetExplorerDriver(InternetExplorerDriverService.createDefaultService());
	}

	public synchronized void close() {
		driver.get().quit();
		driver.remove();
	}

}

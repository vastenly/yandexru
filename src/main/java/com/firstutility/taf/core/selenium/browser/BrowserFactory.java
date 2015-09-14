package com.firstutility.taf.core.selenium.browser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BrowserFactory {
	
	private volatile Set<BrowserInstance> browserPool;
	public static ThreadLocal<BrowserInstance> browserInstance;
	private BrowserType browserType;
	
	public BrowserFactory(BrowserType browserType) {
		this.browserType = browserType;
		initCurrentBrowser();
	}

	private synchronized void initCurrentBrowser() {
		synchronized (this) {
			final Set<BrowserInstance> localBrowserPool = new HashSet<BrowserInstance>();
			browserPool = localBrowserPool;
			browserInstance = new ThreadLocal<BrowserInstance>() {
				@Override
				protected BrowserInstance initialValue() {
					BrowserInstance browser = null;
					try {
						browser = new BrowserInstance(browserType);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					localBrowserPool.add(browser);
					return browser;
				}
			};
		}
	}

    public void close() {
        for (BrowserInstance browser : browserPool) {
            browser.close();
        }
    }

	public synchronized BrowserInstance getCurrentBrowserInstance() {
		return browserInstance.get();
	}
}
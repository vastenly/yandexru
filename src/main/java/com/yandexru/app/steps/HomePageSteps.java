package com.yandexru.app.steps;

import com.yandexru.app.ui.HomePageUi;


public class HomePageSteps {
	
	private final HomePageUi ui = new HomePageUi();

	public HomePageSteps () {		
	}
	
	public MarketPageSteps goToMarket(){
    	ui.marketLink.waitForToBeVisible();
    	ui.marketLink.click();
    	return new MarketPageSteps();
    }

    

}

package com.yandexru.app.steps;

import java.awt.AWTException;
import java.awt.Robot;

import org.apache.log4j.Logger;

import com.yandexru.app.ui.ExtSearchPageUi;
import com.yandexru.app.ui.ExtSearchPageUi.Firm;
import com.yandexru.app.ui.ExtSearchPageUi.PriceRange;


public class ExtSearchPageSteps {
	
	private final ExtSearchPageUi ui = new ExtSearchPageUi();
	protected static final Logger log = Logger.getLogger(ExtSearchPageSteps.class);
	
	public ExtSearchPageSteps () {	
	}
	
    public ExtSearchPageSteps inputPriceMin(int priceMin){
    	ui.searchOkButton.waitForToBeDisplayed();
    	ui.searchByPricePrototype.setLocatorStringVariable(PriceRange.ОТ.getFieldType()).setValue(priceMin);
    	return this;
    }
    
    public ExtSearchPageSteps selectFirm(Firm firm){
    	ui.searchOkButton.waitForToBeDisplayed();
    	ui.searchByFirmPrototype.setLocatorStringVariable(firm.getTitle()).click();
    	return this;
   	}
    
    public void clickOkButton(){
    	ui.searchOkButton.waitForToBeDisplayed();
    	ui.searchOkButton.click();
   	}
    
    public void clickHeadSearchButton(){

    	ui.searchHeaderButton.jsClick();
   	}
    
    public int getElementsQuantityOnThePage(){
    	ui.cardContent.waitForToBeDisplayed();
    	return ui.cardContent.getCount();
   	}

    public String getCardTitle(){
    	ui.cardTitle.waitForToBeDisplayed();
    	return ui.cardTitle.getText();
   	}
    
    public ExtSearchPageSteps inputValueToHeadSearchField(String item){
    	ui.headerSearch.waitForToBeDisplayed();
    	ui.headerSearch.setValue(item);
    	return this;
   	}
    
    public boolean verifyIsHeadSearchResultEqualToCardTitle(String item){
    	ui.headerSearchResult.waitForToBeDisplayed();
    	return ui.headerSearchResult.getSpecificAttributeValue("title").equalsIgnoreCase(item);    	
   	}
 
}

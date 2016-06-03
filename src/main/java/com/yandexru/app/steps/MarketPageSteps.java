package com.yandexru.app.steps;

import org.apache.log4j.Logger;

import com.yandexru.app.ui.MaketPageUi;
import com.yandexru.app.ui.MaketPageUi.Category;
import com.yandexru.app.ui.MaketPageUi.SubCategory;

public class MarketPageSteps {
	
	private final MaketPageUi ui = new MaketPageUi();
	protected static final Logger log = Logger.getLogger(MarketPageSteps.class);
	
	public MarketPageSteps () {	
	}
	
    public void verifyMarketPageLoaded(){
    	ui.marketSearch.waitForToBePresent();	
    }

    public void selectCategory(Category category){
   	 	ui.categoryLinkPrototype.setLocatorStringVariable(category.getTitle()).waitForToBeDisplayed();
   	 	getSubMenu(category);   	 	
   	}
    
    public void getSubMenu(Category category){
    	ui.categoryLinkPrototype.setLocatorStringVariable(category.getTitle()).mouseHover();  	 	
   	 	if (!ui.subMenu.isVisible())
   	 		ui.categoryLinkPrototype.setLocatorStringVariable(category.getTitle()).mouseHover();   	 	
   	}
    
    public void selectSubCategory(SubCategory subCategory){
    	ui.subCategoryLinkPrototype.setLocatorStringVariable(subCategory.getTitle()).waitForToBeDisplayed();
    	ui.subCategoryLinkPrototype.setLocatorStringVariable(subCategory.getTitle()).click();
   	}
    
    public ExtSearchPageSteps goToExtendedSearch(){
    	ui.extSearchLink.waitForToBeDisplayed();
    	ui.extSearchLink.click();
    	return new ExtSearchPageSteps();
   	}

}

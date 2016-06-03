package com.yandexru.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.godeltech.taf.utils.prop.ConfigProperties;
import com.yandexru.app.test.BaseTest;
import com.yandexru.app.ui.ExtSearchPageUi.Firm;
import com.yandexru.app.ui.MaketPageUi.Category;
import com.yandexru.app.ui.MaketPageUi.SubCategory;

public class N01Test extends BaseTest {
	
	@Test 
	public void scenario_1() {
		log.info("[Step] Open market page");
		app.homePage().goToMarket().verifyMarketPageLoaded();
		log.info("[Step] Select category");
		app.marketPage().selectCategory(Category.ЭЛЕКТРОНИКА);
		log.info("[Step] Select subCategory");
		app.marketPage().selectSubCategory(SubCategory.ТЕЛЕВИЗОРЫ);
		log.info("[Step] goToExtendedSearch");
		app.marketPage().goToExtendedSearch();
		log.info("[Step] inputValues");
		app.extSearchPage().inputPriceMin(20000).selectFirm(Firm.SAMSUNG).selectFirm(Firm.LG);
		log.info("[Step] clickOkButton");
		app.extSearchPage().clickOkButton();
		browser.waitForTimeout(Integer.parseInt(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
		log.info("[Step] getElementsQuantityOnThePage");
		int elCount = app.extSearchPage().getElementsQuantityOnThePage();
		log.info("[Step] verify el Count: "+elCount);
		Assert.assertTrue(elCount==10);		
		log.info("[Step] getCardTitle");
		String cardTitle = app.extSearchPage().getCardTitle();
		log.info("[Step] cardTitle: "+cardTitle);		
		app.extSearchPage().inputValueToHeadSearchField(cardTitle);
		browser.waitForTimeout(Integer.parseInt(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
		app.extSearchPage().clickHeadSearchButton();
		browser.waitForTimeout(Integer.parseInt(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
		Assert.assertTrue(app.extSearchPage().verifyIsHeadSearchResultEqualToCardTitle(cardTitle));
	}
}
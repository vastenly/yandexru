package com.yandexru.app;

import com.yandexru.app.steps.ExtSearchPageSteps;
import com.yandexru.app.steps.MarketPageSteps;
import com.yandexru.app.steps.HomePageSteps;

public class Application {

	public final HomePageSteps homePage() { return new HomePageSteps(); }
	public final MarketPageSteps marketPage() { return new MarketPageSteps(); }
	public final ExtSearchPageSteps extSearchPage() { return new ExtSearchPageSteps(); }
}

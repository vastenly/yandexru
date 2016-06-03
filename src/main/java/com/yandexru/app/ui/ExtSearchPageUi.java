package com.yandexru.app.ui;

import com.godeltech.taf.core.ui.Element;
import com.godeltech.taf.core.ui.controls.Button;
import com.godeltech.taf.core.ui.controls.Checkbox;
import com.godeltech.taf.core.ui.controls.Input;

public class ExtSearchPageUi {

	public final Element searchBlockPrototype = new Element("//span[contains(@class,'title__content') and text()[contains(.,'%s')]]/../../../");
	public final Input searchByPricePrototype = new Input("//span[contains(@sign-title,'%s')]//input");
	public final Checkbox searchByFirmPrototype = new Checkbox("//label[contains(@class,'checkbox') and text()[contains(.,'%s')]]/../span");
	public final Button searchHeaderButton = new Button("//span[contains(text(),'Найти')]/..");
	public final Button searchOkButton = new Button("//span[contains(text(),'Применить')]/..");
	public final Element cardContent = new Element("//div[@class='snippet-card__content']");
	public final Element cardTitle = new Element("//span[contains(@class,'snippet-card__header-text')]");
	public final Input headerSearch = new Input("id=header-search");
	public final Element headerSearchResult = new Element("//div[@class='headline__header']/h1");
	
	
	
	
	public enum PriceRange {
		ОТ ( "от"),
		ДО ( "до");
	
		private String range;
		
		private  PriceRange(String range) {
			this.range = range;
		}
		
		public String getFieldType() {
			return range;
		}
    }
	
	public enum SearchBlock {
		ЦЕНА ( "Цена"),
		ПРОИЗВОДИТЕЛЬ ( "Производитель");
	
		private String block;
		
		private  SearchBlock(String block) {
			this.block = block;
		}
		
		public String getSearchBlock() {
			return block;
		}
    }
	
	public enum Firm {
		SAMSUNG ( "Samsung"),
		LG ( "LG"),
		BEATS ( "Beats");
	
		private String firm;
		
		private  Firm(String firm) {
			this.firm = firm;
		}
		
		public String getTitle() {
			return firm;
		}
    }
	
/*	public enum SearchButton {
		НАЙТИ ( "Найти"),
		ПРИМЕНИТЬ ( "Применить");
	
		private String button;
		
		private  SearchButton(String button) {
			this.button = button;
		}
		
		public String getType() {
			return button;
		}
    }*/
	
}

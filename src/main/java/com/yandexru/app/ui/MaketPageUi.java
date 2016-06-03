package com.yandexru.app.ui;

import com.godeltech.taf.core.ui.Element;
import com.godeltech.taf.core.ui.controls.Link;

public class MaketPageUi {

	public final Element marketSearch = new Element("//a[contains(@class,'nameplate')]/span");
	public final Element categoryLinkPrototype = new Element("//a[contains(@class,'link topmenu__link') and text()[contains(.,'%s')]]");
	public final Element subCategoryLinkPrototype = new Element("//a[contains(@class,'link topmenu__subitem') and text()[contains(.,'%s')]]");
	public final Element subMenu = new Element("//a[contains(@class,'link topmenu__subitem')]");
	public final Link extSearchLink = new Link("link=Расширенный поиск →");

	
	public enum Category {
		ЭЛЕКТРОНИКА ( "Электроника"),
		КОМПЬЮТЕРЫ ( "Компьютеры");
	
		private String сategory;
		
		private  Category(String сategory) {
			this.сategory = сategory;
		}
		
		public String getTitle() {
			return сategory;
		}
    }
		
	public enum SubCategory {
		ТЕЛЕВИЗОРЫ	 ( "Телевизоры"),
		НАУШНИКИ ( "Наушники");
	
		private String сategory;
		
		private  SubCategory(String сategory) {
			this.сategory = сategory;
		}
		
		public String getTitle() {
			return сategory;
		}
    }
	
}

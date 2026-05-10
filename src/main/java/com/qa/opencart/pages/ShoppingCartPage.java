package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class ShoppingCartPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	public ShoppingCartPage(WebDriver driver) {
		driver= this.driver;
		eleutil = new ElementUtil(driver);
	}
	
	

}

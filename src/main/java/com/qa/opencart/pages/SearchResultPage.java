package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	private By productResults = By.cssSelector("div.product-thumb");
	
	public int getProductResultCount() {
		int resultcount = eleutil.waitForElementsVisible(productResults, AppConstants.SHORT_TIME_OUT).size();
		System.out.println("product result count==>"+resultcount);
		return resultcount;
		}
	
	public ProductInfoPage selectTheProduct(String productName) {
		System.out.println("product name:"+productName);
		eleutil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}

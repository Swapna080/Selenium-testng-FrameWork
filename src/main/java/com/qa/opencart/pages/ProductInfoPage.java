package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private Map<String, String> productMap;
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	private By productHeader = By.tagName("h1");
	private By productImage = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By qnt = By.xpath("//input[@name='quantity']");
	private By addToCartBtn = By.cssSelector("button#button-cart");
	private By shopingcartLinkText = By.xpath("//div[@id='product-product']/div[@class='alert alert-success alert-dismissible']");
	private By shopingcartLink = By.xpath("//div[@id='product-product']/div[@class='alert alert-success alert-dismissible']/a[2]");
	
	public String getProductHeader() {
		String header = eleutil.doElementGetText(productHeader);
		System.out.println("product header ==>"+ productHeader);
		return header;
	}
	
	public int getProductImageCount() {
		int imgcount = eleutil.waitForElementsPresence(productImage, AppConstants.SHORT_TIME_OUT).size();
		System.out.println(getProductHeader()+": Images count = "+ imgcount);
		return imgcount;
	}
	
	public Map<String, String> getProductInfo() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new TreeMap<String, String>();
		productMap.put("header", getProductHeader());
		productMap.put("imagesCount", getProductImageCount()+"");
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleutil.waitForElementsPresence(productMetaData, AppConstants.SHORT_TIME_OUT);
		for(WebElement e : metaList) {
			String metaTest = e.getText();
			String meta[] = metaTest.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}	
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleutil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
		String productPrice = priceList.get(0).getText().trim();
		String productExPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("extax", productExPrice);
	}
	
	public void Enterqntity(String val) {
		eleutil.doSendKeys(qnt, val);
	}
	
	public void clickAddtoCartbtn() {
		eleutil.doClick(addToCartBtn);
	}
	
	public String getLinkText() {
		 eleutil.waitForElementsVisible(shopingcartLinkText, AppConstants.SHORT_TIME_OUT);
		return eleutil.doElementGetText(shopingcartLinkText);
	}
	
	public ShoppingCartPage clickShopinglink() {
		eleutil.doClick(shopingcartLink);
		return new ShoppingCartPage(driver);
	}
	
}

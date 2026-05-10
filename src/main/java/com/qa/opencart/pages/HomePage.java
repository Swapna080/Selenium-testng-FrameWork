package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil eleutil;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By header = By.cssSelector("div#content>h2");
	private By searchKeys = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getHomePageTitle() {
		String title = eleutil.waitForTitleContains(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page title==>"+title);
		return title;
	}
	
	public String getHomePageUrl() {
		String url = eleutil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page url==>"+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleutil.doIsElementDisplayed(logoutLink);
	}
	
	public List<String> getHeadersList() {
		List<WebElement> headerList = eleutil.waitForElementsVisible(header, AppConstants.SHORT_TIME_OUT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e:headerList) {
			String text = e.getText();
			headersValList.add(text);
		}
		
		return headersValList;
	}
	
	public void Logout() {
		if(isLogoutLinkExist()) {
			eleutil.doClick(logoutLink);
		}
		
	}
	
	public SearchResultPage doSearch(String searchKey) {
		System.out.println("Search key  :"+searchKeys );
		WebElement searchEle = eleutil.waitForElementVisible(searchKeys, AppConstants.DEFAULT_TIME_OUT);
		eleutil.doSendKeys(searchEle, searchKey);
		eleutil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
}

package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	public CommonsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	private By logo = By.className("img-responsive");
	private By footer = By.xpath("//footer//a");
	
	public boolean isLogoDisplayed() {
		return eleutil.doIsElementDisplayed(logo);
	}
	
	public List<String> getFootersList() {
		List<WebElement> footerList = eleutil.waitForElementsPresence(footer, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Total number of footer:"+footerList.size());
		List<String> footers = new ArrayList<String>();
		for(WebElement e: footerList) {
			String text = e.getText();
			footers.add(text);
		}
		return footers;
	}
	
	public boolean checkFooterLink(String footerLink) {
		return getFootersList().contains(footerLink);
	}
}

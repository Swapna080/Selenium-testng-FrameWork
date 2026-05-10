package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	@Step("get login page title")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page title==>"+title);
		//ChainTestListener.log("login page title==>" + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		
		String url = eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page url==>"+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleutil.doIsElementDisplayed(forgotPwdLink);
	}
	
	public HomePage doLogin(String uname, String pwd) {
		System.out.println("App cred are:==>"+uname+": "+pwd);
		eleutil.waitForElementsVisible(emailId, AppConstants.DEFAULT_TIME_OUT);
		eleutil.doSendKeys(emailId,uname);
		eleutil.doSendKeys(password,pwd.trim());
		
		eleutil.doClick(loginBtn);
		return new HomePage(driver);
	}
}

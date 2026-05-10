package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic: 100: design login page of open cart")
@Story("US 101: design the various feature of open cart login page")
@Feature("Feature 50: Login Page Feature")
public class LoginPageTest extends BaseTest{
	
	@Description("Checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginpageTitleTest() {
	//	ChainTestListener.log("Verifying Login Page title");
		String actTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Checking login page url....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginpageUrlTest() {
		String actUrl= loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Description("Checking lorgor pwd link....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Description("Checking user is able to login with right credentials....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "checking logo on home page")
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(), AppError.LOGO_NOT_FOUND_ERROR);
	}
	
	@DataProvider
	public Object[][] getFooterdata() {
		return new Object[][] {
			{"About Us"}, {"Brands"},{"Contact Us"}
		};
	}
	
	@Test(dataProvider ="getFooterdata", description= "checking important footer links on home page")
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerLink));
	}
}

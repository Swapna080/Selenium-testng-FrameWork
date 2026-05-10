package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest{

	@BeforeClass
	public void homepageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void homePageTitleTest() {
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void homePageUrlTest() {
		Assert.assertTrue(homepage.getHomePageUrl().contains(AppConstants.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homepage.isLogoutLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Test
	public void headerTest() {
		List<String>actualHeader = homepage.getHeadersList();
		System.out.println("Home page header is: ==>"+actualHeader);
		
	}
	
	@DataProvider()
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook",3}, {"imac", 1}, {"canon", 1}, {"airtel",0}, {"samsung", 2}
		};
	}
	
	
	@Test(priority=1, dataProvider ="getSearchData")
	public void SearchTest(String searchkey, int resultcount) {
		searchResultpage= homepage.doSearch(searchkey);
		Assert.assertEquals(searchResultpage.getProductResultCount(), resultcount);
	}
	
	@Test(description = "checking logo on home page", enabled=false)
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(), AppError.LOGO_NOT_FOUND_ERROR);
	}
	
	@DataProvider
	public Object[][] getFooterdata() {
		return new Object[][] {
			{"About Us"}, {"Brands"},{"Contact Us"}
		};
	}
	
	@Test(dataProvider ="getFooterdata", description= "checking important footer links on home page", enabled= false)
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerLink));
	}
}

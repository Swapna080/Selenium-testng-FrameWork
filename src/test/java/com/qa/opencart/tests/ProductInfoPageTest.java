package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void productinfosetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductHeaderData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
		};
	}
	
	@Test(dataProvider = "getProductHeaderData")
	public void productSearchHeaderTest(String searchkey, String productname) {
		searchResultpage= homepage.doSearch(searchkey);
		productinfopage = searchResultpage.selectTheProduct(productname);
		String actProductHeader = productinfopage.getProductHeader();
		Assert.assertEquals(actProductHeader, productname);
	}
	
//	@DataProvider
//	public Object[][] getProductImageData() {
//		return new Object[][] {
//			{"macbook", "MacBook Pro", 4},
//			{"macbook", "MacBook Air", 4},
//			{"imac", "iMac", 3},
//			{"samsung", "Samsung Galaxy Tab 10.1", 7}
//		};
//	}
	
	@DataProvider
	public Object[][] getProductImageSheetData() {
		Object productdata[][]= ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return productdata;
	}
	
	@Test(dataProvider = "getProductImageSheetData")
	public void productImageCountTest(String searchkey, String productname, String expectedImagecount) {
		searchResultpage= homepage.doSearch(searchkey);
		productinfopage = searchResultpage.selectTheProduct(productname);
		int actProductImageCount = productinfopage.getProductImageCount();
		Assert.assertEquals(actProductImageCount, Integer.parseInt(expectedImagecount));
	}

	@Test
	public void productInfoTest() {
		searchResultpage= homepage.doSearch("macbook");
		productinfopage = searchResultpage.selectTheProduct("MacBook Pro");
		Map<String, String> productInfoMap = productinfopage.getProductInfo();
		productInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("header"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("extax"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@Test
	public void AddProductandGoToShoppingcartpage() {
		searchResultpage= homepage.doSearch("macbook");
		productinfopage = searchResultpage.selectTheProduct("MacBook Pro");
		productinfopage.Enterqntity("2");
		productinfopage.clickAddtoCartbtn();
		Assert.assertTrue(productinfopage.getLinkText().contains(productinfopage.getProductHeader()),"header data is not matching");
		shoppingcartpage= productinfopage.clickShopinglink();
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
	
	@Test(dataProvider ="getFooterdata", description= "checking important footer links on home page", enabled=false)
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerLink));
	}
	
}

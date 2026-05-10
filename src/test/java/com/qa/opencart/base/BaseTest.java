package com.qa.opencart.base;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import io.qameta.allure.Allure;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.pages.ShoppingCartPage;

//@Listeners(ChainTestListener.class)
@Listeners(TestAllureListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultPage searchResultpage;
	protected ProductInfoPage productinfopage;
	protected ShoppingCartPage shoppingcartpage;
	protected CommonsPage commonsPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);

		// ChainTest initializes when @Listeners(ChainTestListener.class) is active.
		ChainPluginService chain = ChainPluginService.getInstance();
		if (chain != null) {
			chain.addSystemInfo("Build#", "1.0");
			chain.addSystemInfo("headless", prop.getProperty("headless", ""));
			chain.addSystemInfo("browser", prop.getProperty("browser", ""));
			chain.addSystemInfo("os", System.getProperty("os.name"));
		}
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			// Attach here (not in ITestListener.onTestFailure): AllureTestNg stops/writes the test
			// case in its onTestFailure before other listeners; @AfterMethod runs under Allure's fixture.
			WebDriver d = DriverFactory.getDriver();
			if (d != null) {
				byte[] png = DriverFactory.getScreenshotByte();
				Allure.addAttachment("Failure screenshot", "image/png", new ByteArrayInputStream(png), "png");
				if (ChainPluginService.getInstance() != null) {
					ChainTestListener.embed(png, "image/png");
				}
			}
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

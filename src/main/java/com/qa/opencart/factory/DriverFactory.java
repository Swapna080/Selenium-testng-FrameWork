package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
//import org.slf4j.Logger;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager opt;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = Logger.getLogger(DriverFactory.class);
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		highlight= prop.getProperty("highlight");
		
		opt = new OptionsManager(prop);
		System.out.println("browser name is :"+ browserName);
		switch(browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(opt.getchromeOptions()));
			//driver = new ChromeDriver(opt.getchromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(opt.getfirefoxOptions()));
			//driver = new FirefoxDriver(opt.getfirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			//driver = new EdgeDriver();
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			break;	
		default:
			System.out.println("Plz pass the valid browser name..");
			throw new FrameworkException("== Invalid browser name==");
			
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	//supply env name using maven command line
	//mvn clean install -Denv="qa"
	public Properties initProp() {
		
		String envName = System.getProperty("env");
		System.out.println("running test suit on env:"+envName);
		FileInputStream ip = null;
		prop= new Properties();
		try {
		if(envName==null) {
			System.out.println("no env is passed, hence running test suit on qa env..");
			ip= new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
		} 
		else {
			switch(envName.trim().toLowerCase()) {
			case"qa":
				ip= new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
				break;
			case "stg":
				ip= new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
				break;
			case "prod":
				ip= new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
				break;	
			default: 
				throw new FrameworkException("==invalid environment==");
					
			}
		}
		prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
		
		
		
//		prop= new Properties();
//		try {
//			FileInputStream ip = new FileInputStream(AppConstants.CONFIG_PROP_FILE_PATH);
//				prop.load(ip);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		return prop;
		
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		

		return srcFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir
		
	}
	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir
		
	}
}

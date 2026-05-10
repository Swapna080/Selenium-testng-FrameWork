package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Properties;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions getchromeOptions() {
		co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("== Running in Headless Mode ==");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("== Running in Incognito Mode ==");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getfirefoxOptions() {
		fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("== Running in Headless Mode ==");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incogito"))) {
			System.out.println("== Running in Incognito Mode ==");
			fo.addArguments("--incognito");
		}
		return fo;
	}
	

}

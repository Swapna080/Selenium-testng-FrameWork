package com.qa.opencart.constants;

public class AppConstants {

	public final static int DEFAULT_TIME_OUT = 5;
	public final static int SHORT_TIME_OUT = 10;
	public final static int MEDIUM_TIME_OUT = 15;
	public final static int MAX_TIME_OUT = 20;
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	
	public final static String HOME_PAGE_TITLE = "My Account";
	public final static String HOME_PAGE_URL_FRACTION = "route=account/account";
	
	public static final String CONFIG_PROD_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/config/Qa.config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/config/Prod.config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/config/Stg.config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/config/uat.config.properties";
	
	
	//*********sheet names*******//
	public static final String PRODUCT_SHEET_NAME = "products";
	public static final String CONFIG_PROP_FILE_PATH = ".\\src\\test\\resources\\config\\config.properties";

}

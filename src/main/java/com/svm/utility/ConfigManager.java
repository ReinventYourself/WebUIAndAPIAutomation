package com.svm.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	public static Properties prop;
	
	public static void loadconfig()
	{
		prop = new Properties();
		FileInputStream FIS;
		try {
			FIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			prop.load(FIS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void configsetup()
	{

		if (prop == null) {
			throw new NullPointerException("Properties object is not initialized.");
		}
		String browser = prop.getProperty("Browser");
		String env = prop.getProperty("Env");

		if (browser == null || browser.isEmpty()) {
			throw new IllegalArgumentException("Browser property is not specified in the configuration file.");
		}
		
		 if (!browser.equalsIgnoreCase("Chrome") && !browser.equalsIgnoreCase("Firefox") && !browser.equalsIgnoreCase("Edge")) {
	            throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }

		if (env == null || env.isEmpty()) {
			throw new IllegalArgumentException("Environment property is not specified in the configuration file.");
		}
		
		if (!env.equalsIgnoreCase("QA") && !env.equalsIgnoreCase("Staging")  ) {
			throw new IllegalArgumentException("Unsupported Environment: " + env);
		}

		utils.setup(browser, env);
		
	}
	
}

package com.svm.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class utils {

	public static WebDriver driver;

	public static WebDriver setup(String Browser, String Env) {

		if (Browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();

		}

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}

		if (Browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();

		openURL(driver, Env);

		return driver;

	}

	private static WebDriver openURL(WebDriver driver, String Env) {

		if (Env.equalsIgnoreCase("QA")) {
			driver.get("https://svm-pymtadmin-qa-us.azurewebsites.net/#/login");
		}

		if (Env.equalsIgnoreCase("Staging")) {
			driver.get("https://svm-pymtadmin-Staging-us.azurewebsites.net/#/login");
		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		return driver;

	}

	
	public static void teardown()
	{
	 driver.quit();
	}
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	
}

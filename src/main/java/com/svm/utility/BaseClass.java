package com.svm.utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	private boolean initializationSuccessful = false;
	
	@BeforeSuite
	public void initialsetup()
	{
		ConfigManager.loadconfig();
		
	}

	@BeforeMethod
	public void driverinitilization() {
            ConfigManager.configsetup();
			initializationSuccessful = true;

			}
			/*
			 * 
			 * @AfterMethod public void driverclose() { if(initializationSuccessful)
			 * utils.teardown(); }
			 * 
			 */
	
	
	
	
	
	
	
}

package com.svm.utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseClass {

	private boolean initializationSuccessful = false;
	
	
	public BaseClass() {
		ConfigManager.loadconfig();

	  }
	 
	
	
	/*
	 * @BeforeSuite public void initialsetup() { ConfigManager.loadconfig(); }
	 */	
	

	@BeforeMethod
	public void driverinitilization() {
            ConfigManager.intiDriver();
			initializationSuccessful = true;

			}
			
			  
			@AfterMethod
			public void driverclose() {
				if (initializationSuccessful)
					utils.teardown();
			}
	
	
	@AfterSuite
	public void SendEmail()
	{
		if(ConfigManager.sendEmail.equalsIgnoreCase("True"))
		{
	      ConfigManager.SendMailSSLWithAttachment();
				} 
		else
		{
			System.out.println("If you want to send the Test result Email then turn on the setting from the config and run from TestNGXML file");
			
		}
		
		System.out.println("Execution Finish");
		  
    }
	
	
	
	
}

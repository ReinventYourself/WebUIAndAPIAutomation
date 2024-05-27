package com.svm.module;

import org.testng.Assert;

import com.svm.pageobject.LoginPage;

public class Login extends LoginPage {

	public Admindashboard fillLoginDetailsAndClickLogin(String username, String password)
	{
		enterusername(username);
		enterPassword(password);
		ClickOnAdminLogin();
		
		return new Admindashboard();
	}
	
	
public void verifygetLogoimage()
{
	Assert.assertTrue(getLogoimage());
}


public void varifyValidationmessage(String Expectedmsg)
{
String ActualMsg =	getValidationmsg();

Assert.assertEquals(ActualMsg, Expectedmsg);
		
}

	
}

package com.svm.testscript;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.svm.module.Admindashboard;
import com.svm.module.Login;
import com.svm.utility.BaseClass;
import com.svm.utility.ConfigManager;
import com.svm.utility.XLUtility;

public class LoginTest extends BaseClass {
	
	
	Login login = new Login();

	String Username = ConfigManager.excel.getCellData("Login", 1, 0);
	String Password = "qazwsxed123!";
	String Invalidusername ="123";
	String Invalidpassword = "1233";
	String ExpectedMsg = "Invalid credentials - please try again.1";
			
	
	
	
	@Test
	public void VerifyLogoimageavailabilityonLoginPage()
	{
		login.verifygetLogoimage();
		
	}
	
	@Test
	public void VerifyLogin()
	{
	
    Admindashboard dashboard=	login.fillLoginDetailsAndClickLogin(Username, Password);
	dashboard.verifyAdminLoginPageIsOpenUp();	
	}
	
	@Test
	public void VerifyvalidationmessagewithinvalidusernameandPassword()
	{
        login.enterusername(Invalidusername);
        login.enterPassword(Invalidpassword);
        login.ClickOnAdminLogin();
		login.varifyValidationmessage(ExpectedMsg);
	}

}

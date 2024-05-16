package com.svm.testscript;

import org.testng.annotations.Test;

import com.svm.module.Login;
import com.svm.utility.BaseClass;

public class LoginTest extends BaseClass {
	
	
	Login login = new Login();
	
	@Test
	public void VerifyLogin()
	{
		login.fillLoginDetailsAndClickLogin("admi","23!");
		
	}

}

package com.svm.module;

import com.svm.pageobject.LoginPage;

public class Login extends LoginPage {

	public void fillLoginDetailsAndClickLogin(String username, String password)
	{
		enterusername(username);
		enterPassword(password);
		ClickOnAdminLogin();
		
	}
	
	
	
	
}

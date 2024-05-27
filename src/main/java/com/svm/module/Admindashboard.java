package com.svm.module;

import org.testng.Assert;

import com.svm.pageobject.Admindashboardpage;

public class Admindashboard extends Admindashboardpage {

	public void verifyAdminLoginPageIsOpenUp()
	{
		
		Assert.assertTrue(getAdminHomePageTitle());
			
	}
	
	
}

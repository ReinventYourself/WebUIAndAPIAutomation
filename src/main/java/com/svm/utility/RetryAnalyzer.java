package com.svm.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter =0;
	int retry =2;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(counter<retry)
		{
			
			counter++;
			return true;
		}
		else 
		{
		return false;
		}
	}

}

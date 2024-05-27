package com.svm.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ExtentReportManager extends utils implements ITestListener  {

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	public static  int passCount=0;
	public static int failCount =0;
	public static String repName;
	 
	
	public void onStart(ITestContext testContext) {
		
	 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	 repName = "Test-Report-"+timeStamp+".html";
	
	 sparkreporter = new ExtentSparkReporter(".\\reports\\"+repName);
	 sparkreporter.config().setDocumentTitle("SVMAutomationResults");
	 sparkreporter.config().setReportName("SVM Report");
	 sparkreporter.config().setTheme(Theme.DARK);
	
	 extent = new ExtentReports();
	 extent.attachReporter(sparkreporter);
	 extent.setSystemInfo("Application", "SVM Report");
	 extent.setSystemInfo("Operating System", System.getProperty("os.name")); 
	 extent.setSystemInfo("User Name", System.getProperty("user.name"));
	 
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		passCount++;
		
	}
	

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		try{
		String screenshotPath = utils.getScreenshot(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotPath);
		}catch(Exception e) {
			test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
		}
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		 failCount++;
	}
	

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
	}
}

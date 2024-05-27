package com.svm.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.svm.utility.WebElementsAction;

public class LoginPage extends WebElementsAction {

	public By LogoImage = By.xpath("//img[@src='assets/images/SVM-Payments.png']");
	public By UserName_Textbox = By.xpath("//input[@name='username']");
	public By Password_Textbox = By.xpath("//input[@name='password']");
	public By Sign_button = By.xpath("//button[@id ='Submitlogin']");
    public By ADMINUSERNAME_LABEL = By.xpath("//label[text()='Username']");
    public By ADMINPASSWORD_LABEL = By.xpath("//label[text()='Password']");
    public By VALIDATIONMESSAGE_LABEL = By.xpath("//p[text()='Invalid credentials -  please try again.']");
    public By USERLOCKED_ERRORMSG = By.xpath("//p[@class='validator']");
    public By FORGOTUSERNAME = By.xpath("//a[text()='Forgot Username']");
    public By FORGOTPASSWORD = By.xpath("//a[text()='Forgot Password']");


	public boolean getLogoimage() {
		try {

			boolean image = ElementExists(LogoImage, "Logo image");
			return image;
		} catch (Exception e) {
			throw new NoSuchElementException("Unable to locate Logo image" + LogoImage);

		}

	}

	public void enterusername(String username) {
		try {
			WaitForElementToBePresentByLocator(UserName_Textbox).sendKeys(username);
		} catch (Exception e) {

			throw new NoSuchElementException("Element is not visible" + UserName_Textbox);

		}
	}

	public void enterPassword(String Password) {
		try {
			WaitForElementToBePresentByLocator(Password_Textbox).sendKeys(Password);
		} catch (Exception e) {

			throw new NoSuchElementException("Element is not visible" + Password_Textbox);

		}
	}

	public void ClickOnAdminLogin() {
		try {
			WaitForElementToBePresentByLocator(Sign_button).click();
		}

		catch (Exception e) {
			throw new NoSuchElementException("Login Button is not clickable");
		}
	}
	
public String  getValidationmsg()
	{
String actualmesage =WaitForElementToBePresentByLocator(VALIDATIONMESSAGE_LABEL).getText().toString();
return actualmesage;
} 


	

}

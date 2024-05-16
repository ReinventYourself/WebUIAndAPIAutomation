package com.svm.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.svm.utility.WebElementsAction;

public class LoginPage extends WebElementsAction {

public By LogoImage  = By.xpath("//img[@src='assets/images/SVM-Payments.png']");
public By UserName_Textbox =  By.xpath("//input[@name='username']");
public By Password_Textbox =  By.xpath("//input[@name='password']");
public By Sign_button = By.xpath("//button[@id ='Submitlogin']");


public boolean GetLogoimage()
{
try {
	
boolean image  = ElementExists(LogoImage,"Logo image");
return image;
}catch(Exception e )
{
throw new NoSuchElementException("Unable to locate Logo image" + LogoImage); 

}

}


public void enterusername(String username)
{
	try {
	WaitForElementToBePresentByLocator(UserName_Textbox).sendKeys(username);
	} catch (Exception e)
	{
		
		throw new NoSuchElementException("Element is not visible" + UserName_Textbox);
		
	}
}


public void enterPassword(String Password)
{
	try {
	WaitForElementToBePresentByLocator(Password_Textbox).sendKeys(Password);
	} catch (Exception e)
	{
		
		throw new NoSuchElementException("Element is not visible" + Password_Textbox);
		
	}
}


public void ClickOnAdminLogin()
{
    try
    {
        WaitForElementToBePresentByLocator(Sign_button).click();
    }

    catch(Exception e)
    {
        throw new NoSuchElementException("Login Button is not clickable");
    }
}

}

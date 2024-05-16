package com.svm.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementsAction extends utils {

	
	
	
	public boolean ElementExists(By locator, String control)
	{
		return WaitForElementToBePresentByLocator(locator).isDisplayed();
		
	}
	
	
	public WebElement getElement(By Locator)
	{
		
	return driver.findElement(Locator);
	}
	
	public String TextToBePresentInElement(By locator)
    {
        return WaitForElementToBePresentByLocator(locator).getText();
    }
	
	
	
	
	
	protected WebElement WaitForElementToBePresentByLocator(By locator) {
	 
	       WebElement element = null;
	       WebDriverWait wait = null; 
	
	  try {
		  
		  wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	      element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	
	  }catch (Exception e){
		  
		  System.out.println("Error occured in locating element" + e.getMessage());
		  
	  } finally 
	  {
		  
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  }

	 return element;
}



public void PressEnterKey(By locator)
{
     WaitForElementToBePresentByLocator(locator).sendKeys(Keys.ENTER);           
}


public void ClearText(By locator)
{
    WaitForElementToBePresentByLocator(locator).clear();;
}

public void SelectValueFromDropdown(By locator, String value)
{
    try
    {
        Select selectElement = new Select(WaitForElementToBePresentByLocator(locator));
        selectElement.selectByValue(value);
    }

    catch (Exception e)
    {
        throw new ElementNotInteractableException("Unable to Select " + locator + "from dropdown");
    }
}

public void SelectFromDropdownByVisbileText(By locator, String value)
{
    try
    {
        Select selectElement = new Select(WaitForElementToBePresentByLocator(locator));
        selectElement.selectByVisibleText(value);
    }

    catch(Exception e)
    {
        throw new ElementNotInteractableException("Unable to Select " + locator + " from dropdown");
    }
}

public void ClickonElementwithAction(By element, String value)
{
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(element)).click().build().perform();
}


public void DoubleClickonElementwithAction(By locator)
{
    Actions action = new Actions(driver);
    action.doubleClick(driver.findElement(locator)).perform();
}


/*
 * public WebElement WaitUntilElementIsClickable(By elementLocator, int timeout)
 * { try { WebDriverWait wait = new WebDriverWait(driver,
 * Duration.ofSeconds(5)); return
 * wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
 * 
 * } catch (NoSuchElementException e) {
 * System.out.println("Element with locator:" + elementLocator);
 * 
 * }
 * 
 * 
 * }
 */

protected void WaitForElementwithmultiplehit(By locator)
{
    boolean clickable = true;

    int count = 0;
    while (clickable && count <= 3)
    {
        try
        {

            driver.findElement(locator).click();
            clickable = false;
            count++;
        }
        catch (WebDriverException e)
        {

            clickable = true;
        }
    }

    if (count == 4)
        throw new ElementNotInteractableException("unable to locate element" + locator);
}
}
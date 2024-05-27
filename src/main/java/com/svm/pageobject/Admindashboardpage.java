package com.svm.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.svm.utility.WebElementsAction;

public class Admindashboardpage extends WebElementsAction {

	 public  By ADMINPAGE_TITLE = By.xpath("//h2[contains(.,'Welcome ')]");
	
	 
	 public boolean getAdminHomePageTitle()
     {
         try
         {
             boolean link = ElementExists(ADMINPAGE_TITLE, "Admin Portal Title");
             return link;
         }

         catch(Exception e)
         {
             throw new NoSuchElementException("Unable to locate admin page Title" + ADMINPAGE_TITLE);
         }
     }
	 
	 
	 
	 
	 
}

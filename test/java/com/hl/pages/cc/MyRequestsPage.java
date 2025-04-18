package com.hl.pages.cc;

import org.openqa.selenium.By;

import com.hl.base.TestBase;
import com.hl.base.TestBase.ExceptionHandling;
import com.hl.utilities.TestUtilities;

public class MyRequestsPage extends TestBase {
	
	public By byToastClose=By.xpath("//*[contains(@class,'p-toast-icon-close')]");
	public void closeToastMessage() {
		//wait.until(ExpectedConditions.presenceOfElementLocated(byToastClose));
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		getWebElement(byToastClose,"Toast Message", ExceptionHandling.WARNING).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public By byNotFoundToastMessage=By.xpath("//*[text()='Not Found']");
	public void isNotFoundToastMessageVisible() {
		if(isElementVisible(byNotFoundToastMessage,"Toast message : Please ensure all required files are uploaded", ExceptionHandling.WARNING)) 
			closeToastMessage();
	}

	By bySearchParameter = By.xpath("//input[@formcontrolname='parameter']");

	public boolean setSearchParameter(String searchParameter) {
		return setTextBoxText(bySearchParameter, searchParameter, "Search Parameter Filter",ExceptionHandling.STOP);
	}
	
	By bySearchButton = By.xpath("//button[@aria-label='Search']");

	public void clickSearchButton() {
		//try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
		clickButton(bySearchButton, "Search");
	}
	
	By byResetButton = By.xpath("//span[text()='Reset Filters']/parent::button");

	public void clickResetButton() {
		//try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
		clickButton(byResetButton, "Reset");
	}
}

package com.hl.pages.hlfoundation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hl.base.TestBase;
import com.hl.utilities.TestUtilities;

public class LandingPage extends TestBase{

	WebDriver webDriver;
	
	public LandingPage(WebDriver driver) {
		webDriver = driver;
	}
	
	public By byUserName=By.xpath("//*[text()='Uchian, Harmik']");
	
	public boolean isWarningMessageVisible(By by,String warningMessage, ExceptionHandling... exceptionHandling) {
		scrollUpDownToElement(webDriver, by, warningMessage,-100);
		if(isElementVisible(by,warningMessage,exceptionHandling)) {
			TestUtilities.logPassWithScreenshot("["+warningMessage+"] is visible");
			return true;
		} else
			return false;
	}
	
	public boolean isNotWarningMessageVisible(By by,String warningMessage, ExceptionHandling... exceptionHandling) {
		if(isNotElementVisible(by,warningMessage,exceptionHandling)) {
			TestUtilities.logPassWithScreenshot("["+warningMessage+"] is not visible");
			return true;
		} else
			return false;
	}

	public String lessThanOneYearMessage="Warning: You do not have access to these benefits until you have reached one year of";
	public By byLessThanOneYearMessage=By.xpath("//*[contains(text(),'"+lessThanOneYearMessage+"')]");

	public boolean isLessThanOneYearMessageVisible() {
		if(isWarningMessageVisible(byLessThanOneYearMessage,lessThanOneYearMessage,TestBase.ExceptionHandling.FAIL)) {
			return true;
		} else
			return false;
	}
	
	public boolean isNotLessThanOneYearMessageVisible() {
		if(isNotWarningMessageVisible(byLessThanOneYearMessage,lessThanOneYearMessage,TestBase.ExceptionHandling.FAIL)) {
			return true;
		} else
			return false;
	}
	
	public String emptyManagementLevelMessage="User staff type is not set.";
	public String emptyManagementLevelURL="https://hlfoundationsapp-test.hl.com/app/access-denied";
	public By byemptyManagementLevel=By.xpath("//*[text()='"+emptyManagementLevelMessage+"']");

	public boolean isEmptyManagementLevelVisible() {
		if(isWarningMessageVisible(byemptyManagementLevel,emptyManagementLevelMessage,TestBase.ExceptionHandling.FAIL)) {
			String strUrl = driver.getCurrentUrl();
			verifyEquals(emptyManagementLevelURL,strUrl);
			return true;
		} else
			return false;
	}
	
	public boolean isNotEmptyManagementLevelVisible() {
		if(isNotWarningMessageVisible(byNotFullTimeMessage,notFullTimeMessage,TestBase.ExceptionHandling.FAIL)) {
			return true;
		} else
			return false;
	}
	
	public String notFullTimeMessage="User is not authorized.";
	public String notFullTimeURL="https://hlfoundationsapp-test.hl.com/app/access-denied";
	public By byNotFullTimeMessage=By.xpath("//*[text()='"+notFullTimeMessage+"']");

	public boolean isFullTimeMessageVisible() {
		if(isWarningMessageVisible(byNotFullTimeMessage,notFullTimeMessage,TestBase.ExceptionHandling.FAIL)) {
			String strUrl = driver.getCurrentUrl();
			verifyEquals(notFullTimeURL,strUrl);
			return true;
		} else
			return false;
	}
	
	public boolean isNotFullTimeMessageVisible() {
		if(isNotWarningMessageVisible(byNotFullTimeMessage,notFullTimeMessage,TestBase.ExceptionHandling.FAIL)) {
			return true;
		} else
			return false;
	}
	
	public String notEmployeeMessage="OK (VwHLStaff (huchian) is not found)";
	public String notEmployeeURL="https://hlfoundationsapp-test.hl.com/app/error";
	public By byEmployeeMessage=By.xpath("//*[text()='"+notEmployeeMessage+"']");
	public boolean isEmployeeMessageVisible() {
		if(isWarningMessageVisible(byEmployeeMessage,notEmployeeMessage,TestBase.ExceptionHandling.FAIL)) {
			String strUrl = driver.getCurrentUrl();
			verifyEquals(notEmployeeURL,strUrl);
			return true;
		} else
			return false;
	}
	
	public boolean isNotEmployeeMessageVisible() {
		if(isNotWarningMessageVisible(byEmployeeMessage,notEmployeeMessage,TestBase.ExceptionHandling.FAIL)) {
			return true;
		} else
			return false;
	}

	public By byCreateNewMatching=By.xpath("//*[text()='Create New Matching Request']");
	public void clickCreateNewMatching() {
		clickButton(byCreateNewMatching,"CreateNewMatching");
	}

	public boolean isCreateNewMatchingEnable(ExceptionHandling...exceptionHandling) {
		scrollUpDownToElement(webDriver, byCreateNewMatching, "CreateNewMatching Button",-300);
		if ( isElementEnabled(byCreateNewMatching,"Create New Matching Button",exceptionHandling)) {
			TestUtilities.logPassWithScreenshot("Create New Matching button is enable");
			return true;
		} else
			return false;
	}

	public By byMenuButton = By.xpath("//*[contains(@class,'p-toolbar-group-right')]");
	public void clickMenuButton() {
		clickElement(byMenuButton, "Menu Button");
	}
	
	By byCreateMatchingRequest=By.xpath("//*[text()='Create Matching Request']");
	public void clickCreateMatchingRequest() {
		clickMenuButton();
		clickElement(byCreateMatchingRequest, "Create Matching Request");
		driver.navigate().refresh();
	}
	
	By byCreateGiveADayRequest=By.xpath("//*[text()='Create Give A Day Request']");
	public void clickCreateGADRequest() {
		clickMenuButton();
		clickElement(byCreateGiveADayRequest, "Create Give A Day Request");
		driver.navigate().refresh();
	}
	
	By bySubmittedRequests=By.xpath("//*[text()='Pending Requests']");
	public void clickSubmittedRequests() {
		clickMenuButton();
		clickElement(bySubmittedRequests, "Submitted Requests");
		driver.navigate().refresh();
	}
	
	By byApprovalQueue=By.xpath("//*[text()='Approval Queue']");
	public void clickApprovalQueue() {
		clickMenuButton();
		clickElement(byApprovalQueue, "Approval Queue");
		driver.navigate().refresh();
	}
	
	
	public By byLogo=By.xpath("//*[@alt='Houlihan Lokey Logo']");
	public void clickLogo() {
		clickElement(byLogo, "Logo");
	}

	public By byToastClose=By.xpath("//*[contains(@class,'p-toast-icon-close')]");
	public void closeToastMessage() {
		//wait.until(ExpectedConditions.presenceOfElementLocated(byToastClose));
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		getWebElement(byToastClose,"Toast Message", ExceptionHandling.WARNING).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
}

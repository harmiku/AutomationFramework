package com.hl.pages.hlfoundation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hl.base.TestBase;
import com.hl.base.TestBase.ExceptionHandling;
import com.hl.utilities.TestUtilities;

public class SubmittedRequestsPage extends TestBase {

	WebDriver webDriver;
	LandingPage landingPage;
	String filePath = excel.getCellData("SubmittedMatchingFiles", "FilePath", 2);
	String fileName="";
	
	public SubmittedRequestsPage(WebDriver driver) {
		webDriver=driver;
		landingPage=new LandingPage(driver);
	}
	
	public By byActionLabel=By.xpath("//*[text()='Action']");

	public By byGiveADayButton=By.xpath("//*[text()='Give-A-Day']");
	public void clickGiveADayButton() {
		scrollUpDownToElement(webDriver, byGiveADayButton, "Give-A-Day Button" );
		clickElement(byGiveADayButton, "Give-A-Day Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byMatchingButton=By.xpath("//*[text()='Matching']");
	public void clickMatchingButton() {
		scrollUpDownToElement(webDriver, byMatchingButton, "Matching Button" );
		clickElement(byMatchingButton, "Matching Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byCreateNewButton=By.xpath("//*[@id='p-tabpanel-1']//button[*[text()='Create New']]");
	public void clickCreateNewButton() {
		scrollUpDownToElement(webDriver, byCreateNewButton, "Create New Button", -200 );
		clickElement(byCreateNewButton, "Create New Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byUpdateButton=By.xpath("//*[@id='p-tabpanel-1']//button[*[text()='Update']]");
	public void clickUpdateButton() {
		scrollUpDownToElement(webDriver, byUpdateButton, "Update Button" );
		clickElement(byUpdateButton, "Update Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public boolean isUpdateButtonDisplayed() {
		scrollUpDownToElement(webDriver, byActionLabel, "Action Label");
		if(isElementVisible(byUpdateButton,"Update Button", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Update Button is displayed");
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot("Update Button is NOT displayed");
			return false;
		}
	}
	public boolean isNotUpdateButtonDisplayed() {
		scrollUpDownToElement(webDriver, byActionLabel, "Action Label");
		if(isNotElementVisible(byUpdateButton,"Update Button", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Update Button is not displayed");
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot("Update Button is displayed");
			return false;
		}
	}

	public By byCancelButton=By.xpath("//*[@id='p-tabpanel-1']//button[*[text()='Cancel']]");
	public void clickCancelButton() {
		scrollUpDownToElement(webDriver, byCancelButton, "Cancel Button" );
		clickElement(byCancelButton, "Cancel Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public boolean isCancelButtonDisplayed() {
		scrollUpDownToElement(webDriver, byActionLabel, "Action Label");
		if(isElementVisible(byCancelButton,"Cancel Button", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Cancel Button is displayed");
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot("Cancel Button is NOT displayed");
			return false;
		}
	}
	public boolean isNotCancelButtonDisplayed() {
		scrollUpDownToElement(webDriver, byActionLabel, "Action Label");
		if(isNotElementVisible(byCancelButton,"Cancel Button", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Cancel Button is not displayed");
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot("Cancel Button is displayed");
			return false;
		}
	}
	
	public By byDateSubmitted=By.xpath("//*[@id='p-tabpanel-1']//tr/td[1]");
	public String getDateSubmitted(ExceptionHandling... exceptionHandling) {
		return getElementText(byDateSubmitted, "Date Submitted", exceptionHandling);
	}
	
	public By byDonationDate =By.xpath("//*[@id='p-tabpanel-1']//tr/td[2]");
	public String getDonationDate(ExceptionHandling... exceptionHandling) {
		return getElementText(byDonationDate, "Donation Date", exceptionHandling);
	}

	public By byCharityName=By.xpath("//*[@id='p-tabpanel-1']//tr/td[3]");
	public String getCharityName(ExceptionHandling... exceptionHandling) {
		return getElementText(byCharityName, "Charity Name", exceptionHandling);
	}

	public By byAmountRequested=By.xpath("//*[@id='p-tabpanel-1']//tr/td[4]");
	public String getAmountRequested(ExceptionHandling... exceptionHandling) {
		return getElementText(byAmountRequested, "Amount Requested", exceptionHandling);
	}

	public By byContactPerson=By.xpath("//*[@id='p-tabpanel-1']//tr/td[5]");
	public String getContactPerson(ExceptionHandling... exceptionHandling) {
		return getElementText(byContactPerson, "Contact Person", exceptionHandling);
	}

	public By byContactPhone=By.xpath("//*[@id='p-tabpanel-1']//tr/td[6]");
	public String getContactPhone(ExceptionHandling... exceptionHandling) {
		return getElementText(byContactPhone, "Contact Phone", exceptionHandling);
	}

	public By byApplicationStatus=By.xpath("//*[@id='p-tabpanel-1']//tr/td[7]");
	public String getApplicationStatus(ExceptionHandling... exceptionHandling) {
		return getElementText(byApplicationStatus, "Application Status", exceptionHandling);
	}

	public void verifyData(String sheetName, String applicationStatus) {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String strDate=sdf.format(currentDate);
		verifyEquals(strDate,getDateSubmitted(ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "DonationDate", 2),getDonationDate(ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityName", 2),getCharityName(ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "MatchingAmount", 2),getAmountRequested(ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityContact", 2),getContactPerson(ExceptionHandling.FAIL),"DisplayEqualMessage"); 
		verifyEquals(excel.getCellData(sheetName, "CharityPhone", 2),getContactPhone(ExceptionHandling.FAIL),"DisplayEqualMessage"); 
		verifyEquals(applicationStatus,getApplicationStatus(ExceptionHandling.FAIL),"DisplayEqualMessage"); 
	}
	
}

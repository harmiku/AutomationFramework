package com.hl.pages.hlfoundation;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hl.base.TestBase;
import com.hl.base.TestBase.ExceptionHandling;

public class ApprovalQueuePage extends TestBase{

	WebDriver webDriver;
	LandingPage landingPage;

	public ApprovalQueuePage(WebDriver driver) {
		webDriver = driver;
		landingPage=new LandingPage(driver);
	}
	
	public By byActiveButton=By.xpath("//*[text()='Active']");
	public void clickActiveButton() {
		scrollUpDownToElement(webDriver, byActiveButton, "Active Button", -200);
		clickElement(byActiveButton, "Active Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byArchivedButton=By.xpath("//*[text()='Archived']");
	public void clickArchivedButton() {
		scrollUpDownToElement(webDriver, byArchivedButton, "Archived Button", -200 );
		clickElement(byArchivedButton, "Archived Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byGlobalFilter=By.xpath("//*[@id='p-tabpanel-0']//*[@placeholder='Global Filter']");
	public void setGlobalFilter(String filterValuew) {
		setTextBoxText(byGlobalFilter, filterValuew, "Global Filter");
	}
	
	public By byReviewButton=By.xpath("//*[text()='Review']");
	public void clickReviewButton() {
		scrollUpDownToElement(webDriver, byReviewButton, "Review Button", -200 );
		clickElement(byReviewButton, "Review Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byType=By.xpath("//*[@id='p-tabpanel-0']//tr/td[1]");
	public String getType(ExceptionHandling... exceptionHandling) {
		return getElementText(byType, "Type", exceptionHandling);
	}
	
	public By byName =By.xpath("//*[@id='p-tabpanel-0']//tr/td[2]");
	public String getName(ExceptionHandling... exceptionHandling) {
		return getElementText(byName, "Name", exceptionHandling);
	}

	public By byOffice=By.xpath("//*[@id='p-tabpanel-0']//tr/td[3]");
	public String getOffice(ExceptionHandling... exceptionHandling) {
		return getElementText(byOffice, "Office", exceptionHandling);
	}

	public By byCharityName=By.xpath("//*[@id='p-tabpanel-0']//tr/td[4]");
	public String getCharityName(ExceptionHandling... exceptionHandling) {
		return getElementText(byCharityName, "Charity Name", exceptionHandling);
	}

	public By byDateSubmitted=By.xpath("//*[@id='p-tabpanel-0']//tr/td[5]");
	public String getDateSubmitted(ExceptionHandling... exceptionHandling) {
		return getElementText(byDateSubmitted, "Date Submitted", exceptionHandling);
	}
	
	public By byDonationDate=By.xpath("//*[@id='p-tabpanel-0']//tr/td[6]");
	public String getDonationDate(ExceptionHandling... exceptionHandling) {
		return getElementText(byDonationDate, "Donation Date", exceptionHandling);
	}

	public By byAmount=By.xpath("//*[@id='p-tabpanel-0']//tr/td[7]");
	public String getAmount(ExceptionHandling... exceptionHandling) {
		return getElementText(byAmount, "Amount", exceptionHandling);
	}

	public void verifyData(String dataSet, String type) {
		String sheetName;
		if(dataSet.equals("New"))
			if(type.equals("Matching"))
				sheetName="NewMatchingRequest";
			else
				sheetName="NewGADRequest";
		else
			if(type.equals("Matching"))
				sheetName="SubmittedMatchingRequest";
			else
				sheetName="SubmittedGADRequest";
		verifyEquals("Matching",getType(ExceptionHandling.FAIL));
		verifyEquals(executeSQLSelectName(),getName(ExceptionHandling.FAIL));
		verifyEquals(executeSQLSelectOfficeLocation(),getOffice(ExceptionHandling.FAIL)); 
		verifyEquals(excel.getCellData(sheetName, "CharityName", 2),getCharityName(ExceptionHandling.FAIL));
		verifyEquals(LocalDateTime.now().toString(),getDateSubmitted(ExceptionHandling.FAIL));
		verifyEquals(excel.getCellData(sheetName, "DonationDate", 2),getDonationDate(ExceptionHandling.FAIL));
		verifyEquals(excel.getCellData(sheetName, "MatchingAmount", 2),getAmount(ExceptionHandling.FAIL)); 
	}

	public By byActionDropDown=By.xpath("//*[text()='Select an Action']");
	public By byApprovedAction=By.xpath("//p-dropdown//*[text()='Approved']");
	public By byRejectedAction=By.xpath("//p-dropdown//*[text()='Rejected']");
	public By byNeedInfoAction=By.xpath("//p-dropdown//*[text()='Need Info']");
	public void selectAction(String action) {
		switch(action){
		case "Approved":selectDropDownOption(byActionDropDown, action, byApprovedAction, "Action");break;
		case "Rejected":selectDropDownOption(byActionDropDown, action, byRejectedAction, "Action");break;
		case "Need Info":selectDropDownOption(byActionDropDown, action, byNeedInfoAction, "Action");break;
		}
	}
	
	public By byTextArea=By.xpath("//textarea");
	public void setTextArea(String text) {
		setTextBoxText(byTextArea, text, "Note");
	}

	public By bySubmitButton=By.xpath("//*[text()='Submit']");
	public void clickSubmitButton() {
		clickElement(bySubmitButton, "Submit Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

}

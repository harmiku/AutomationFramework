package com.hl.pages.hlfoundation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hl.base.TestBase;
import com.hl.base.TestBase.ExceptionHandling;
import com.hl.utilities.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class UpdateMatchingRequestPage extends TestBase {

	WebDriver webDriver;
	LandingPage landingPage;
	String filePath = excel.getCellData("MatchingRequest2Files", "FilePath", 2);
	String fileName="";

	public UpdateMatchingRequestPage(WebDriver driver) {
		webDriver = driver;
		landingPage=new LandingPage(driver);
	}

	public By byViewPolicyButton=By.xpath("//button[text()='View Policy']");
	public void clickViewPolicyButton() {
		scrollUpDownToElement(webDriver, byViewPolicyButton, "View Policy" );
		clickElement(byViewPolicyButton, "View Policy Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byPastRequestsButton=By.xpath("//button[text()='Past Requests']");
	public void clickPastRequestsButton() {
		scrollUpDownToElement(webDriver, byPastRequestsButton, "Past Requests" );
		clickElement(byPastRequestsButton, "Past Requests Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byCancelButton=By.xpath("//button[*[text()='Cancel']]");
	public void clickCancelButton() {
		scrollUpDownToElement(webDriver, byCancelButton, "Cancel Button" );
		clickElement(byCancelButton, "Cancel Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public By byUpdateButton=By.xpath("//button[*[text()='Update form and documents']]");
	public void clickUpdateButton() {
		scrollUpDownToElement(webDriver, byUpdateButton, "Update Button" );
		clickElement(byUpdateButton, "Update Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}

	public boolean isFileNameDisplayed(String fileName) {
		scrollUpDownToElement(webDriver, byRequiredDocumentsLabel, "RequiredDocumentsLabel");
		wait = new WebDriverWait(driver, 45);
		if(isElementVisible(By.xpath("//*[text()=' "+fileName+" ']"),fileName, ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot(fileName+" is displayed");
			wait = new WebDriverWait(driver, 15);
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot(fileName+" is NOT displayed");
			wait = new WebDriverWait(driver, 15);
			return false;
		}
	}

	public By byOver100MBFileValidationMessage=By.xpath("//*[contains(text(),'"+fileName+": Invalid file size')]");
	public void isOver100MBFileValidationMessageVisible() {
		if(isElementVisible(byOver100MBFileValidationMessage,"Over100MBFileValidationMessage", ExceptionHandling.FAIL))  
			TestUtilities.logPassWithScreenshot("Over100MBFileValidationMessage is displayed");
		else 
			TestUtilities.logFailedWithScreenshot("Over100MBFileValidationMessage is NOT displayed");
	}
	public void isNotOver100MBFileValidationMessageVisible() {
		if(isNotElementVisible(byOver100MBFileValidationMessage,"Over100MBFileValidationMessage", ExceptionHandling.FAIL))  
			TestUtilities.logPassWithScreenshot("Over100MBFileValidationMessage is not displayed");
		else 
			TestUtilities.logFailedWithScreenshot("Over100MBFileValidationMessage is displayed");
	}
	
	public By byNonPDFFileValidationMessage=By.xpath("//*[contains(text(),'"+fileName+": Invalid file type')]");
	public void isNonPDFFileValidationMessageVisible() {
		if(isElementVisible(byNonPDFFileValidationMessage,"NonPDFFileValidationMessage", ExceptionHandling.FAIL))  
			TestUtilities.logPassWithScreenshot("NonPDFFileValidationMessage is displayed");
		else 
			TestUtilities.logFailedWithScreenshot("NonPDFFileValidationMessage is NOT displayed");
	}
	public void isNotNonPDFFileValidationMessageVisible() {
		if(isNotElementVisible(byNonPDFFileValidationMessage,"NonPDFFileValidationMessage", ExceptionHandling.FAIL))  
			TestUtilities.logPassWithScreenshot("NonPDFFileValidationMessage is not displayed");
		else 
			TestUtilities.logFailedWithScreenshot("NonPDFFileValidationMessage is displayed");
	}
	
	public By byUploadMonetaryDoc=By.xpath("//span[text()='Monetary contribution evidence']");
	public By byDeleteMonetaryDoc=By.xpath("(//*[text()='Delete'])[1]");
	public void uploadMonetaryDoc(String fileType) {
		scrollUpDownToElement(webDriver, byUploadMonetaryDoc,"Upload Monetary contribution evidence", -200);
		clickElement(byUploadMonetaryDoc,"Upload Monetary contribution evidence");
		switch(fileType) {
		case "ValidFile":fileName = excel.getCellData("MatchingRequest2Files", "MonetaryFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("MatchingRequest2Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("MatchingRequest2Files", "Over100MB", 2);break;
		}
		try {TestUtilities.copyPaste(filePath+fileName);} catch (Exception e) {e.printStackTrace();}
		test.log(LogStatus.INFO, "Select: [" + fileName + "] for Document: [Monetary contribution evidence]");
		if (fileType.equals("ValidFile")) isFileNameDisplayed(fileName);
	}
	public void isMonetaryContributionEvidenceButtonVisible() {
		scrollUpDownToElement(webDriver, byUploadMonetaryDoc,"Upload Monetary contribution evidence", -200);
		if(isElementVisible(byUploadMonetaryDoc,"Upload Monetary contribution evidence", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Monetary contribution evidence Doc is deleted");
		}
		else {
			TestUtilities.logFailedWithScreenshot("Monetary contribution evidence Doc is NOT deleted");
		}
	}
	public void deleteMonetaryDoc() {
		scrollUpDownToElement(webDriver, byDeleteMonetaryDoc,"Delete Monetary Doc");
		clickElement(byDeleteMonetaryDoc,"DeleteMonetaryDoc", ExceptionHandling.FAIL);
	}

	public By byUploadCompletedDoc=By.xpath("//span[text()='Completed HL check request']");
	public By byDeleteCompletedDoc=By.xpath("(//*[text()='Delete'])[2]");
	public void uploadCompletedDoc(String fileType) {
		scrollUpDownToElement(webDriver, byUploadCompletedDoc,"Upload Completed HL check request", -200);
		clickElement(byUploadCompletedDoc,"Upload Completed HL check request");
		switch(fileType) {
		case "ValidFile":fileName = excel.getCellData("MatchingRequest2Files", "CompletedFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("MatchingRequest2Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("MatchingRequest2Files", "Over100MB", 2);break;
		}
		try {TestUtilities.copyPaste(filePath+fileName);} catch (Exception e) {e.printStackTrace();}
		test.log(LogStatus.INFO, "Select: [" + fileName + "] for Document: [Completed HL check request]");
		if (fileType.equals("ValidFile")) isFileNameDisplayed(fileName);
	}
	public void isCompletedHLCheckRequestButtonVisible() {
		scrollUpDownToElement(webDriver, byUploadCompletedDoc,"Upload Completed HL check request", -200);
		if(isElementVisible(byUploadCompletedDoc,"Upload Completed HL check request", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Completed HL check request Doc is deleted");
		}
		else {
			TestUtilities.logFailedWithScreenshot("Completed HL check request Doc is NOT deleted");
		}
	}
	public void deleteCompletedDoc() {
		scrollUpDownToElement(webDriver, byDeleteCompletedDoc,"Delete Completed Doc");
		clickElement(byDeleteCompletedDoc,"DeleteCompletedDoc", ExceptionHandling.FAIL);
	}

	public By byUploadNonprofitDoc=By.xpath("//span[text()='Non-profit status evidence']");
	public By byDeleteNonprofitDoc=By.xpath("(//*[text()='Delete'])[3]");
	public void uploadNonprofitDoc(String fileType) {
		scrollUpDownToElement(webDriver, byUploadNonprofitDoc,"Upload Non-profit status evidence");
		clickElement(byUploadNonprofitDoc,"Upload Non-profit status evidence");
		switch(fileType) {
		case "ValidFile":fileName = excel.getCellData("MatchingRequest2Files", "NonprofitFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("MatchingRequest2Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("MatchingRequest2Files", "Over100MB", 2);break;
		}
		try {TestUtilities.copyPaste(filePath+fileName);} catch (Exception e) {e.printStackTrace();}
		test.log(LogStatus.INFO, "Select: [" + fileName + "] for Document: [Non-profit status evidence]");
		if (fileType.equals("ValidFile")) isFileNameDisplayed(fileName);
	}
	public void isNonprofitStatusEvidenceButtonVisible() {
		scrollUpDownToElement(webDriver, byUploadNonprofitDoc,"Upload Non-profit status evidence", -200);
		if(isElementVisible(byUploadNonprofitDoc,"Upload Non-profit status evidence", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Non-profit status evidence Doc is deleted");
		}
		else {
			TestUtilities.logFailedWithScreenshot("Non-profit status evidence Doc is NOT deleted");
		}
	}
	public void deleteNonprofitDoc() {
		scrollUpDownToElement(webDriver, byDeleteNonprofitDoc,"Delete Nonprofit Doc");
		clickElement(byDeleteNonprofitDoc,"DeleteNonprofitDoc", ExceptionHandling.FAIL);
	}

	public By byRecordUpdatedSuccessfully=By.xpath("//*[text()='Record Updated Successfully']");
	public void isRecordUpdatedSuccessfullyVisible(String... display) {
		if (display.length ==0) {
			if(isElementVisible(byRecordUpdatedSuccessfully,"Toast message : Record Updated Successfully", ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+"Toast message : Record Updated Successfully"+"] is visible");
			}
		}
		landingPage.closeToastMessage();
	}

	public By byFileUploadValidationToastMessage=By.xpath("//*[text()='Please ensure all required files are uploaded']");
	public void isFileUploadValidationToastMessageVisible(String... display) {
		if (display.length ==0) {
			if(isElementVisible(byFileUploadValidationToastMessage,"Toast message : Please ensure all required files are uploaded", ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+"Toast message : Please ensure all required files are uploaded"+"] is visible");
			}
			landingPage.closeToastMessage();
		}
	}

	public void isValidationToastMessageVisible(String toastMessage, String... display) {
		if (display.length ==0) {
			if(isElementVisible(By.xpath("//*[text()='"+toastMessage+"']"),"Toast message : "+toastMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+"Toast message : "+toastMessage+"] is visible");
			}
			landingPage.closeToastMessage();
		}
	}

	public void isFileDeletedToastMessageVisible(String... display) {
		isValidationToastMessageVisible("File successfully deleted");
	}
	
	public void isFileUploadingToastMessageVisible(String... display) {
		isValidationToastMessageVisible("File Uploading");
	}
	
	public void isFileUploadedToastMessageVisible(String... display) {
		isValidationToastMessageVisible("File Uploaded");
	}

	String fileUploadValidationMessage="Please ensure all documents are uploaded before finalizing submission.";
	public By byFileUploadValidationMessage=By.xpath("//*[text()='"+fileUploadValidationMessage+"']");
	public boolean isFileUploadValidationMessageVisible(String... display) {
		scrollUpDownToElement(webDriver, byRequiredDocumentsLabel, "RequiredDocumentsLabel");
		if(isElementVisible(byFileUploadValidationMessage,fileUploadValidationMessage, ExceptionHandling.FAIL)) {
			if (display.length ==0) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+fileUploadValidationMessage+"] is visible");
			}
			return true;
		} else
			return false;
	}
	public boolean isNotFileUploadValidationMessageVisible(String... display) {
		scrollUpDownToElement(webDriver, byRequiredDocumentsLabel, "RequiredDocumentsLabel");
		if(isNotElementVisible(byFileUploadValidationMessage,fileUploadValidationMessage, ExceptionHandling.FAIL)) {
			if (display.length ==0) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+fileUploadValidationMessage+"] is not visible");
			}
			return true;
		} else
			return false;
	}

	public By byRequiredDocumentsLabel=By.xpath("//*[text()='Required Documents']");

	public By byMatchingRequestFormPanel=By.xpath("//*[text()='Matching Request Form']");
	public By byProgressLabel=By.xpath("//*[text()='Progress']");

	String pendingRequestMessage="You have a matching request in progress please come back after it has been approved or denied.";
	public By byPendingRequestMessage = By.xpath("//*[text()='"+pendingRequestMessage+"']");

	public void isPendingRequestMessageVisible(String status) {
		isValidationMessageVisible(byPendingRequestMessage,pendingRequestMessage, status);
	}

	public void isNotPendingRequestMessageVisible(String status) {
		isNotValidationMessageVisible(byPendingRequestMessage,pendingRequestMessage, status);
	}

	String maxedOutMessage="You have maxed out your reimbursement rate.";
	public By byMaxedOutMessage = By.xpath("//*[text()='"+maxedOutMessage+"']");

	public void isMaxedOutMessageVisible(String status) {
		isValidationMessageVisible(byMaxedOutMessage,maxedOutMessage, status, -400);
	}

	public void isNoMaxedOutMessageVisible(String status) {
		isNotValidationMessageVisible(byMaxedOutMessage,maxedOutMessage, status);
	}

	public void isMatchedAllowedMessageVisible(String managementLevelName,String amount) {
		String progressMessage="";
		By byProgressMessage;

		switch (managementLevelName) {
		case "Non-Officers":  
			progressMessage=" $"+amount+" matched out of $1000 allowed for 2023 ";
			break;
		case "SVP, VP, Administrative Officers":  
			progressMessage=" $"+amount+" matched out of $5000 allowed for 2023 ";
			break;
		case "Director":  
			progressMessage=" $"+amount+" matched out of $10000 allowed for 2023 ";
			break;
		case "MD, Sr. MD, C-Level":  
			progressMessage=" $"+amount+" matched out of $20000 allowed for 2023 ";
			break;
		}
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		isValidationMessageVisible(byProgressMessage,progressMessage, managementLevelName,-300);
	}

	public void isValidationMessageVisible(By by, String validationMessage, String status, int...UpDown) {
		if(UpDown.length != 0)	
			scrollUpDownToElement(webDriver, byMatchingRequestFormPanel, "MatchingRequestFormPanel",UpDown[0]);
		else
			scrollUpDownToElement(webDriver, byMatchingRequestFormPanel, "MatchingRequestFormPanel",-200);
		if(isElementVisible(by, validationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("["+validationMessage+"] is visible for: "+status);	
		}
	}

	public void isNotValidationMessageVisible(By by, String validationMessage, String status) {
		scrollUpDownToElement(webDriver, byProgressLabel, "MatchingRequestFormPanel",-200);
		if(isNotElementVisible(by, validationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("["+validationMessage+"] is not visible for: "+status);	
		}
	}

	public By byName= By.xpath("//*[@id='employeeNameInputField']");
	public void ValidateName() {
		scrollUpDownToElement(webDriver, byName, "Name", -200);
		if (isElementDisabled(byName,"Name", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Name is disabled");
			if (executeSQLSelectName().equals(getTextBoxText(byName,"Name", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Name is populated from user’s login credentials");
			else
				TestUtilities.logFailedWithScreenshot("Name is NOT populated from user’s login credentials");
		}
	}

	public By byOfficeLocationEnabled = By.xpath("//*[@id='officeLocationInputField']");
	public void ValidateOfficeLocation() {
		scrollUpDownToElement(webDriver, byOfficeLocationEnabled, "Office Location", -200);
		if (isElementDisabled(byOfficeLocationEnabled,"Office Location", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Office Location is disabled");
			if (executeSQLSelectOfficeLocation().equals(getTextBoxText(byOfficeLocationEnabled,"Office Location", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Office Location is populated from user’s login credentials");
			else		
				TestUtilities.logPassWithScreenshot("Office Location is NOT populated from user’s login credentials");
		}
	}

	public By byTitleEnabled = By.xpath("//*[@id='employeeTitleInputField']");
	public void ValidateTitle() {
		scrollUpDownToElement(webDriver, byTitleEnabled, "Title", -200);
		if (isElementDisabled(byTitleEnabled,"Title", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Title is disabled");
			String expected=executeSQLSelectTitle();
			String actual=getTextBoxText(byTitleEnabled,"Title", ExceptionHandling.FAIL);
			String passMessage="Title is populated from user’s login credentials";
			String failMessage="Title is NOT populated from user’s login credentials";
			if(verifyEquals(expected, actual))
				TestUtilities.logPassWithScreenshot(passMessage);
			else 
				TestUtilities.logFailedWithScreenshot(failMessage);
		}
	}

	public By byDonationAmount=By.id("donationAmountInputField");
	public String donationAmountValidationMessage="Donation amount must be between $1 and $999999 dollars.";
	public By byDonationAmountValidationMessage=By.xpath("//*[text()='"+donationAmountValidationMessage+"']");
	public void setDonationAmount(String amount) {
		setTextBoxText(byDonationAmount,amount,"Donation Amount");
	}
	public String getDonationAmount() {
		return getTextBoxText(byDonationAmount,"Donation Amount");
	}

	public void ValidateDonationAmount() {
		Actions act;
		setDonationAmount("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byDonationAmount, "Donation Amount", -200);
		if (isElementVisible(byDonationAmountValidationMessage,donationAmountValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Donation Amount is required");
		} else 			
			TestUtilities.logFailedWithScreenshot("Donation Amount is NOT required");
		setDonationAmount("Donation");
		if ("".equals(getDonationAmount())) 
			TestUtilities.logPassWithScreenshot("Donation Amount is only accepting numerical values");
		else
			TestUtilities.logFailedWithScreenshot("Donation Amount is accepting alphanumeric values");
		setDonationAmount("-1");
		act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byDonationAmountValidationMessage,donationAmountValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Donation Amount is not accepting nagative values");
		}
		else
			TestUtilities.logFailedWithScreenshot("Donation Amount is accepting nagative values");
		
		setDonationAmount("99999999");
		act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if ("999999".equals(getDonationAmount())) 
			TestUtilities.logPassWithScreenshot("Donation Amount max value is 999999");
		else
			TestUtilities.logFailedWithScreenshot("Donation Amount max value is NOT 999999");
		if (isElementVisible(byDonationAmountValidationMessage,donationAmountValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Donation Amount validation message is displayed");
		}
	}

	public By byMatchingAmount=By.id("matchingAmountInputField");
	public String matchingAmountValidationMessage="Matching amount must be between $1 and $1000 dollars.";
	public By byMatchingAmountValidationMessage=By.xpath("//*[text()='"+matchingAmountValidationMessage+"']");
	public By byOutOfRangeValidationMessage=By.xpath("//*[text()='The requested match amount is out of the range for this action. Please lower request amount to not exceed maximum for the year.']");
	public void setMatchingAmount(String amount) {
		setTextBoxText(byMatchingAmount,amount,"Matching Amount");
	}
	public String getMatchingAmount() {
		return getTextBoxText(byMatchingAmount,"Matching Amount");
	}

	public void ValidateMatchingAmount() {
		Actions act;
		setMatchingAmount("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byMatchingAmount, "Matching Amount", -200);
		if (isElementVisible(byMatchingAmountValidationMessage,matchingAmountValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Matching Amount is required");
		} else
			TestUtilities.logFailedWithScreenshot("Matching Amount is NOT required");
		setMatchingAmount("Matching");
		if ("".equals(getMatchingAmount())) 
			TestUtilities.logPassWithScreenshot("Matching Amount is only accepting numerical values");
		else
			TestUtilities.logFailedWithScreenshot("Matching Amount is accepting alphanumeric values");
		setMatchingAmount("-1");
		act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byMatchingAmountValidationMessage,matchingAmountValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Matching Amount is not accepting nagative values");
		} else
			TestUtilities.logFailedWithScreenshot("Matching Amount is accepting nagative values");
		setMatchingAmount("99999999");
		act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if ("99999".equals(getMatchingAmount())) 
			TestUtilities.logPassWithScreenshot("Matching Amount max value is 99999");
		else
			TestUtilities.logFailedWithScreenshot("Matching Amount max value is NOT 99999");
		if (isElementVisible(byMatchingAmountValidationMessage,matchingAmountValidationMessage, ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Matching Amount validation message is displayed");
		}
	}

	public By byDonationDate=By.id("donationDateInputField");
	public String getDonationDate() {
		return getTextBoxText(byDonationDate,"Donation Date");
	}
	public void ValidateDonationDate() {
		scrollUpDownToElement(webDriver, byDonationDate, "Donation Date", -200);
		if(isElementDisabled(byName,"Name", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Name is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Name is NOT disabled");
	}

	public By byCharityName=By.id("charityNameInputField");
	public String getCharityName() {
		return getTextBoxText(byCharityName,"Charity Name");
	}
	public void isCharityNameDisable() {
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if(isElementDisabled(byCharityName,"Charity Name", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Name is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is NOT disabled");
	}


	public String charityContactEmailValidationMessage="Charity Contact Email should be in the format contact@charity.com.";
	public By byCharityContactEmailValidationMessage=By.xpath("//*[text()='"+charityContactEmailValidationMessage+"']");
	public By byCharityContactEmail=By.xpath("//*[text()='Charity Contact Email']/following-sibling::input");
	public void setCharityContactEmail(String email) {
		setTextBoxText(byCharityContactEmail, email,"Charity Contact Email");
	}
	public String getCharityContactEmail() {
		return getTextBoxText(byCharityContactEmail,"Charity Contact Email");
	}

	public void isCharityContactEmailEnable() {
		scrollUpDownToElement(webDriver, byCharityContactEmail, "Charity Contact Email", -200);
		if(isElementEnabled(byCharityContactEmail,"Charity Contact Email", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Contact Email is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email is NOT enabled");
	}
	public void isCharityContactEmailDisable() {
		scrollUpDownToElement(webDriver, byCharityContactEmail, "Charity Contact Email", -200);
		if(isElementDisabled(byCharityContactEmail,"Charity Contac tEmail", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Contact Email is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email is NOT disabled");
	}

	public void ValidateCharityContactEmail() {
		Actions act = new Actions(driver);
		//TODO  Verify is not required. only this field is blank. verify can submit successfuly
		setCharityContactEmail("CharityContactEmail");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byCharityContactEmailValidationMessage,charityContactEmailValidationMessage, ExceptionHandling.STOP)) { 
			TestUtilities.logPassWithScreenshot("Charity Contact Email validation message is displayed");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email validation message is NOT displayed");
		setCharityContactEmail("CharityContactEmailCharityContactEmail@Charity.comontactEmailCharityContactEmail@Charity.com");
		String tmpStr=getCharityContactEmail();
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityContactEmailCharityContactEmail@Charity.com".equals(tmpStr))  
			TestUtilities.logPassWithScreenshot("Charity Contact Email is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email is accepting "+tmpStr.length()+" characters");	
	}

	public String charityPhoneValidationMessage="This field cannot be blank and must be in the format +00-000-000-0000.";
	public By byCharityPhoneValidationMessage=By.xpath("//*[text()='"+charityPhoneValidationMessage+"']");
	public By byCharityPhone=By.xpath("//*[text()='Charity Phone']/following-sibling::input");
	public void setCharityPhone(String phone) {
		setTextBoxText(byCharityPhone, phone,"Charity Phone");
	}
	public String getCharityPhone() {
		return getTextBoxText(byCharityPhone, "Charity Phone");
	}

	public void isCharityPhoneEnable() {
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if(isElementEnabled(byCharityPhone,"Charity Phone", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Phone is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is NOT enabled");
	}
	public void isCharityPhoneDisable() {
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if(isElementDisabled(byCharityPhone,"Charity Phone", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Phone is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is NOT disabled");
	}

	public void ValidateCharityPhone() {
		Actions act = new Actions(driver);
		setCharityPhone("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if (isElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Phone is required");
		} else
			TestUtilities.logPassWithScreenshot("Charity Phone is NOT required");
		setCharityPhone("Phone");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		if ("+".equals(getCharityPhone())) 
			TestUtilities.logPassWithScreenshot("Charity Phone is not accepting alphnumeric characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is accepting alphnumeric characters");
		setCharityPhone("01818");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Charity Phone validation message is displayed for invalid Phone number");
		} else		
			TestUtilities.logFailedWithScreenshot("Charity Phone validation message is NOT displayed for invalid Phone number");
		setCharityPhone("123456789101123456789101");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("+12-345-678-9101",getCharityPhone()))  
			TestUtilities.logPassWithScreenshot("Charity Phone is accepting 12 digits");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is accepting "+getCharityPhone().length()+" characters");
	}

	public String websiteValidationMessage="This field cannot be left blank.";
	public By byWebsiteValidationMessage=By.xpath("//*[text()='"+websiteValidationMessage+"']");
	public By byWebsite=By.id("websiteInputField");
	public void setWebsite(String website) {
		setTextBoxText(byWebsite,website,"Website");
	}
	public String getWebsite() {
		return getTextBoxText(byWebsite,"Website");
	}

	public void isWebsiteEnable() {
		scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
		if(isElementEnabled(byWebsite,"Charity Phone", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Website is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Website is NOT enabled");
	}
	public void isWebsiteDisable() {
		scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
		if(isElementDisabled(byWebsite,"Charity Phone", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Website is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Website is NOT disabled");
	}

	public void ValidateWebsite() {
		Actions act = new Actions(driver);
		setWebsite("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
		if (isElementVisible(byWebsiteValidationMessage,websiteValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Website is required");
		} else
			TestUtilities.logFailedWithScreenshot("Website is NOT required");
		setWebsite("Website");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byWebsiteValidationMessage,"Website should be in correct format", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Website validation message is displayed");
		} else
			TestUtilities.logFailedWithScreenshot("Website validation message is NOT displayed for incorrect format");
		setWebsite("www.charitycharitycharitycharitycharitycharity.comwww.charitycharitycharitycharitycharitycharity.com");
		act.sendKeys(Keys.TAB).build().perform();
		if ("www.charitycharitycharitycharitycharitycharity.com".equals(getWebsite()))  
			TestUtilities.logPassWithScreenshot("Website is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getWebsite().length()+" characters");
	}


	public String positionValidationMessage="This field cannot be left blank.";
	public By byPositionValidationMessage=By.xpath("//*[text()='Position in charity']/following-sibling::*[text()='"+positionValidationMessage+"']");
	public By byPosition=By.id("positionInCharityInputField");
	public void setPosition(String position) {
		setTextBoxText(byPosition,position,"Position in charity");
	}
	public String getPosition() {
		return getTextBoxText(byPosition,"Position in charity");
	}

	public void isPositionEnable() {
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		if(isElementEnabled(byPosition,"Position", ExceptionHandling.FAIL))
			TestUtilities.logPassWithScreenshot("Position is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Position is NOT enabled");
	}
	public void isPositionDisable() {
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		if(isElementVisible(byPosition," Position", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Position is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Position is NOT disabled");
	}

	public void ValidatePosition() {
		Actions act = new Actions(driver);
		setPosition("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		if (isElementVisible(byPositionValidationMessage,positionValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Position is required");
		} else
			TestUtilities.logFailedWithScreenshot("Position is NOT required");
		setPosition("BoardorCommitteeBoardorCommitteeBoardorCommitteeBoBoardorCommitteeBoardorCommitteeBoardorCommitteeBo");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("BoardorCommitteeBoardorCommitteeBoardorCommitteeBo",getPosition()))
			TestUtilities.logPassWithScreenshot("Position is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Position is accepting "+getPosition().length()+" characters");
	}

	public String charityContactValidationMessage="This field cannot be left blank.";
	public By byCharityContactValidationMessage=By.xpath("//*[text()='Charity Contact']/following-sibling::*[text()='"+charityContactValidationMessage+"']");
	public By byCharityContact=By.id("charityContactInputField");
	public void setCharityContact(String charityContact) {
		setTextBoxText(byCharityContact,charityContact,"Charity Contact");
	}
	public String getCharityContact() {
		return getTextBoxText(byCharityContact,"Charity Contact");
	}

	public void isCharityContactEnable() {
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		if(isElementEnabled(byCharityContact,"Charity Contact", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Contact is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact is NOT enabled");
	}
	public void isCharityContactDisable() {
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		if(isElementDisabled(byCharityContact,"Charity Contact", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Contact is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact is NOT disabled");
	}

	public void ValidateCharityContact() {
		Actions act = new Actions(driver);
		setCharityContact("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		if (isElementVisible(byCharityContactValidationMessage,charityContactValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Contact is required");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Contact is NOT required");
		setCharityContact("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byCharityContactValidationMessage,"Special characters are not accepted for Charity Contact", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Charity Contact validation message is displayed");
		} else		
			TestUtilities.logFailedWithScreenshot("Special characters are accepted for Charity Contact");
		setCharityContact("JohnSmithJohnSmithJohnSmithJohnSmithJohnSmithJohnSSmithJohnSmithJohnS");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("JohnSmithJohnSmithJohnSmithJohnSmithJohnSmithJohnS",getCharityContact())) 
			TestUtilities.logPassWithScreenshot("Charity Contact is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getCharityContact().length()+" characters");
	}

	public String charityAddress1ValidationMessage="This field cannot be left blank.";
	public By byCharityAddress1ValidationMessage=By.xpath("//*[text()='Charity Address 1']/following-sibling::*[text()='"+charityAddress1ValidationMessage+"']");
	public By byCharityAddress1=By.id("charityAddress1InputField");
	public void setCharityAddress1(String address1) {
		setTextBoxText(byCharityAddress1,address1,"Charity Address 1");
	}
	public String getCharityAddress1() {
		return getTextBoxText(byCharityAddress1,"Charity Address 1");
	}

	public void isCharityAddress1Enable() {
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address1", -200);
		if(isElementEnabled(byCharityAddress1,"Charity Address1", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address1 is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address1 is NOT enabled");
	}
	public void isCharityAddress1Disable() {
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address1", -200);
		if(isElementDisabled(byCharityAddress1,"Charity Address1", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address1 is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address1 is NOT disabled");
	}

	public void ValidateCharityAddress1() {
		Actions act = new Actions(driver);
		setCharityAddress1("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address 1", -200);
		if (isElementVisible(byCharityAddress1ValidationMessage,charityAddress1ValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Address 1 is required");
		}
		setCharityAddress1("Address1Address1Address1Address1Address1Address1AdAddress1Address1Add");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("Address1Address1Address1Address1Address1Address1Ad",getCharityAddress1()))  
			TestUtilities.logPassWithScreenshot("CharityAddress 1 is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("CharityAddress 1 is accepting "+getCharityAddress1().length()+" characters");
	}

	public By byCharityAddress2=By.id("charityAddress2InputField");
	public void setCharityAddress2(String address2) {
		setTextBoxText(byCharityAddress2,address2,"Charity Address 2");
	}
	public String getCharityAddress2() {
		return getTextBoxText(byCharityAddress2,"Charity Address 2");
	}

	public void isCharityAddress2Enable() {
		scrollUpDownToElement(webDriver, byCharityAddress2, "Charity Address 2", -200);
		if(isElementEnabled(byCharityAddress2,"Charity Address 2", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address 2 is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address 2 is NOT enabled");
	}
	public void isCharityAddress2Disable() {
		scrollUpDownToElement(webDriver, byCharityAddress2, "Charity Address2", -200);
		if(isElementDisabled(byCharityAddress2,"Charity Address 2", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address 2 is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address 2 is NOT disabled");
	}

	public void ValidateCharityAddress2() {
		Actions act = new Actions(driver);
		setCharityAddress2("Address2Address2Address2Address2Address2Address2AdAddress2Address2Address");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("Address2Address2Address2Address2Address2Address2Ad",getCharityAddress2())) 
			TestUtilities.logPassWithScreenshot("CharityAddress2 is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getCharityAddress2().length()+" characters");
	}

	public String charityAddress3ValidationMessage="This field cannot be left blank.";
	public By byCharityAddress3ValidationMessage=By.xpath("//*[text()='Charity Address 3']/following-sibling::*[text()='"+charityAddress3ValidationMessage+"']");
	public By byCharityAddress3=By.id("charityAddress3InputField");
	public void setCharityAddress3(String address3) {
		setTextBoxText(byCharityAddress3,address3,"Charity Address 3");
	}
	public String getCharityAddress3() {
		return getTextBoxText(byCharityAddress3,"Charity Address 3");
	}

	public void isCharityAddress3Enable() {
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address3", -200);
		if(isElementEnabled(byCharityAddress3,"Charity Address3", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address3 is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address3 is NOT enabled");
	}
	public void isCharityAddress3Disable() {
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address3", -200);
		if(isElementDisabled(byCharityAddress3,"Charity Address3", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Address3 is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address3 is NOT disabled");
	}

	public void ValidateCharityAddress3() {
		Actions act = new Actions(driver);
		setCharityAddress3("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address 3", -200);
		if (isElementVisible(byCharityAddress3ValidationMessage,charityAddress3ValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Address 3 is required");
		}
		setCharityAddress3("Address3Address3Address3Address3Address3Address3AdAddress3Address3Add");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("Address3Address3Address3Address3Address3Address3Ad",getCharityAddress3()))  
			TestUtilities.logPassWithScreenshot("CharityAddress 3 is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("CharityAddress 3 is accepting "+getCharityAddress3().length()+" characters");
	}

	public String publicityOpportunitiesValidationMessage="This field cannot be left blank.";
	public By byPublicityOpportunitiesValidationMessage=By.xpath("//*[text()='Please mention any publicity opportunities for HL (Enter N/A if unavailable)']/following-sibling::*[text()='"+publicityOpportunitiesValidationMessage+"']");
	public By byPublicityOpportunities=By.id("publicityOpportunitiesInputField");
	public void setPublicityOpportunities(String publicityOpportunities) {
		setTextBoxText(byPublicityOpportunities,publicityOpportunities,"Publicity Opportunities");
	}
	public String getPublicityOpportunities() {
		return getTextBoxText(byPublicityOpportunities, "Publicity Opportunities");
	}

	public void isPublicityOpportunitiesEnable() {
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		if(isElementEnabled(byPublicityOpportunities,"Publicity Opportunities", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Publicity Opportunities is NOT enabled");
	}
	public void isPublicityOpportunitiesDisable() {
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		if(isElementDisabled(byPublicityOpportunities,"Publicity Opportunities", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Publicity Opportunities is NOT disabled");
	}

	public void ValidatePublicityOpportunities() {
		Actions act = new Actions(driver);
		setPublicityOpportunities("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		if (isElementVisible(byPublicityOpportunitiesValidationMessage,publicityOpportunitiesValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is required");
		} else
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is NOT required");
		setPublicityOpportunities("PublicityOpportunitiesPublicityOpportunitiesPublicPublicityOpportunitiesPublic");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("PublicityOpportunitiesPublicityOpportunitiesPublic",getPublicityOpportunities())) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getPublicityOpportunities().length()+" characters");
	}

	public String charityPurposeValidationMessage="This field cannot be left blank.";
	public By byCharityPurposeValidationMessage=By.xpath("//*[text()='Charity Purpose']/following-sibling::*[text()='"+charityPurposeValidationMessage+"']");
	public By byCharityPurpose=By.id("misc.id");
	public void setCharityPurpose(String charityPurpose) {
		setTextBoxText(byCharityPurpose,charityPurpose,"Charity Purpose");
	}
	public String getCharityPurpose() {
		return getTextBoxText(byCharityPurpose, "Charity Purpose");
	}

	public void isCharityPurposeEnable() {
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		if(isElementEnabled(byCharityPurpose,"Charity Purpose", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Purpose is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is NOT enabled");
	}
	public void isCharityPurposeDisable() {
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		if(isElementDisabled(byCharityPurpose,"Charity Purpose", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Purpose is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is NOT disabled");
	}

	public void ValidateCharityPurpose() {
		Actions act = new Actions(driver);
		setCharityPurpose("");
		clickUpdateButton();
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		if (isElementVisible(byCharityPurposeValidationMessage,charityPurposeValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Purpose is required");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is NOT required");
		setCharityPurpose("CharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharitPurposeCharityPurposeCharityPurposeCharityPurposeCharit");
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharit".equals(getCharityPurpose()))  
			TestUtilities.logPassWithScreenshot("Charity Purpose is accepting max 300 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is accepting "+getCharityPurpose().length()+" characters");
	}

	public void updateMatchingRequest(String sheetName, String amount, String... display) {
		setDonationAmount(excel.getCellData(sheetName, "DonationAmount", 2));
		if (amount=="") setMatchingAmount(excel.getCellData(sheetName, "MatchingAmount", 2));
		else setMatchingAmount(amount);
		enterCharityInfo(sheetName);
		deleteMonetaryDoc();
		uploadMonetaryDoc(excel.getCellData(sheetName, "MonetaryFileName", 2));
		deleteCompletedDoc();
		uploadCompletedDoc(excel.getCellData(sheetName, "CompletedFileName", 2));
		deleteNonprofitDoc();
		uploadNonprofitDoc(excel.getCellData(sheetName, "NonprofitFileName", 2));
		clickUpdateButton();
		isRecordUpdatedSuccessfullyVisible(display);
	}

	public void enterCharityInfo(String sheetName) {
		setCharityContactEmail(excel.getCellData(sheetName, "CharityContactEmail", 2));
		setCharityPhone(excel.getCellData(sheetName, "CharityPhone", 2));
		setWebsite(excel.getCellData(sheetName, "Website", 2));
		setPosition(excel.getCellData(sheetName, "Position", 2));
		setCharityContact(excel.getCellData(sheetName, "CharityContact", 2));
		setCharityAddress1(excel.getCellData(sheetName, "CharityAddress1", 2));
		setCharityAddress2(excel.getCellData(sheetName, "CharityAddress2", 2));
		setCharityAddress3(excel.getCellData(sheetName, "CharityAddress3", 2));
		setPublicityOpportunities(excel.getCellData(sheetName, "PublicityOpportunities", 2));
		setCharityPurpose(excel.getCellData(sheetName, "CharityPurpose", 2));
	}

	public void isAllCharityFieldsDisabled() {
		isCharityNameDisable();
		isCharityContactEmailDisable();
		isCharityPhoneDisable();
		isWebsiteDisable();
		isPositionDisable();
		isCharityContactDisable();
		isCharityAddress1Disable();
		isCharityAddress2Disable();
		isCharityAddress3Disable();
		isPublicityOpportunitiesDisable();
		isCharityPurposeDisable();
	}

	public void isAllCharityFieldsEnabled() {
		isCharityContactEmailEnable();
		isCharityPhoneEnable();
		isWebsiteEnable();
		isPositionEnable();
		isCharityContactEnable();
		isCharityAddress1Enable();
		isCharityAddress2Enable();
		isCharityAddress3Enable();
		isPublicityOpportunitiesEnable();
		isCharityPurposeEnable();
	}

	public void Verify_OutOfRangeValidation(String managementLevel, String managementLevelName, String amount) {
/*		executeSQLUpdateStaff("FIN",managementLevel,"2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		createSubmittedMatchingRequest(amount);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		landingPage.clickCreateMatchingRequest();
		createSubmittedMatchingRequest(amount,"DontShowToastMessage");
		scrollUpDownToElement(webDriver, byProgressLabel, "Progress Label", -200);
		if (isElementVisible(byOutOfRangeValidationMessage,"OutOfRangeValidationMessage", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Out Of Range Validation Message is displayed for: "+managementLevelName);
		landingPage.closeToastMessage();
		*/
	}

	public void verifyData(String sheetName) {
		verifyEquals(executeSQLSelectName(),(getTextBoxText(byName,"Name", ExceptionHandling.FAIL)),"DisplayEqualMessage");
		verifyEquals(executeSQLSelectOfficeLocation(),getTextBoxText(byOfficeLocationEnabled,"Office Location", ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(executeSQLSelectTitle(),getTextBoxText(byTitleEnabled,"Title", ExceptionHandling.FAIL),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "DonationAmount", 2),getDonationAmount(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "MatchingAmount", 2),getMatchingAmount(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "DonationDate", 2),getDonationDate(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityName", 2),getCharityName(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityContact", 2),getCharityContact(),"DisplayEqualMessage"); 
		verifyEquals(excel.getCellData(sheetName, "CharityContactEmail", 2),getCharityContactEmail(),"DisplayEqualMessage"); 
		verifyEquals(excel.getCellData(sheetName, "CharityPhone", 2),getCharityPhone(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "Website", 2),getWebsite(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "Position", 2),getPosition(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityPurpose", 2),getCharityPurpose(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityAddress1", 2),getCharityAddress1(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityAddress2", 2),getCharityAddress2(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "CharityAddress3", 2),getCharityAddress3(),"DisplayEqualMessage");
		verifyEquals(excel.getCellData(sheetName, "PublicityOpportunities", 2),getPublicityOpportunities(),"DisplayEqualMessage");
	}

}
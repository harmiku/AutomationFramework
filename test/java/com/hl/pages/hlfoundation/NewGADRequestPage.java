package com.hl.pages.hlfoundation;

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

public class NewGADRequestPage extends TestBase {

	WebDriver webDriver;
	LandingPage landingPage;
	String filePath = excel.getCellData("GADRequest1Files", "FilePath", 2);
	String fileName="";

	public NewGADRequestPage(WebDriver driver) {
		webDriver = driver;
		landingPage=new LandingPage(driver);
	}

	public By byCreateButton=By.xpath("//button[*[text()='Create Give A Day Request']]");
	public void clickCreateButton() {
		scrollUpDownToElement(webDriver, byCreateButton, "Create Give A Day Button" );
		clickElement(byCreateButton, "Create Give A Day Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void isCreateButtonEnable() {
		scrollUpDownToElement(webDriver, byCreateButton, "Create Give A Day Button");
		if(isElementEnabled(byCreateButton,"Create Give A Day Button", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Create Give A Day Button is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Create Give A Day Button is NOT enabled");
	}
	public void isCreateButtonDisable() {
		scrollUpDownToElement(webDriver, byCreateButton, "Create Give A Day Button");
		if(isElementDisabled(byCreateButton,"Create Give A Day Button", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Create Give A Day Button is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Create Give A Day Button is NOT disabled");
	}

	public By byClearButton=By.xpath("//button[*[text()='Clear Form']]");
	public void clickClearFormButton() {
		scrollUpDownToElement(webDriver, byClearButton, "Clear Form Button");
		clickElement(byClearButton, "Clear Form Button");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void isClearFormButtonEnable() {
		scrollUpDownToElement(webDriver, byClearButton, "Clear Form Button");
		if(isElementEnabled(byClearButton,"Clear Form Button", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Clear Form Button is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Clear Form Button is NOT enabled");
	}
	public void isClearFormButtonDisable() {
		scrollUpDownToElement(webDriver, byClearButton, "Clear Form Button");
		if(isElementDisabled(byClearButton,"Clear Form Button", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Clear Form Button is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Clear Form Button is NOT disabled");
	}

	public boolean isFileNameDisplayed(String fileName) {
		scrollUpDownToElement(webDriver, byRequiredDocumentsLabel, "RequiredDocumentsLabel");
		wait = new WebDriverWait(driver, 60);
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
		case "ValidFile":fileName = excel.getCellData("GADRequest1Files", "MonetaryFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("GADRequest1Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("GADRequest1Files", "Over100MB", 2);break;
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
		case "ValidFile":fileName = excel.getCellData("GADRequest1Files", "CompletedFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("GADRequest1Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("GADRequest1Files", "Over100MB", 2);break;
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
		case "ValidFile":fileName = excel.getCellData("GADRequest1Files", "NonprofitFileName", 2);break;
		case "NonPDFFile":fileName = excel.getCellData("GADRequest1Files", "NonPDF", 2);break;
		case "Over100MBFile":fileName = excel.getCellData("GADRequest1Files", "Over100MB", 2);break;
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

	public By byRecordAddedSuccessfully=By.xpath("//*[text()='Record Added Successfully']");
	public void isRecordAddedSuccessfullyVisible(String... display) {
		if (display.length ==0) {
			if(isElementVisible(byRecordAddedSuccessfully,"Toast message : Record Added Successfully", ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("["+"Toast message : Record Added Successfully"+"] is visible");
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

	public By byGADRequestFormPanel=By.xpath("//*[text()='Give A Day Request Form']");
	public By byProgressLabel=By.xpath("//*[text()='Progress']");

	String pendingRequestMessage="You have 5 Give A Day request in progress please come back after it has been approved or denied.";
	public By byPendingRequestMessage = By.xpath("//*[text()='"+pendingRequestMessage+"']");

	public void isPendingRequestMessageVisible(String status) {
		isValidationMessageVisible(byPendingRequestMessage,pendingRequestMessage, status, 0);
	}

	public void isNotPendingRequestMessageVisible(String status) {
		isNotValidationMessageVisible(byPendingRequestMessage,pendingRequestMessage, status);
	}

	String maxedOutMessage="You have maxed out your Give A Day.";
	public By byMaxedOutMessage = By.xpath("//*[text()='"+maxedOutMessage+"']");

	public void isMaxedOutMessageVisible(String status) {
		isValidationMessageVisible(byMaxedOutMessage,maxedOutMessage, status, -400);
	}

	public void isNoMaxedOutMessageVisible(String status) {
		isNotValidationMessageVisible(byMaxedOutMessage,maxedOutMessage, status);
	}

	public void isValidationMessageVisible(By by, String validationMessage, String status, int...UpDown) {
		if(UpDown.length != 0)	
			scrollUpDownToElement(webDriver, byGADRequestFormPanel, "GADRequestFormPanel",UpDown[0]);
		else
			scrollUpDownToElement(webDriver, byGADRequestFormPanel, "GADRequestFormPanel",-200);
		if(isElementVisible(by, validationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("["+validationMessage+"] is visible for: "+status);	
		}
	}

	public void isNotValidationMessageVisible(By by, String validationMessage, String status) {
		scrollUpDownToElement(webDriver, byProgressLabel, "GADRequestFormPanel",-200);
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
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byName, "Name", -200);
		if (isElementDisabled(byName,"Name", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Name is still disabled after clicking Clear Form button");
			if (executeSQLSelectName().equals(getTextBoxText(byName,"Name", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Name is not cleared after clicking Clear Form button");
			else 
				TestUtilities.logFailedWithScreenshot("Name is changed after clicking Clear Form button");
		}
	}

	public By byOfficeLocation = By.xpath("//*[@id='officeLocationInputField']");
	public void ValidateOfficeLocation() {
		scrollUpDownToElement(webDriver, byOfficeLocation, "Office Location", -200);
		if (isElementDisabled(byOfficeLocation,"Office Location", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Office Location is disabled");
			if (executeSQLSelectOfficeLocation().equals(getTextBoxText(byOfficeLocation,"Office Location", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Office Location is populated from user’s login credentials");
			else		
				TestUtilities.logPassWithScreenshot("Office Location is NOT populated from user’s login credentials");
		}
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byOfficeLocation, "Office Location", -200);
		if (isElementDisabled(byOfficeLocation,"Office Location", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Office Location is still disabled after clicking Clear Form button");
			if (executeSQLSelectOfficeLocation().equals(getTextBoxText(byOfficeLocation,"Office Location", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Office Location is not cleared after clicking Clear Form button");
			else
				TestUtilities.logFailedWithScreenshot("Office Location is changed after clicking Clear Form button");
		}
	}

	public By byTitle = By.xpath("//*[@id='employeeTitleInputField']");
	public void ValidateTitle() {
		scrollUpDownToElement(webDriver, byTitle, "Title", -200);
		if (isElementDisabled(byTitle,"Title", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Title is disabled");
			String expected=executeSQLSelectTitle();
			String actual=getTextBoxText(byTitle,"Title", ExceptionHandling.FAIL);
			String passMessage="Title is populated from user’s login credentials";
			String failMessage="Title is NOT populated from user’s login credentials";
			if(verifyEquals(expected, actual))
				TestUtilities.logPassWithScreenshot(passMessage);
			else 
				TestUtilities.logFailedWithScreenshot(failMessage);
		}
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byTitle, "Title", -200);
		if (isElementDisabled(byTitle,"Title", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Title is still disabled after clicking Clear Form button");
			if (executeSQLSelectTitle().equals(getTextBoxText(byTitle,"Title", ExceptionHandling.FAIL))) 
				TestUtilities.logPassWithScreenshot("Title is not cleared after clicking Clear Form button");
			else
				TestUtilities.logFailedWithScreenshot("Title is changed after clicking Clear Form button");
		}
	}

	public String activityDateValidationMessage="Date must be at least 1 month ahead.";
	public By byActivityDateValidationMessage=By.xpath("//*[contains(text(),'Date must be at least 1 month ahead.')]");
	public By byActivityDateValidationMessageElement=By.xpath("//*[text()='Date Of Activity']//following-sibling::small");
	public By byActivityDate=By.id("dateOfActivityInputField");
	public void setActivityDate(String date) {
		setTextBoxText(byActivityDate,date,"Activity Date");
	}
	public String getActivityDate() {
		return getTextBoxText(byActivityDate,"Activity Date");
	}

	public void ValidateActivityDate() {
		clickCreateButton();
		scrollUpDownToElement(webDriver, byActivityDate, "Activity Date", -200);
		if (isElementVisible(byActivityDateValidationMessageElement,activityDateValidationMessage, ExceptionHandling.FAIL)) {  
			if (isElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL))  
				TestUtilities.logPassWithScreenshot("Activity Date is required");
			else
				TestUtilities.logFailedWithScreenshot("Activity Date validation message is NOT correct");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byActivityDate, "Activity Date", -200);
			if (isNotElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Activity Date validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Activity Date validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Activity Date is NOT required");
		clickClearFormButton();
		setActivityDate(TestUtilities.addToCurrentDate(0, 1,-1).substring(0, 5));
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Activity Date validation message is displayed for less than 1 month ahead.");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byActivityDate, "Activity Date", -200);
			isNotElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL);
		} else
			TestUtilities.logFailedWithScreenshot("Activity Date validation message is NOT displayed for less than 1 month ahead.");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

		//clickClearFormButton();
		setActivityDate(TestUtilities.addToCurrentDate(0, 1, 0).substring(0, 5));
		act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		if ((TestUtilities.addToCurrentDate(0, 1, 0, "yyyy-MM-dd").equals(getActivityDate())) &
				(isNotElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL)))
			TestUtilities.logPassWithScreenshot("Activity Date is accepted for at least 1 month ahead.");
		else
			TestUtilities.logFailedWithScreenshot("Activity Date is NOT accepted for at least 1 month ahead.");

		clickClearFormButton();
		scrollUpDownToElement(webDriver, byActivityDate, "Activity Date", -200);
		isNotElementVisible(byActivityDateValidationMessage,activityDateValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getActivityDate())) 
			TestUtilities.logPassWithScreenshot("Activity Date is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Activity Date is NOT cleared after clicking ClearForm Button");
	}

	public By byPreviousCharityRadioNegativeInput=By.xpath("//input[@name='previousCharityRadioNegative']");
	public By byPreviousCharityRadioNegative=By.name("previousCharityRadioNegative");
	public void setPreviousCharityRadioNegative() {
		scrollUpDownToElement(webDriver, byPreviousCharityRadioNegative, "Previous Charity No Radio button",-300);
		selectRadioButton(byPreviousCharityRadioNegative,"Have you donated to this charity before? No");
	}

	public void Verify_PreviousCharityRadioNegativeSelected(){
		scrollUpDownToElement(webDriver, byPreviousCharityRadioNegative, "Previous Charity No Radio button",-300);
		if (isWebElementSelected(byPreviousCharityRadioNegativeInput,"Previous Charity No Radio button", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("By default No is selected for: Have you donated to this charity before?");
		else
			TestUtilities.logFailedWithScreenshot("By default No is NOT selected for: Have you donated to this charity before?");
	}

	public By byPreviousCharityRadioPositive=By.name("previousCharityRadioPositive");
	public void setPreviousCharityRadioPositive() {
		scrollUpDownToElement(webDriver, byPreviousCharityRadioPositive, "Previous Charity Yes Radio button",-300);
		selectRadioButton(byPreviousCharityRadioPositive,"Have you donated to this charity before? Yes");
	}

	public By byCharityNameDiv=By.xpath("//div[*[text()='Select Previous Charity']]");
	public By byCharityName=By.id("charityNameInputField");
	public By byCharityNameDisabled=By.xpath("//*[@id='charityNameInputField' and @disabled]");
	public By byCharityNameValidationMessage=By.xpath("//*[text()='Charity Name']/following-sibling::*[text()='This field cannot be left blank.']");
	public By byCharityDropDown = By.xpath("//*[text()='Select Previous Charity']/parent::div//*[contains(@class,'p-dropdown-label')]");
	public void setCharityName(String charityName) {
		setTextBoxText(byCharityName,charityName,"Charity Name");
	}
	public String getCharityName() {
		return getTextBoxText(byCharityName,"Charity Name");
	}

	public void setCharityDropDown(String value, ExceptionHandling... exceptionHandling) {
		selectDropDownOption(byCharityDropDown, value, "Select Previous Charity", exceptionHandling);
	}
	public String getCharityDropDown(ExceptionHandling... exceptionHandling) {
		return getElementText(byCharityDropDown, "Select Previous Charity Drop Down", exceptionHandling);
	}

	public void isSelectPreviousCharityDropDownClear() {
		scrollUpDownToElement(webDriver, byCharityDropDown, "Charity DropDown", -200);
		if(getCharityDropDown(ExceptionHandling.FAIL).equals("Select a Charity"))
			TestUtilities.logPassWithScreenshot("[Select Previous Charity drop down] is cleared");
		else
			TestUtilities.logFailedWithScreenshot("[Select Previous Charity drop down] is not cleared");
	}

	public void isNotCharityNameDivVisible() {
		if(isNotElementVisible(byCharityNameDiv,"Select Previous Charity", ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("[Select Previous Charity drop down] is not displayed");
		}
	}
	public void isCharityNameDivVisible() {
		if(isElementVisible(byCharityNameDiv,"Select Previous Charity", ExceptionHandling.FAIL)){
			TestUtilities.logPassWithScreenshot("[Select Previous Charity drop down] is displayed");
		}
	}

	public void isCharityNameEnable() {
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if(isElementEnabled(byCharityName,"Charity Name", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Name is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is NOT enabled");

	}
	public void isCharityNameDisable() {
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if(isElementDisabled(byCharityName,"Charity Name", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Name is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is NOT disabled");
	}

	public void ValidateCharityName() {
		Actions act = new Actions(driver);
		//setPreviousCharityRadioNegative();
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if (isElementVisible(byCharityNameValidationMessage,"Charity Name Validation Message", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Charity Name is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
			if(isNotElementVisible(byCharityNameValidationMessage,"Charity Name Validation Message", ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Name Validation Message is cleared after clicking ClearForm Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Name Validation Message is NOT cleared after clicking ClearForm Button");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Name is NOT required");
		setCharityName("CharityNameCharityNameCharityNameCharityCharityNamCharityNamCharityNamCharityNamCharityNam");
		String tmpStr=getCharityName();
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityNameCharityNameCharityNameCharityCharityNam".equals(tmpStr)) 
			TestUtilities.logPassWithScreenshot("Charity Name is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+tmpStr.length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if("".equals(getCharityName())) 
			TestUtilities.logPassWithScreenshot("Charity Name is cleared after clicking Clear Form Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is NOT cleared after clicking Clear Form Button");
	}

	public void Verify_SelectingCharityDropDown_PopulatesCharityFields() {
		String sheetName="GADRequest1";
		createNewGADRequest(sheetName);
		executeSQLUpdateAllGADRequestsStatus("7");
		refreshPage();
		setPreviousCharityRadioPositive();
		isCharityNameDivVisible();
		isCharityNameDisable();
		String fieldData=excel.getCellData(sheetName, "CharityName", 2);
		selectDropDownOption(byCharityDropDown, fieldData, "Charity",ExceptionHandling.FAIL);
		scrollUpDownToElement(webDriver, byCharityName, "Charity Name", -200);
		if (verifyEquals(fieldData,getCharityName())) 
			TestUtilities.logPassWithScreenshot("Charity Name is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityContactEmail, "Charity Contact Email", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityContactEmail", 2),getCharityContactEmail())) 
			TestUtilities.logPassWithScreenshot("Charity Contact Email is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityPhone", 2),getCharityPhone())) 
			TestUtilities.logPassWithScreenshot("Charity Phone is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
		if (verifyEquals(excel.getCellData(sheetName, "Website", 2),getWebsite())) 
			TestUtilities.logPassWithScreenshot("Website is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		if (verifyEquals(excel.getCellData(sheetName, "Position", 2),getPosition())) 
			TestUtilities.logPassWithScreenshot("Position in charity is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityPurpose", 2),getCharityPurpose())) 
			TestUtilities.logPassWithScreenshot("Charity Purpose is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityActivity", 2),getCharityActivity())) 
			TestUtilities.logPassWithScreenshot("Charity Activity is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityContact", 2),getCharityContact())) 
			TestUtilities.logPassWithScreenshot("Charity Contact is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address1", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityAddress1", 2),getCharityAddress1())) 
			TestUtilities.logPassWithScreenshot("Charity Address 1 is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityAddress2, "Charity Address2", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityAddress2", 2),getCharityAddress2())) 
			TestUtilities.logPassWithScreenshot("Charity Address 2 is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address3", -200);
		if (verifyEquals(excel.getCellData(sheetName, "CharityAddress3", 2),getCharityAddress3())) 
			TestUtilities.logPassWithScreenshot("Charity Address 3 is copied from previously approved requests");
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		if (verifyEquals(excel.getCellData(sheetName, "PublicityOpportunities", 2),getPublicityOpportunities())) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is copied from previously approved requests");
	}

	public void ValidateCharityDropDown() {
		createNewGADRequest("GADRequest1");
		executeSQLUpdateAllGADRequestsStatus("7");
		refreshPage();
		setPreviousCharityRadioPositive();
		isCharityNameDivVisible();
		isCharityNameDisable();
		isCreateButtonDisable();
		isSelectPreviousCharityDropDownClear();
		selectDropDownOption(byCharityDropDown, excel.getCellData("GADRequest1", "CharityName", 2), "Charity",ExceptionHandling.FAIL);
		isCreateButtonEnable();
		clickClearFormButton();
		isCharityNameEnable();
		setPreviousCharityRadioPositive();
		isCharityNameDivVisible();
		isCharityNameDisable();
		isCreateButtonDisable();
		isSelectPreviousCharityDropDownClear();
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
		if (isElementVisible(byCharityContactEmailValidationMessage,charityContactEmailValidationMessage, ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Charity Contact Email validation message is displayed for incorrect email format");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityContactEmail, "Charity Contact Email",-200);
			if (isNotElementVisible(byCharityContactEmailValidationMessage,charityContactEmailValidationMessage, ExceptionHandling.FAIL))
			{
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Contact Email validation message is cleared after clicking ClearForm Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Contact Email validation message is NOT cleared after clicking ClearForm Button");
		} else  
			TestUtilities.logFailedWithScreenshot("Charity Contact Email validation message is NOT displayed for incorrect email format");
		setCharityContactEmail("CharityContactEmailCharityContactEmail@Charity.comontactEmailCharityContactEmail@Charity.com");
		String tmpStr=getCharityContactEmail();
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityContactEmailCharityContactEmail@Charity.com".equals(tmpStr))  
			TestUtilities.logPassWithScreenshot("Charity Contact Email is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email is accepting "+tmpStr.length()+" characters");	
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityContactEmail, "Charity Contact Email",-200);
		if ("".equals(getCharityContactEmail())) 
			TestUtilities.logPassWithScreenshot("Charity Contact Email is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact Email is cleared after clicking ClearForm Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if (isElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Phone is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
			if(isNotElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Phone validation message is cleared after clicking ClearForm Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Phone validation message is cleared after clicking ClearForm Button");
		} else
			TestUtilities.logPassWithScreenshot("Charity Phone is NOT required");
/*		setCharityPhone("Phone");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		if ("+".equals(getCharityPhone())) 
			TestUtilities.logPassWithScreenshot("Charity Phone is not accepting alphnumeric characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is accepting alphnumeric characters");
		setCharityPhone("01818");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Charity Phone validation message is displayed for invalid Phone number");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
			isNotElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL);
		} else		
			TestUtilities.logFailedWithScreenshot("Charity Phone validation message is NOT displayed for invalid Phone number");
*/		setCharityPhone("123456789101234567891012345678910");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("123456789101234567891012345678910",getCharityPhone()))  
			TestUtilities.logPassWithScreenshot("Charity Phone is accepting 30 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is accepting "+getCharityPhone().length()+" characters");
		
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		isNotElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getCharityPhone())) 
			TestUtilities.logPassWithScreenshot("Charity Phone is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Phone is NOT cleared after clicking ClearForm Button");		
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
		if (isElementVisible(byWebsiteValidationMessage,websiteValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Website is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byWebsite, "Website", -200);
			if (isNotElementVisible(byWebsiteValidationMessage,websiteValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Website validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Website validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Website is NOT required");
		setWebsite("Website");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byWebsiteValidationMessage,"Website should be in correct format", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Website validation message is displayed for incorrect format");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byWebsite, "Website",-200);
			isNotElementVisible(byWebsiteValidationMessage,websiteValidationMessage, ExceptionHandling.FAIL);
		} else
			TestUtilities.logFailedWithScreenshot("Website validation message is NOT displayed for incorrect format");
		setWebsite("www.charitycharitycharitycharitycharitycharity.comwww.charitycharitycharitycharitycharitycharity.com");
		act.sendKeys(Keys.TAB).build().perform();
		if ("www.charitycharitycharitycharitycharitycharity.com".equals(getWebsite()))  
			TestUtilities.logPassWithScreenshot("Website is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getWebsite().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byWebsite, "Website",-200);
		isNotElementVisible(byWebsiteValidationMessage,websiteValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getWebsite())) 
			TestUtilities.logPassWithScreenshot("Website is cleared after clicking Clear Form Button");
		else
			TestUtilities.logFailedWithScreenshot("Website is NOT cleared after clicking Clear Form Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		if (isElementVisible(byPositionValidationMessage,positionValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Position is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byPosition, "Position", -200);
			if(isNotElementVisible(byPositionValidationMessage,positionValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Position validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Position validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Position is NOT required");
		setPosition("BoardorCommitteeBoardorCommitteeBoardorCommitteeBoBoardorCommitteeBoardorCommitteeBoardorCommitteeBo");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("BoardorCommitteeBoardorCommitteeBoardorCommitteeBo",getPosition()))
			TestUtilities.logPassWithScreenshot("Position is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Position is accepting "+getPosition().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byPosition, "Position", -200);
		isNotElementVisible(byPositionValidationMessage,positionValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getPosition())) 
			TestUtilities.logPassWithScreenshot("Position is cleared after clicking Clear Form Button");
		else
			TestUtilities.logFailedWithScreenshot("Position is NOT cleared after clicking Clear Form Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		if (isElementVisible(byCharityContactValidationMessage,charityContactValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Contact is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
			if (isNotElementVisible(byCharityContactValidationMessage,charityContactValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Contact validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Contact validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Contact is NOT required");
		setCharityContact("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
		act.sendKeys(Keys.TAB).build().perform();
		if (isElementVisible(byCharityContactValidationMessage,"Special characters are not accepted for Charity Contact", ExceptionHandling.FAIL)) { 
			TestUtilities.logPassWithScreenshot("Special characters are not accepted for Charity Contact");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
			isNotElementVisible(byCharityContactValidationMessage,charityContactValidationMessage, ExceptionHandling.FAIL);
		} else		
			TestUtilities.logFailedWithScreenshot("Special characters are accepted for Charity Contact");
		setCharityContact("JohnSmithJohnSmithJohnSmithJohnSmithJohnSmithJohnSSmithJohnSmithJohnS");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("JohnSmithJohnSmithJohnSmithJohnSmithJohnSmithJohnS",getCharityContact())) 
			TestUtilities.logPassWithScreenshot("Charity Contact is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getCharityContact().length()+" characters");

		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityContact, "Charity Contact", -200);
		isNotElementVisible(byCharityContactValidationMessage,charityContactValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getCharityContact())) 
			TestUtilities.logPassWithScreenshot("Charity Contact is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Contact is NOT cleared after clicking ClearForm Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address 1", -200);
		if (isElementVisible(byCharityAddress1ValidationMessage,charityAddress1ValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Address 1 is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address 1", -200);
			if(isNotElementVisible(byCharityAddress1ValidationMessage,charityAddress1ValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Address 1 validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Address 1 validation message is NOT cleared after clicking Clear Form Button");
		}
		setCharityAddress1("Address1Address1Address1Address1Address1Address1AdAddress1Address1Add");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("Address1Address1Address1Address1Address1Address1Ad",getCharityAddress1()))  
			TestUtilities.logPassWithScreenshot("CharityAddress 1 is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("CharityAddress 1 is accepting "+getCharityAddress1().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityAddress1, "Charity Address 1", -200);
		isNotElementVisible(byCharityAddress1ValidationMessage,charityAddress1ValidationMessage, ExceptionHandling.FAIL); 
		if ("".equals(getCharityAddress1())) 
			TestUtilities.logPassWithScreenshot("Charity Address 1 is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address 1 is NOT cleared after clicking ClearForm Button");
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
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityAddress2, "Charity Address2", -200);
		if ("".equals(getCharityAddress2())) 
			TestUtilities.logPassWithScreenshot("Charity Address2 is cleared after clicking Clear Form Button");
		else
			TestUtilities.logPassWithScreenshot("Charity Address2 is NOT cleared after clicking Clear Form Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address 3", -200);
		if (isElementVisible(byCharityAddress3ValidationMessage,charityAddress3ValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Address 3 is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address 3", -200);
			if(isNotElementVisible(byCharityAddress3ValidationMessage,charityAddress3ValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Address 3 validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Address 3 validation message is NOT cleared after clicking Clear Form Button");
		}
		setCharityAddress3("Address3Address3Address3Address3Address3Address3AdAddress3Address3Add");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("Address3Address3Address3Address3Address3Address3Ad",getCharityAddress3()))  
			TestUtilities.logPassWithScreenshot("CharityAddress 3 is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("CharityAddress 3 is accepting "+getCharityAddress3().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityAddress3, "Charity Address 3", -200);
		isNotElementVisible(byCharityAddress3ValidationMessage,charityAddress3ValidationMessage, ExceptionHandling.FAIL); 
		if ("".equals(getCharityAddress3())) 
			TestUtilities.logPassWithScreenshot("Charity Address3 is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Address3 is NOT cleared after clicking ClearForm Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		if (isElementVisible(byPublicityOpportunitiesValidationMessage,publicityOpportunitiesValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
			if(isNotElementVisible(byPublicityOpportunitiesValidationMessage,publicityOpportunitiesValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Publicity Opportunities validation message is cleared after clicking ClearForm Button");
			} else
				TestUtilities.logPassWithScreenshot("Publicity Opportunities validation message is NOT cleared after clicking ClearForm Button");
		} else
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is NOT required");
		setPublicityOpportunities("PublicityOpportunitiesPublicityOpportunitiesPublicPublicityOpportunitiesPublic");
		act.sendKeys(Keys.TAB).build().perform();
		if (verifyEquals("PublicityOpportunitiesPublicityOpportunitiesPublic",getPublicityOpportunities())) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is accepting max 50 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Name is accepting "+getPublicityOpportunities().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byPublicityOpportunities, "Publicity Opportunities", -200);
		isNotElementVisible(byPublicityOpportunitiesValidationMessage,publicityOpportunitiesValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getPublicityOpportunities())) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Publicity Opportunities is NOT cleared after clicking ClearForm Button");
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
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		if (isElementVisible(byCharityPurposeValidationMessage,charityPurposeValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Purpose is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
			if(isNotElementVisible(byCharityPurposeValidationMessage,charityPurposeValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Purpose validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Purpose validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is NOT required");
		setCharityPurpose("CharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharitPurposeCharityPurposeCharityPurposeCharityPurposeCharit");
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharityPurposeCharit".equals(getCharityPurpose()))  
			TestUtilities.logPassWithScreenshot("Charity Purpose is accepting max 300 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is accepting "+getCharityPurpose().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityPurpose, "Charity Purpose", -200);
		isNotElementVisible(byCharityPurposeValidationMessage,charityPurposeValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getCharityPurpose())) 
			TestUtilities.logPassWithScreenshot("Charity Purpose is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Purpose is NOT cleared after clicking ClearForm Button");
	}

	public String charityActivityValidationMessage="This field cannot be left blank.";
	public By byCharityActivityValidationMessage=By.xpath("//*[text()='Charity Activity']/following-sibling::*[text()='"+charityPurposeValidationMessage+"']");
	public By byCharityActivity=By.xpath("(//*[@id='misc.id'])[2]");
	public void setCharityActivity(String charityActivity) {
		setTextBoxText(byCharityActivity,charityActivity,"Charity Activity");
	}
	public String getCharityActivity() {
		return getTextBoxText(byCharityActivity, "Charity Activity");
	}

	public void isCharityActivityEnable() {
		scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
		if(isElementEnabled(byCharityActivity,"Charity Activity", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Activity is enabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Activity is NOT enabled");
	}
	public void isCharityActivityDisable() {
		scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
		if(isElementDisabled(byCharityActivity,"Charity Activity", ExceptionHandling.FAIL)) 
			TestUtilities.logPassWithScreenshot("Charity Activity is disabled");
		else
			TestUtilities.logFailedWithScreenshot("Charity Activity is NOT disabled");
	}

	public void ValidateCharityActivity() {
		Actions act = new Actions(driver);
		clickCreateButton();
		scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
		if (isElementVisible(byCharityActivityValidationMessage,charityActivityValidationMessage, ExceptionHandling.FAIL)) {
			TestUtilities.logPassWithScreenshot("Charity Activity is required");
			clickClearFormButton();
			scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
			if(isNotElementVisible(byCharityActivityValidationMessage,charityActivityValidationMessage, ExceptionHandling.FAIL)) {
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				TestUtilities.logPassWithScreenshot("Charity Activity validation message is cleared after clicking Clear Form Button");
			} else
				TestUtilities.logFailedWithScreenshot("Charity Activity validation message is NOT cleared after clicking Clear Form Button");
		} else
			TestUtilities.logFailedWithScreenshot("Charity Activity is NOT required");
		setCharityActivity("CharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharitActivityCharityActivityCharityActivityCharityActivityCharit");
		act.sendKeys(Keys.TAB).build().perform();
		if ("CharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivityCharityActivity".equals(getCharityActivity()))  
			TestUtilities.logPassWithScreenshot("Charity Activity is accepting max 300 characters");
		else
			TestUtilities.logFailedWithScreenshot("Charity Activity is accepting "+getCharityActivity().length()+" characters");
		clickClearFormButton();
		scrollUpDownToElement(webDriver, byCharityActivity, "Charity Activity", -200);
		isNotElementVisible(byCharityActivityValidationMessage,charityActivityValidationMessage, ExceptionHandling.FAIL);
		if ("".equals(getCharityActivity())) 
			TestUtilities.logPassWithScreenshot("Charity Activity is cleared after clicking ClearForm Button");
		else
			TestUtilities.logFailedWithScreenshot("Charity Activity is NOT cleared after clicking ClearForm Button");
	}

	public void createNewGADRequest(String sheetName, String... display) {
		setActivityDate(TestUtilities.addToCurrentDate(0, 1, 0).substring(0, 5));
		//setPreviousCharityRadioNegative();
		enterCharityInfo(sheetName);
		uploadNonprofitDoc(excel.getCellData(sheetName, "NonprofitFileName", 2));
		clickCreateButton();
		isRecordAddedSuccessfullyVisible(display);
	}

	public void enterCharityInfo(String sheetName) {
		setCharityName(excel.getCellData(sheetName, "CharityName", 2));
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
		setCharityActivity(excel.getCellData(sheetName, "CharityActivity", 2));
	}

	public void enterInvalidCharityInfo(String... sheetName) {
		setCharityContactEmail("InvalidEmail");
		setCharityPhone("123");
		setWebsite("InvalidWebsite");
		setCharityContact("!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~");
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
		isCharityActivityDisable();
	}

	public void isAllCharityFieldsEnabled() {
		isCharityNameEnable();
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
		isCharityActivityEnable();
	}

	public void isAllCharityFieldsCleared() {
		if (verifyEquals("",getCharityName())) 
			TestUtilities.logPassWithScreenshot("Charity Name is cleared");
		if (verifyEquals("",getCharityContactEmail())) 
			TestUtilities.logPassWithScreenshot("Charity Contact Email is cleared");
		if (verifyEquals("",getCharityPhone())) 
			TestUtilities.logPassWithScreenshot("Charity Phone is cleared");
		if (verifyEquals("",getWebsite())) 
			TestUtilities.logPassWithScreenshot("Website is cleared");
		if (verifyEquals("",getPosition())) 
			TestUtilities.logPassWithScreenshot("Position is cleared");
		if (verifyEquals("",getCharityContact())) 
			TestUtilities.logPassWithScreenshot("Charity Contact is cleared");
		if (verifyEquals("",getCharityAddress1())) 
			TestUtilities.logPassWithScreenshot("Charity Address1 is cleared");
		if (verifyEquals("",getCharityAddress2())) 
			TestUtilities.logPassWithScreenshot("Charity Address2 is cleared");
		if (verifyEquals("",getCharityAddress3())) 
			TestUtilities.logPassWithScreenshot("Charity Address3 is cleared");
		if (verifyEquals("",getPublicityOpportunities())) 
			TestUtilities.logPassWithScreenshot("Publicity Opportunities is cleared");
		if (verifyEquals("",getCharityPurpose())) 
			TestUtilities.logPassWithScreenshot("Charity Purpose is cleared");
		if (verifyEquals("",getCharityActivity())) 
			TestUtilities.logPassWithScreenshot("Charity Activity is cleared");
	}

	public void isAllCharityFieldsValidationMessageCleared() {
		scrollUpDownToElement(webDriver, byCharityPhone, "Charity Phone", -200);
		if(isNotElementVisible(byCharityPhoneValidationMessage,charityPhoneValidationMessage, ExceptionHandling.FAIL)) {
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			TestUtilities.logPassWithScreenshot("Charity Phone validation message is cleared");
		}
	}

}
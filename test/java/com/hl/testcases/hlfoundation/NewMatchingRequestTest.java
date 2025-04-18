package com.hl.testcases.hlfoundation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.hlfoundation.LandingPage;
import com.hl.pages.hlfoundation.NewMatchingRequestPage;
import com.hl.utilities.TestUtilities;

public class NewMatchingRequestTest extends TestBase {

	public LandingPage landingPage;
	public NewMatchingRequestPage newMatchingRequest;
	
	@BeforeClass( groups = {"upload","sanity"})
	void setupClass() {
		executeSQLUpdateStaff("Admin","6 Associate","2022-03-14 00:00:00.000","T");
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		newMatchingRequest=new NewMatchingRequestPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
	}
	
	void SetupC() {
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		newMatchingRequest=new NewMatchingRequestPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
	}

	@Test(priority = 0, enabled = true ,groups = {"upload"})
	public void Test1() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
	}
	
	@Test(priority = 1, enabled = false , groups = {"frontend"})
	public void ValidateName() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.setPreviousCharityRadioPositive();
		newMatchingRequest.ValidateName();
	}

	@Test(priority = 2, enabled = false, groups = {"frontend"})
	public void ValidateOfficeLocation() {
		refreshPage();
		newMatchingRequest.ValidateOfficeLocation();
	}

	@Test(priority = 3, enabled = false, groups = {"frontend"})
	public void ValidateTitle() {
		refreshPage();
		newMatchingRequest.ValidateTitle();
	}

	@Test(priority = 4, enabled = false)
	public void ValidateDonationAmount() {
		refreshPage();
		newMatchingRequest.ValidateDonationAmount();
	}

	@Test(priority = 5, enabled = false)
	public void ValidateMatchingAmount() {
		refreshPage();
		newMatchingRequest.ValidateMatchingAmount();
	}

	@Test(priority = 6, enabled = false)
	public void ValidateDonationDate() {
		refreshPage();
		newMatchingRequest.ValidateDonationDate();
	}

	@Test(priority = 13, enabled = false)
	public void ValidateCharityName() {
		refreshPage();
		newMatchingRequest.ValidateCharityName();
	}
	
	@Test(priority = 14, enabled = false)
	public void ValidateCharityContactEmail() {
		refreshPage();
		newMatchingRequest.ValidateCharityContactEmail();
	}
	
	@Test(priority = 15, enabled = false)
	public void ValidateCharityPhone() {
		refreshPage();
		newMatchingRequest.ValidateCharityPhone();
	}

	@Test(priority = 16, enabled = false)
	public void ValidateWebsite() {
		refreshPage();
		newMatchingRequest.ValidateWebsite();
	}

	@Test(priority = 17, enabled = false)
	public void ValidatePosition() {
		refreshPage();
		newMatchingRequest.ValidatePosition();
	}
	
	@Test(priority = 18, enabled = false)
	public void ValidateCharityContact() {
		refreshPage();
		newMatchingRequest.ValidateCharityContact();
		//softAssert.assertAll();
	}
	
	@Test(priority = 19, enabled = false)
	public void ValidateCharityAddress1() {
		refreshPage();
		newMatchingRequest.ValidateCharityAddress1();
		
	}
	
	@Test(priority = 20, enabled = false)
	public void ValidateCharityAddress2() {
		refreshPage();
		newMatchingRequest.ValidateCharityAddress2();
		
	}
	
	@Test(priority = 21, enabled = false)
	public void ValidateCharityAddress3() {
		refreshPage();
		newMatchingRequest.ValidateCharityAddress3();

	}
	
	@Test(priority = 22, enabled = false)
	public void ValidatePublicityOpportunities() {
		refreshPage();
		newMatchingRequest.ValidatePublicityOpportunities();
		
	}
	
	@Test(priority = 23, enabled = false)
	public void ValidateCharityPurpose() {
		refreshPage();
		newMatchingRequest.ValidateCharityPurpose();
		
	}

	@Test(priority = 24, enabled = false)
	public void ValidateCharityDropDown() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.ValidateCharityDropDown();
	}

	@Test(priority = 25, enabled = false)
	public void Validate_HaveYouDonatedToThisCharityBefore_No() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newMatchingRequest.setPreviousCharityRadioPositive();
		newMatchingRequest.setCharityDropDown(excel.getCellData("MatchingRequest1", "CharityName", 2), ExceptionHandling.FAIL);
		newMatchingRequest.enterInvalidCharityInfo();
		newMatchingRequest.setPreviousCharityRadioNegative();
		newMatchingRequest.isWorkedCharityDivVisible();
		newMatchingRequest.isNotCharityNameDivVisible();
		newMatchingRequest.isCharityNameEnable();
		newMatchingRequest.isAllCharityFieldsCleared();
		newMatchingRequest.isAllCharityFieldsValidationMessageCleared();
		newMatchingRequest.isCreateButtonEnable();
		newMatchingRequest.isClearFormButtonEnable();
		
		newMatchingRequest.setPreviousCharityRadioPositive();
		newMatchingRequest.isSelectPreviousCharityDropDownClear();
		executeSQLDeleteStaffAllMatchingRequests();
	}
	
	@Test(priority = 26, enabled = false)
	public void Verify_HaveYouDonatedToThisCharityBefore_NoIsDefault() {
		refreshPage();
		newMatchingRequest.Verify_PreviousCharityRadioNegativeSelected();
	}
	
	@Test(priority = 27, enabled = false)
	public void Validate_HaveYouDonatedToThisCharityBefore_Yes() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();

		newMatchingRequest.setPreviousCharityRadioNegative();
		newMatchingRequest.setWorkedCharityYesInputField();
		newMatchingRequest.enterCharityInfo("MatchingRequest1");
		
		newMatchingRequest.setPreviousCharityRadioPositive();
		newMatchingRequest.isNotWorkedCharityDivVisible();
		newMatchingRequest.isCharityNameDivVisible();
		newMatchingRequest.isCharityNameDisable();
		newMatchingRequest.isAllCharityFieldsCleared();
		newMatchingRequest.isAllCharityFieldsValidationMessageCleared();
		newMatchingRequest.isCreateButtonDisable();
		newMatchingRequest.isClearFormButtonEnable();
	}
	
	@Test(priority = 28, enabled = false)
	public void Validate_HaveYouWorkedWithThisCharity_No() {
		refreshPage();
		newMatchingRequest.setWorkedCharityYesInputField();
		newMatchingRequest.enterCharityInfo("MatchingRequest1");
		newMatchingRequest.setWorkedCharityNoInputField();
		newMatchingRequest.isWorkedAtLeastaYearMessageVisible();
		newMatchingRequest.isAllCharityFieldsDisabled();
		newMatchingRequest.isAllCharityFieldsCleared();
		newMatchingRequest.isAllCharityFieldsValidationMessageCleared();
		newMatchingRequest.isCreateButtonDisable();
		newMatchingRequest.isClearFormButtonDisable();
	}

	@Test(priority = 29, enabled = false)
	public void Validate_HaveYouWorkedWithThisCharity_Yes() {
		refreshPage();
		newMatchingRequest.setWorkedCharityNoInputField();
		newMatchingRequest.setWorkedCharityYesInputField();
		newMatchingRequest.isNotWorkedAtLeastaYearMessageVisible();
		newMatchingRequest.isAllCharityFieldsEnabled();
		newMatchingRequest.isAllCharityFieldsCleared();
		newMatchingRequest.isAllCharityFieldsValidationMessageCleared();
		newMatchingRequest.isCreateButtonEnable();
		newMatchingRequest.isClearFormButtonEnable();
	}
	
	@Test(priority = 30, enabled = false)
	public void Verify_HaveYouWorkedWithThisCharityForAtLeastAYear_YesIsDefault() {
		refreshPage();
		newMatchingRequest.Verify_WorkedCharityYesInputFieldSelected();
	}
	
	@Test(priority = 31, enabled = false)
	public void Verify_SelectingCharityDropDown_PopulatesCharityFields() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.Verify_SelectingCharityDropDown_PopulatesCharityFields();
	}
	
	@Test(priority = 32, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForApproved_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		//TODO Approve the request Approver1 then Approver2
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newMatchingRequest.isNotPendingRequestMessageVisible("Approved");
	}
	
	@Test(priority = 33, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForRejected_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		//TODO Reject the request Approver1/Approver2 
		executeSQLUpdateAllMatchingRequestsStatus("13");
		refreshPage();
		newMatchingRequest.isNotPendingRequestMessageVisible("Rejected");
	}
	
	@Test(priority = 34, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForCancelled_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		//TODO cancel request by employee for PendingInitialApproval
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		newMatchingRequest.isNotPendingRequestMessageVisible("Cancelled");
		//TODO cancel request by employee for NeedInfo
		executeSQLUpdateAllMatchingRequestsStatus("11");
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		newMatchingRequest.isNotPendingRequestMessageVisible("Cancelled");
	}
	
	@Test(priority = 35, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForPendingInitialApproval_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		newMatchingRequest.isPendingRequestMessageVisible("PendingInitialApproval");
	}
	
	@Test(priority = 36, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForPendingFinalApproval_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		//TODO Approve the request Approver1 
		executeSQLUpdateAllMatchingRequestsStatus("10");
		refreshPage();
		newMatchingRequest.isPendingRequestMessageVisible("Pending Final Approval");
	}
	
	@Test(priority = 37, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForNeedInfo_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		//TODO NeedInfo the request Approver1/Approver2 
		executeSQLUpdateAllMatchingRequestsStatus("11");
		refreshPage();
		newMatchingRequest.isPendingRequestMessageVisible("NeedInfo");
	}
	
	@Test(priority = 41, enabled = false)
	public void Verify_MaxedOutReimbursementRate_NonOfficers() {
		Verify_MaxedOutReimbursementRate("6 Associate","Non-Officers","1000");
	}

	@Test(priority = 42, enabled = false)
	public void Verify_MaxedOutReimbursementRate__SVP_VP_Administrative_Officers() {
		Verify_MaxedOutReimbursementRate("4 Sr Vice President","SVP, VP, Administrative Officers","5000");
	}

	@Test(priority = 43, enabled = false)
	public void Verify_MaxedOutReimbursementRate_Director() {
		Verify_MaxedOutReimbursementRate("3 Director","Director","10000");
	}

	@Test(priority = 44, enabled = false)
	public void Verify_MaxedOutReimbursementRate_MD_SrMD_C_Level() {
		Verify_MaxedOutReimbursementRate("1 Executive Officers","MD, Sr. MD, C-Level","20000");
	}

	public void Verify_MaxedOutReimbursementRate(String managementLevel, String managementLevelName, String amount) {
		executeSQLUpdateStaff("FIN",managementLevel,"2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1",amount);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newMatchingRequest.isMaxedOutMessageVisible(managementLevelName);
		executeSQLUpdateAllMatchingRequestsAmount("100");
		refreshPage();
		newMatchingRequest.isNoMaxedOutMessageVisible(managementLevelName);
	}

	@Test(priority = 51, enabled = false)
	public void Verify_Matched_Allowed_Amount_Non_Officers() {
		Verify_Matched_Allowed_Amount("6 Associate","Non-Officers","100");
	}

	@Test(priority = 52, enabled = false)
	public void Verify_Matched_Allowed_Amount_SVP_VP_Administrative_Officers() {
		Verify_Matched_Allowed_Amount("4 Sr Vice President","SVP, VP, Administrative Officers","500");
	}

	@Test(priority = 53, enabled = false)
	public void Verify_Matched_Allowed_Amount_Director() {
		Verify_Matched_Allowed_Amount("3 Director","Director","1000");
	}

	@Test(priority = 54, enabled = false)
	public void Verify_Matched_Allowed_Amount_MD_SrMD_C_Level() {
		Verify_Matched_Allowed_Amount("1 Executive Officers","MD, Sr. MD, C-Level","2000");
	}

	public void Verify_Matched_Allowed_Amount(String managementLevel, String managementLevelName, String amount) {
		executeSQLUpdateStaff("FIN",managementLevel,"2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.isMatchedAllowedMessageVisible(managementLevelName, "0");
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1",amount);
		newMatchingRequest.isMatchedAllowedMessageVisible(managementLevelName, "0");
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newMatchingRequest.isMatchedAllowedMessageVisible(managementLevelName, amount);
	}

	@Test(priority = 55, enabled = false, groups = {"upload"})
	public void VerifyMonetaryContributionEvidenceDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.uploadCompletedDoc("ValidFile");
		newMatchingRequest.uploadNonprofitDoc("ValidFile");
		newMatchingRequest.clickCreateButton();
		newMatchingRequest.isFileUploadValidationToastMessageVisible();
		if(newMatchingRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Monetary contribution evidence] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Monetary contribution evidence] Doc is NOT required");
		newMatchingRequest.uploadMonetaryDoc("ValidFile");
		newMatchingRequest.isFileUploadingToastMessageVisible();
		newMatchingRequest.isFileUploadedToastMessageVisible();
		newMatchingRequest.clickCreateButton();
		if(newMatchingRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newMatchingRequest.deleteMonetaryDoc();
		newMatchingRequest.isFileDeletedToastMessageVisible();
		newMatchingRequest.isMonetaryContributionEvidenceButtonVisible();
		newMatchingRequest.uploadMonetaryDoc("NonPDFFile");
		newMatchingRequest.isNonPDFFileValidationMessageVisible();
		newMatchingRequest.uploadMonetaryDoc("ValidFile");
		newMatchingRequest.isNotNonPDFFileValidationMessageVisible();
		newMatchingRequest.deleteMonetaryDoc();
		newMatchingRequest.uploadMonetaryDoc("Over100MBFile");
		newMatchingRequest.isOver100MBFileValidationMessageVisible();
		newMatchingRequest.uploadMonetaryDoc("ValidFile");
		newMatchingRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 56, enabled = false, groups = {"upload"})
	public void VerifyCompletedHLCheckRequestDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.uploadMonetaryDoc("ValidFile");
		newMatchingRequest.uploadNonprofitDoc("ValidFile");
		newMatchingRequest.clickCreateButton();
		newMatchingRequest.isFileUploadValidationToastMessageVisible();
		if(newMatchingRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Completed HL check reques] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Completed HL check reques] Doc is NOT required");
		newMatchingRequest.uploadCompletedDoc("ValidFile");
		newMatchingRequest.isFileUploadingToastMessageVisible();
		newMatchingRequest.isFileUploadedToastMessageVisible();
		newMatchingRequest.clickCreateButton();
		if(newMatchingRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newMatchingRequest.deleteCompletedDoc();
		newMatchingRequest.isFileDeletedToastMessageVisible();
		newMatchingRequest.isCompletedHLCheckRequestButtonVisible();
		newMatchingRequest.uploadCompletedDoc("NonPDFFile");
		newMatchingRequest.isNonPDFFileValidationMessageVisible();
		newMatchingRequest.uploadCompletedDoc("ValidFile");
		newMatchingRequest.isNotNonPDFFileValidationMessageVisible();
		newMatchingRequest.deleteCompletedDoc();
		newMatchingRequest.uploadCompletedDoc("Over100MBFile");
		newMatchingRequest.isOver100MBFileValidationMessageVisible();
		newMatchingRequest.uploadCompletedDoc("ValidFile");
		newMatchingRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 57, enabled = false, groups = {"upload"})
	public void VerifyNonProfitStatusEvidenceDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.uploadMonetaryDoc("ValidFile");
		newMatchingRequest.uploadCompletedDoc("ValidFile");
		newMatchingRequest.clickCreateButton();
		newMatchingRequest.isFileUploadValidationToastMessageVisible();
		if(newMatchingRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Non-profit status evidence] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Non-profit status evidence] Doc is NOT required");
		newMatchingRequest.uploadNonprofitDoc("ValidFile");
		newMatchingRequest.isFileUploadingToastMessageVisible();
		newMatchingRequest.isFileUploadedToastMessageVisible();
		newMatchingRequest.clickCreateButton();
		if(newMatchingRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newMatchingRequest.deleteNonprofitDoc();
		newMatchingRequest.isFileDeletedToastMessageVisible();
		newMatchingRequest.isNonprofitStatusEvidenceButtonVisible();
		newMatchingRequest.uploadNonprofitDoc("NonPDFFile");
		newMatchingRequest.isNonPDFFileValidationMessageVisible();
		newMatchingRequest.uploadNonprofitDoc("ValidFile");
		newMatchingRequest.isNotNonPDFFileValidationMessageVisible();
		newMatchingRequest.deleteNonprofitDoc();
		newMatchingRequest.uploadNonprofitDoc("Over100MBFile");
		newMatchingRequest.isOver100MBFileValidationMessageVisible();
		newMatchingRequest.uploadNonprofitDoc("ValidFile");
		newMatchingRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 61, enabled = false)
	public void Verify_PendingInitialApproval_NotAddedToMatchedAmount() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingInitialApproval",-300);
	}

	@Test(priority = 62, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_PendingFinalApproval_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("10");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingFinalApproval",-300);
	}

	@Test(priority = 63, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_NeedInfo_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("11");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "NeedInfo",-300);
	}

	@Test(priority = 64, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Rejected_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("13");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Rejected",-300);
	}

	@Test(priority = 65, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Cancelled_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Cancelled",-300);
	}

	public void Verify_PendingDocumentSubmission_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("15");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingDocumentSubmission",-300);
	}

	@Test(priority = 66, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Approved_AddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		String progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved",-300);
	}

	@Test(priority = 67, enabled = false, dependsOnMethods = { "Verify_Approved_AddedToMatchedAmount" })
	public void Verify_Approved_PreviousYears_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsDate("2022-03-14");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved Previous Year",-300);
		executeSQLUpdateAllMatchingRequestsDate("2023-03-14");
		refreshPage();
		progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved This Year",-300);
	}

	@Test(priority = 68, enabled = false)
	public void Verify_AllApproved_AddedToMatchedAmount() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingInitialApproval",-300);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved",-300);
		
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","100");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Second PendingInitialApproval",-300);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		progressMessage=" $200 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newMatchingRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Second Approved",-300);
		//TODO close toast message
	}
		
	@Test(priority = 71, enabled = false)
	public void Verify_OutOfRangeValidation_Non_Officers() {
		newMatchingRequest.Verify_OutOfRangeValidation("6 Associate","Non-Officers","900");
	}

	@Test(priority = 72, enabled = false)
	public void Verify_OutOfRangeValidation_SVP_VP_Administrative_Officers() {
		newMatchingRequest.Verify_OutOfRangeValidation("4 Sr Vice President","SVP, VP, Administrative Officers","4000");
	}

	@Test(priority = 73, enabled = false)
	public void Verify_OutOfRangeValidation_Director() {
		newMatchingRequest.Verify_OutOfRangeValidation("3 Director","Director","9000");
	}

	@Test(priority = 74, enabled = false)
	public void Verify_OutOfRangeValidation_MD_SrMD_C_Level() {
		newMatchingRequest.Verify_OutOfRangeValidation("1 Executive Officers","MD, Sr. MD, C-Level","12000");
	}


	@Test(priority = 80, enabled = false, groups = {"sanity"})
	public void Verify_createNewMatchingRequest() {
		executeSQLUpdateStaff("FIN","1 Executive Officers","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.createNewMatchingRequest("MatchingRequest1","");
	}
	
	
	public void Verify_WhenCreateButtonIsDisable() {
		//Dropdown is empty
		//altleast one year no
		//there is validation error message
	}
}

package com.hl.testcases.hlfoundation;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.hlfoundation.LandingPage;
import com.hl.pages.hlfoundation.NewGADRequestPage;

public class NewGADRequestTest extends TestBase {

	public LandingPage landingPage;
	public NewGADRequestPage newGADRequest;
	
	@BeforeClass( groups = {"upload","sanity"})
	void setupClass() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000","T");
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		newGADRequest=new NewGADRequestPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
	}
	
	@Test(priority = 0, enabled = false ,groups = {"upload"})
	public void Test1() {
		executeSQLDeleteStaffAllGADRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.ValidateCharityContactEmail();
	}
	
	@Test(priority = 1, enabled = true, groups = {"frontend"}, testName ="Verify if name is validated")
	public void ValidateName() {
		executeSQLDeleteStaffAllGADRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.ValidateName();
	}

	@Test(priority = 2, enabled = false, groups = {"frontend"})
	public void ValidateOfficeLocation() {
		refreshPage();
		newGADRequest.ValidateOfficeLocation();
	}

	@Test(priority = 3, enabled = false, groups = {"frontend"})
	public void ValidateTitle() {
		refreshPage();
		newGADRequest.ValidateTitle();
	}

	@Test(priority = 6, enabled = false)
	public void ValidateActivityDate() {
		refreshPage();
		newGADRequest.ValidateActivityDate();
	}

	@Test(priority = 13, enabled = false)
	public void ValidateCharityName() {
		refreshPage();
		newGADRequest.ValidateCharityName();
	}
	
	@Test(priority = 14, enabled = false)
	public void ValidateCharityContactEmail() {
		refreshPage();
		newGADRequest.ValidateCharityContactEmail();
	}
	
	@Test(priority = 15, enabled = false)
	public void ValidateCharityPhone() {
		refreshPage();
		newGADRequest.ValidateCharityPhone();
	}

	@Test(priority = 16, enabled = false)
	public void ValidateWebsite() {
		refreshPage();
		newGADRequest.ValidateWebsite();
	}

	@Test(priority = 17, enabled = false)
	public void ValidatePosition() {
		refreshPage();
		newGADRequest.ValidatePosition();
	}
	
	@Test(priority = 18, enabled = false)
	public void ValidateCharityContact() {
		refreshPage();
		newGADRequest.ValidateCharityContact();
	}
	
	@Test(priority = 19, enabled = false)
	public void ValidateCharityAddress1() {
		refreshPage();
		newGADRequest.ValidateCharityAddress1();
		
	}
	
	@Test(priority = 20, enabled = false)
	public void ValidateCharityAddress2() {
		refreshPage();
		newGADRequest.ValidateCharityAddress2();
		
	}
	
	@Test(priority = 21, enabled = false)
	public void ValidateCharityAddress3() {
		refreshPage();
		newGADRequest.ValidateCharityAddress3();

	}
	
	@Test(priority = 22, enabled = false)
	public void ValidatePublicityOpportunities() {
		refreshPage();
		newGADRequest.ValidatePublicityOpportunities();
		
	}
	
	@Test(priority = 23, enabled = false)
	public void ValidateCharityPurpose() {
		refreshPage();
		newGADRequest.ValidateCharityPurpose();
		
	}
	
	@Test(priority = 23, enabled = false)
	public void ValidateCharityActivity() {
		refreshPage();
		newGADRequest.ValidateCharityActivity();
		
	}

	@Test(priority = 24, enabled =false )
	public void ValidateCharityDropDown() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.ValidateCharityDropDown();
	}

	@Test(priority = 25, enabled = false)
	public void Validate_HaveYouVolunteeredWithThisCharityBefore_No() {
		executeSQLDeleteStaffAllGADRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.createNewGADRequest("GADRequest1");
		executeSQLUpdateAllMatchingRequestsStatus("7");
		refreshPage();
		
		newGADRequest.Verify_PreviousCharityRadioNegativeSelected();
		
		newGADRequest.setPreviousCharityRadioPositive();
		newGADRequest.setCharityDropDown(excel.getCellData("GADRequest1", "CharityName", 2), ExceptionHandling.FAIL);
		newGADRequest.enterInvalidCharityInfo();
		newGADRequest.setPreviousCharityRadioNegative();
		newGADRequest.isNotCharityNameDivVisible();
		newGADRequest.isCharityNameEnable();
		newGADRequest.isAllCharityFieldsCleared();
		newGADRequest.isAllCharityFieldsValidationMessageCleared();
		newGADRequest.isCreateButtonEnable();
		newGADRequest.isClearFormButtonEnable();
		
		newGADRequest.setPreviousCharityRadioPositive();
		newGADRequest.isSelectPreviousCharityDropDownClear();
		executeSQLDeleteStaffAllGADRequests();
	}

	@Test(priority = 26, enabled = false)
	public void Verify_HaveYouVolunteeredWithThisCharityBefore_NoIsDefault() {
		landingPage.clickCreateGADRequest();
		refreshPage();
		newGADRequest.Verify_PreviousCharityRadioNegativeSelected();
	}
	
	@Test(priority = 27, enabled = false)
	public void Validate_HaveYouVolunteeredWithThisCharityBefore_Yes() {
		executeSQLDeleteStaffAllGADRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.createNewGADRequest("GADRequest1");
		executeSQLUpdateAllMatchingRequestsStatus("7");
		refreshPage();

		newGADRequest.setPreviousCharityRadioNegative();
		newGADRequest.enterCharityInfo("GADRequest1");
		
		newGADRequest.setPreviousCharityRadioPositive();
		newGADRequest.isCharityNameDivVisible();
		newGADRequest.isCharityNameDisable();
		newGADRequest.isAllCharityFieldsCleared();
		newGADRequest.isAllCharityFieldsValidationMessageCleared();
		newGADRequest.isCreateButtonDisable();
		newGADRequest.isClearFormButtonEnable();
	}

	@Test(priority = 31, enabled = false)
	public void Verify_SelectingCharityDropDown_PopulatesCharityFields() {
		executeSQLDeleteStaffAllGADRequests();
		landingPage.clickCreateGADRequest();
		newGADRequest.Verify_SelectingCharityDropDown_PopulatesCharityFields();
	}
/*	
	@Test(priority = 32, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForApproved_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		//TODO Approve the request Approver1 then Approver2
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newGADRequest.isNotPendingRequestMessageVisible("Approved");
	}
	
	@Test(priority = 33, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForRejected_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		//TODO Reject the request Approver1/Approver2 
		executeSQLUpdateAllMatchingRequestsStatus("13");
		refreshPage();
		newGADRequest.isNotPendingRequestMessageVisible("Rejected");
	}
	
	@Test(priority = 34, enabled = false)
	public void Verify_PendingRequestMessage_NotDisplayed_ForCancelled_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		//TODO cancel request by employee for PendingInitialApproval
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		newGADRequest.isNotPendingRequestMessageVisible("Cancelled");
		//TODO cancel request by employee for NeedInfo
		executeSQLUpdateAllMatchingRequestsStatus("11");
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		newGADRequest.isNotPendingRequestMessageVisible("Cancelled");
	}
	
	@Test(priority = 35, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForPendingInitialApproval_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		newGADRequest.isPendingRequestMessageVisible("PendingInitialApproval");
	}
	
	@Test(priority = 36, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForPendingFinalApproval_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		//TODO Approve the request Approver1 
		executeSQLUpdateAllMatchingRequestsStatus("10");
		refreshPage();
		newGADRequest.isPendingRequestMessageVisible("Pending Final Approval");
	}
	
	@Test(priority = 37, enabled = false)
	public void Verify_PendingRequestMessage_Displayed_ForNeedInfo_Request() {
		executeSQLUpdateStaff("FIN","4 Sr Vice President","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		//TODO NeedInfo the request Approver1/Approver2 
		executeSQLUpdateAllMatchingRequestsStatus("11");
		refreshPage();
		newGADRequest.isPendingRequestMessageVisible("NeedInfo");
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
		newGADRequest.createnewGADRequest("MatchingRequest1",amount);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newGADRequest.isMaxedOutMessageVisible(managementLevelName);
		executeSQLUpdateAllMatchingRequestsAmount("100");
		refreshPage();
		newGADRequest.isNoMaxedOutMessageVisible(managementLevelName);
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
		newGADRequest.isMatchedAllowedMessageVisible(managementLevelName, "0");
		newGADRequest.createnewGADRequest("MatchingRequest1",amount);
		newGADRequest.isMatchedAllowedMessageVisible(managementLevelName, "0");
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		newGADRequest.isMatchedAllowedMessageVisible(managementLevelName, amount);
	}

	@Test(priority = 55, enabled = false, groups = {"upload"})
	public void VerifyMonetaryContributionEvidenceDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.uploadCompletedDoc("ValidFile");
		newGADRequest.uploadNonprofitDoc("ValidFile");
		newGADRequest.clickCreateButton();
		newGADRequest.isFileUploadValidationToastMessageVisible();
		if(newGADRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Monetary contribution evidence] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Monetary contribution evidence] Doc is NOT required");
		newGADRequest.uploadMonetaryDoc("ValidFile");
		newGADRequest.isFileUploadingToastMessageVisible();
		newGADRequest.isFileUploadedToastMessageVisible();
		newGADRequest.clickCreateButton();
		if(newGADRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newGADRequest.deleteMonetaryDoc();
		newGADRequest.isFileDeletedToastMessageVisible();
		newGADRequest.isMonetaryContributionEvidenceButtonVisible();
		newGADRequest.uploadMonetaryDoc("NonPDFFile");
		newGADRequest.isNonPDFFileValidationMessageVisible();
		newGADRequest.uploadMonetaryDoc("ValidFile");
		newGADRequest.isNotNonPDFFileValidationMessageVisible();
		newGADRequest.deleteMonetaryDoc();
		newGADRequest.uploadMonetaryDoc("Over100MBFile");
		newGADRequest.isOver100MBFileValidationMessageVisible();
		newGADRequest.uploadMonetaryDoc("ValidFile");
		newGADRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 56, enabled = false, groups = {"upload"})
	public void VerifyCompletedHLCheckRequestDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.uploadMonetaryDoc("ValidFile");
		newGADRequest.uploadNonprofitDoc("ValidFile");
		newGADRequest.clickCreateButton();
		newGADRequest.isFileUploadValidationToastMessageVisible();
		if(newGADRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Completed HL check reques] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Completed HL check reques] Doc is NOT required");
		newGADRequest.uploadCompletedDoc("ValidFile");
		newGADRequest.isFileUploadingToastMessageVisible();
		newGADRequest.isFileUploadedToastMessageVisible();
		newGADRequest.clickCreateButton();
		if(newGADRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newGADRequest.deleteCompletedDoc();
		newGADRequest.isFileDeletedToastMessageVisible();
		newGADRequest.isCompletedHLCheckRequestButtonVisible();
		newGADRequest.uploadCompletedDoc("NonPDFFile");
		newGADRequest.isNonPDFFileValidationMessageVisible();
		newGADRequest.uploadCompletedDoc("ValidFile");
		newGADRequest.isNotNonPDFFileValidationMessageVisible();
		newGADRequest.deleteCompletedDoc();
		newGADRequest.uploadCompletedDoc("Over100MBFile");
		newGADRequest.isOver100MBFileValidationMessageVisible();
		newGADRequest.uploadCompletedDoc("ValidFile");
		newGADRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 57, enabled = false, groups = {"upload"})
	public void VerifyNonProfitStatusEvidenceDocument() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.uploadMonetaryDoc("ValidFile");
		newGADRequest.uploadCompletedDoc("ValidFile");
		newGADRequest.clickCreateButton();
		newGADRequest.isFileUploadValidationToastMessageVisible();
		if(newGADRequest.isFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("[Non-profit status evidence] Doc is required");
		else
			TestUtilities.logFailedWithScreenshot("[Non-profit status evidence] Doc is NOT required");
		newGADRequest.uploadNonprofitDoc("ValidFile");
		newGADRequest.isFileUploadingToastMessageVisible();
		newGADRequest.isFileUploadedToastMessageVisible();
		newGADRequest.clickCreateButton();
		if(newGADRequest.isNotFileUploadValidationMessageVisible("NoDisplay"))
			TestUtilities.logPassWithScreenshot("File Upload Validation Message is cleared after all 3 files are uploaded");
		else
			TestUtilities.logFailedWithScreenshot("File Upload Validation Message is NOT cleared after all 3 files are uploaded");
		newGADRequest.deleteNonprofitDoc();
		newGADRequest.isFileDeletedToastMessageVisible();
		newGADRequest.isNonprofitStatusEvidenceButtonVisible();
		newGADRequest.uploadNonprofitDoc("NonPDFFile");
		newGADRequest.isNonPDFFileValidationMessageVisible();
		newGADRequest.uploadNonprofitDoc("ValidFile");
		newGADRequest.isNotNonPDFFileValidationMessageVisible();
		newGADRequest.deleteNonprofitDoc();
		newGADRequest.uploadNonprofitDoc("Over100MBFile");
		newGADRequest.isOver100MBFileValidationMessageVisible();
		newGADRequest.uploadNonprofitDoc("ValidFile");
		newGADRequest.isNotOver100MBFileValidationMessageVisible();
		refreshPage();
	}
	
	@Test(priority = 61, enabled = false)
	public void Verify_PendingInitialApproval_NotAddedToMatchedAmount() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingInitialApproval",-300);
	}

	@Test(priority = 62, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_PendingFinalApproval_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("10");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingFinalApproval",-300);
	}

	@Test(priority = 63, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_NeedInfo_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("11");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "NeedInfo",-300);
	}

	@Test(priority = 64, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Rejected_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("13");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Rejected",-300);
	}

	@Test(priority = 65, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Cancelled_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("14");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Cancelled",-300);
	}

	public void Verify_PendingDocumentSubmission_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("15");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingDocumentSubmission",-300);
	}

	@Test(priority = 66, enabled = false, dependsOnMethods = { "Verify_PendingInitialApproval_NotAddedToMatchedAmount" })
	public void Verify_Approved_AddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		String progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved",-300);
	}

	@Test(priority = 67, enabled = false, dependsOnMethods = { "Verify_Approved_AddedToMatchedAmount" })
	public void Verify_Approved_PreviousYears_NotAddedToMatchedAmount() {
		executeSQLUpdateAllMatchingRequestsDate("2022-03-14");
		refreshPage();
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved Previous Year",-300);
		executeSQLUpdateAllMatchingRequestsDate("2023-03-14");
		refreshPage();
		progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved This Year",-300);
	}

	@Test(priority = 68, enabled = false)
	public void Verify_AllApproved_AddedToMatchedAmount() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		String progressMessage=" $0 matched out of $1000 allowed for 2023 ";
		By byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "PendingInitialApproval",-300);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		progressMessage=" $100 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Approved",-300);
		
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","100");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Second PendingInitialApproval",-300);
		executeSQLUpdateAllMatchingRequestsStatus("12");
		refreshPage();
		progressMessage=" $200 matched out of $1000 allowed for 2023 ";
		byProgressMessage = By.xpath("//*[text()='"+progressMessage+"']");
		newGADRequest.isValidationMessageVisible(byProgressMessage,progressMessage, "Second Approved",-300);
		//TODO close toast message
	}
		
	@Test(priority = 71, enabled = false)
	public void Verify_OutOfRangeValidation_Non_Officers() {
		newGADRequest.Verify_OutOfRangeValidation("6 Associate","Non-Officers","900");
	}

	@Test(priority = 72, enabled = false)
	public void Verify_OutOfRangeValidation_SVP_VP_Administrative_Officers() {
		newGADRequest.Verify_OutOfRangeValidation("4 Sr Vice President","SVP, VP, Administrative Officers","4000");
	}

	@Test(priority = 73, enabled = false)
	public void Verify_OutOfRangeValidation_Director() {
		newGADRequest.Verify_OutOfRangeValidation("3 Director","Director","9000");
	}

	@Test(priority = 74, enabled = false)
	public void Verify_OutOfRangeValidation_MD_SrMD_C_Level() {
		newGADRequest.Verify_OutOfRangeValidation("1 Executive Officers","MD, Sr. MD, C-Level","12000");
	}


	@Test(priority = 80, enabled = false, groups = {"sanity"})
	public void Verify_createnewGADRequest() {
		executeSQLUpdateStaff("FIN","1 Executive Officers","2022-03-14 00:00:00.000");
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newGADRequest.createnewGADRequest("MatchingRequest1","");
	}
	
	
	public void Verify_WhenCreateButtonIsDisable() {
		//Dropdown is empty
		//altleast one year no
		//there is validation error message
	}
	*/
}

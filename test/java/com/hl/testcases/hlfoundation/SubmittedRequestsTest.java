package com.hl.testcases.hlfoundation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.hlfoundation.LandingPage;
import com.hl.pages.hlfoundation.NewMatchingRequestPage;
import com.hl.pages.hlfoundation.SubmittedRequestsPage;
import com.hl.utilities.TestUtilities;

public class SubmittedRequestsTest extends TestBase {

	public LandingPage landingPage;
	public SubmittedRequestsPage submittedRequestsPage;
	
	@BeforeClass( groups = {"upload","sanity"})
	void setupClass() {
		executeSQLUpdateStaff("Admin","6 Associate","2022-03-14 00:00:00.000","T");
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		submittedRequestsPage=new SubmittedRequestsPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
	}
	
	@Test(priority = 0, enabled = true ,groups = {"upload"})
	public void Test1() {
		//executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickSubmittedRequests();
		submittedRequestsPage.clickMatchingButton();
		submittedRequestsPage.verifyData("New", "Pending");
		submittedRequestsPage.clickUpdateButton();
	}
	/*
	@Test(priority = 1, enabled = true, groups = {"frontend"})
	public void ValidateName() {
		executeSQLDeleteStaffAllMatchingRequests();
		landingPage.clickCreateMatchingRequest();
		newMatchingRequest.setPreviousCharityRadioPositive();
		newMatchingRequest.ValidateName();
	}
*/
}

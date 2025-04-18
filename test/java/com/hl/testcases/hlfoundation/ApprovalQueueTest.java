package com.hl.testcases.hlfoundation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.hlfoundation.ApprovalQueuePage;
import com.hl.pages.hlfoundation.LandingPage;
import com.hl.pages.hlfoundation.NewMatchingRequestPage;
import com.hl.pages.hlfoundation.SubmittedRequestsPage;
import com.hl.utilities.TestUtilities;

public class ApprovalQueueTest extends TestBase {

	public LandingPage landingPage;
	public ApprovalQueuePage approvalQueuePage;
	
	@BeforeClass( groups = {"upload","sanity"})
	void setupClass() {
		executeSQLUpdateStaff("Admin","6 Associate","2022-03-14 00:00:00.000","T");
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		approvalQueuePage=new ApprovalQueuePage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
	}
	
	@Test(priority = 0, enabled = true ,groups = {"upload"})
	public void Test1() {
		landingPage.clickApprovalQueue();
		approvalQueuePage.setGlobalFilter(executeSQLSelectName());
		approvalQueuePage.clickReviewButton();
		//approvalQueuePage.selectAction("Approved");
		approvalQueuePage.selectAction("Rejected");
		//approvalQueuePage.selectAction("Need Info");
		approvalQueuePage.setTextArea("Approver2 Rejected");
		approvalQueuePage.clickSubmitButton();
	}
}

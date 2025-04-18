package com.hl.testcases.companydirectory;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.companydirectory.SearchPage;
import com.hl.pages.companydirectory.SidebarPage;
import com.hl.utilities.TestUtilities;

public class SearchTest extends TestBase {

	public SearchPage searchPage;
	public SidebarPage sidebarPage;

	@BeforeClass
	void SetupClass() {
		String testURL = config.getProperty("baseurl_CompanyDirectory");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		searchPage = new SearchPage();
		sidebarPage = new SidebarPage();
	}

	@Test(dataProviderClass = TestUtilities.class, dataProvider = "dp", priority=1)
	void SearchByEmployeeName(Hashtable<String, String> data) throws Throwable {
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("SKIPPED the test");
		}
		sidebarPage.clickSidebarButton();
		sidebarPage.clickSearchButton();
		searchPage.clickClearButton();
		searchPage.selectTableViewRadioButton();
		searchPage.setEmployeeName(data.get("EmployeeName"));
		searchPage.clickSearchButton();
		searchPage.verifyNamesFromTable(data.get("EmployeeName"));
		searchPage.selectCardViewRadioButton();
		searchPage.verifyNamesFromCard(data.get("EmployeeName"));
	}

	@Test(priority = 2)
	void SearchByOffice() throws InterruptedException {
		sidebarPage.clickSidebarButton();
		sidebarPage.clickSearchButton();
		searchPage.clickClearButton();
		Thread.sleep(2000);
		searchPage.setOffice("Amsterdam");
		searchPage.clickSearchButton();
		Thread.sleep(2000);
		searchPage.selectCardViewRadioButton();
		Thread.sleep(2000);
		searchPage.selectTableViewRadioButton();
		Thread.sleep(2000);
	}

	@Test(priority = 0)
	void SearchByStaffType() throws Exception {
		sidebarPage.clickSidebarButton();
		sidebarPage.clickSearchButton();
		searchPage.clickClearButton();
		searchPage.setStaffType("Corporate",ExceptionHandling.WARNING);
		searchPage.clickSearchButton();
		Thread.sleep(2000);
		searchPage.selectCardViewRadioButton();
		Thread.sleep(2000);
		searchPage.selectTableViewRadioButton();
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	void SearchByNotaryOnly() throws Exception {
		sidebarPage.clickSidebarButton();
		sidebarPage.clickSearchButton();
		Thread.sleep(10000);
		searchPage.checkNotaryOnlyCheckBox();
		Thread.sleep(2000);
		searchPage.unCheckNotaryOnlyCheckBox();
		Thread.sleep(2000);

	}

}

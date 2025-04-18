package com.hl.testcases.companydirectory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.pages.companydirectory.SidebarPage;
import com.hl.base.TestBase;

public class SidebarTest extends TestBase {

	public SidebarPage sidebarPage;

	@BeforeClass(groups = { "regression", "smoke" })
	public void setupClass() {
		String testURL = config.getProperty("baseurl_companydirectory");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		sidebarPage = new SidebarPage();
	}

	@Test(priority=1,groups = { "regression" })
	public void clickProfile() throws InterruptedException {
		Thread.sleep(3000);
		sidebarPage.clickSidebarButton();
		Thread.sleep(3000);
		sidebarPage.clickProfileButton();
		Thread.sleep(3000);
	}

	@Test(priority=2,groups = { "smoke" })
	public void clickSearch() throws InterruptedException {
		Thread.sleep(3000);
		sidebarPage.clickSidebarButton();
		Thread.sleep(3000);
		sidebarPage.clickSearchButton();
		Thread.sleep(3000);
	}

	@Test(priority=3,groups = { "regression", "smoke" })
	public void clickReports() throws InterruptedException {
		Thread.sleep(3000);
		sidebarPage.clickSidebarButton();
		Thread.sleep(3000);
		sidebarPage.clickReportsButton();
		Thread.sleep(3000);
	}

	@Test(priority=4,groups = { "regression", "smoke" })
	public void clickLanguageDropdown() throws InterruptedException {
		Thread.sleep(3000);
		sidebarPage.clickSidebarButton();
		Thread.sleep(3000);
		sidebarPage.clickLanguageDropdown();
		Thread.sleep(3000);
		sidebarPage.clickSidebarCloseButton();
	}
}

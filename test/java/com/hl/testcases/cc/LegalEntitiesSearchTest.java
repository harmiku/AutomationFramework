package com.hl.testcases.cc;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.cc.LegalEntitiesPage;
import com.hl.utilities.TestUtilities;

public class LegalEntitiesSearchTest extends TestBase {
	
	public LegalEntitiesPage legalEntitiesPage;
	
	@BeforeClass
	void SetupClass() {
		String testURL = config.getProperty("baseurl_CC");
		String password = config.getProperty("password");
		driver.get("https://conflictscheck-test.hl.com/legal-entities");
		try {
			Thread.sleep(5000);
			TestUtilities.copyPaste("harmik.uchian@hl.com");
			Thread.sleep(1000);
			TestUtilities.copyPaste(password);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.get("https://conflictscheck-test.hl.com/legal-entities");
		try {
			Thread.sleep(5000);
			TestUtilities.copyPaste("harmik.uchian@hl.com");
			Thread.sleep(1000);
			TestUtilities.copyPaste(password);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		longWait.until(ExpectedConditions.urlToBe("https://conflictscheck-test.hl.com/legal-entities"));
		log.debug("Navigated to : " + testURL);
		legalEntitiesPage = new LegalEntitiesPage();
	}
	
	@Test
	public void SearchByEntityName() {
		legalEntitiesPage.setEntityNameFilter("testharmik");
		legalEntitiesPage.clickSearchButton();
	}
}

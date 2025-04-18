package com.hl.testcases.cc;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.cc.LegalEntitiesPage;
import com.hl.pages.cc.SearchPage;


public class LegalEntitiesSearchTest extends TestBase {
	
	public LegalEntitiesPage legalEntitiesPage;
	
	@BeforeClass
	void SetupClass() {
		String testURL = config.getProperty("baseurl_CC");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		legalEntitiesPage = new LegalEntitiesPage();
	}
	
	@Test
	public void SearchByEntityName() {
		legalEntitiesPage.setEntityNameFilter("testharmik");
		legalEntitiesPage.clickSearchButton();
		legalEntitiesPage.clickSearchButton();
	}
}

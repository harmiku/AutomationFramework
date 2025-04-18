package com.hl.pages.cc;

import org.openqa.selenium.By;

import com.hl.base.TestBase;

public class LegalEntitiesPage extends TestBase {

	By byEntityNameFilter = By.xpath("//input[@formcontrolname='parameterOne']");

	public boolean setEntityNameFilter(String entityName) {
		return setTextBoxText(byEntityNameFilter, entityName, "EntityName Filter",ExceptionHandling.STOP);
	}
	
	By bySearchButton = By.xpath("//button[@aria-label='Search']");

	public void clickSearchButton() {
		clickButton(bySearchButton, "Search");
	}
}

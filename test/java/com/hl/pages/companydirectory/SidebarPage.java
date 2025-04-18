package com.hl.pages.companydirectory;

import org.openqa.selenium.By;
import com.hl.base.TestBase;

public class SidebarPage extends TestBase {
	
	//WebDriver webDriver;
	
	public SidebarPage() {
		//webDriver = driver;
	}
	
	By bySidebarButton = By.xpath("//p-button/button");
	public void clickSidebarButton() {
		clickButton(bySidebarButton, "Sidebar");
	}
	
	By bySidebarCloseButton = By.xpath("//button[contains(@class,'p-sidebar-close')]");
	public void clickSidebarCloseButton() {
		clickButton(bySidebarCloseButton, "Sidebar Close");
	}
	
	By byProfileButton = By.xpath("//*[text()='Profile']");
	public void clickProfileButton() {
		clickButton(byProfileButton, "Profile");
	}
	
	By bySearchButton = By.xpath("//*[text()='Search']");
	public void clickSearchButton() {
		clickButton(bySearchButton, "Search");
	}
	
	By byReportsButton = By.xpath("//*[text()='Reports']");
	public void clickReportsButton() {
		clickButton(byReportsButton, "Reports Button");
	}
	
	By byLanguageDropdown = By.id("languageDropdown");
	public void clickLanguageDropdown() {
		clickButton(byLanguageDropdown, "Language Dropdown");
	}

	//List<WebElement> listOfLanguages = webDriver.findElements(By.xpath("//li"));
}

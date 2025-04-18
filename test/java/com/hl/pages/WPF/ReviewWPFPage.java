package com.hl.pages.WPF;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.hl.base.TestBase;

public class ReviewWPFPage extends TestBase {

	By byRequiredDocumentStatusDropdown = By.xpath("//td[contains(@class,'required')]//following-sibling::*//select");

	public List<WebElement> getRequiredDocumentStatusDropdownElements() {
		List<WebElement> elements;
		elements = getWebElements(byRequiredDocumentStatusDropdown, "Status Dropdown");
		return elements;
	}

	public void setRequiredDocumentStatusDropdown(WebElement element, String status) {
		selectDropDownByVisibleText(element, status, "Status");
	}
	
	By byApproveButton = By.xpath("//button[text()='Approve ']");

	public void clickApproveButton() {
		scrollUpDownToElement(driver, byApproveButton,"APPROVE Button");
		clickButton(byApproveButton, "APPROVE");
	}

}

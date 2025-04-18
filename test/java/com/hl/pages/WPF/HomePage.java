package com.hl.pages.WPF;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hl.base.TestBase;

public class HomePage extends TestBase {

	By byStatusDropdown = By.id("wpfStatusFilter");

	public void setStatusDropdown(String status) {
		selectDropDownByVisibleText(byStatusDropdown, status, "WPF Status");
	}

	By byEngagementNumberFilter = By.xpath("//th[1]/input");

	public void setEngagementNumberFilter(String engagementNumber) {
		setTextBoxText(byEngagementNumberFilter, engagementNumber, "Engagement Number Filter");
	}

	public String getEngagementNumber(int rowNumber) {
		By byEngagementNumber = By.xpath("//tbody/tr[" + rowNumber + "]/td[1]/button");
		return getElementText(byEngagementNumber, "Engagement Number");
	}

	public String getFirstEngagementNumber() {
		return getEngagementNumber(1);
	}

	By byFirstCreateButton = By.xpath("//button[text()='Create']");

	public void clickFirstCreateButton() {
		clickButton(byFirstCreateButton, "Create");
	}

	// By byEngagementNumber = By.xpath("//tbody/tr/td/span/a[text()='" +
	// engagementNumber + "']");
	// By byEngagementNumber1 = By.xpath("//tbody/tr/td[*[*[contains(text(),'" +
	// engagementNumber + "')]]]");

	public String getEngagementName(String engagementNumber) {
		By byEngagementName = By
				.xpath("//tbody/tr/td[*[*[contains(text(),'" + engagementNumber + "')]]]/following-sibling::td[1]");
		return getElementText(byEngagementName, "Engagement Name");
	}

	public String getClientName(String engagementNumber) {
		By byClientName = By
				.xpath("//tbody/tr/td[*[*[contains(text(),'" + engagementNumber + "')]]]/following-sibling::td[2]");
		return getElementText(byClientName, "Client Name");
	}

	public void clickCreateButton(String engagementNumber) {
		By byCreateButton = By.xpath("//tbody/tr/td[*[contains(text(),'" + engagementNumber
				+ "')]]/following-sibling::*/button[text()='Create']");
		sendKeysToButton(byCreateButton, "Create", "Keys.RETURN");
		/*
		 * if (isElementVisible(byCreateButton, "Create Button")) {
		 * wait.until(ExpectedConditions.elementToBeClickable(byCreateButton));
		 * Thread.sleep(500); getWebElement(byCreateButton,
		 * "Create Button").sendKeys(Keys.RETURN); log.info("Clicked Create Button");
		 * test.log(LogStatus.INFO, "Clicked Create Button"); }
		 */
	}

	public void clickReviewApproveButton(String engagementNumber) {
		By byReviewApproveButton = By.xpath("//tbody/tr/td[*[contains(text(),'" + engagementNumber
				+ "')]]/following-sibling::*/button[text()='Review/Approve']");
		sendKeysToButton(byReviewApproveButton, "Review/Approve", "Keys.RETURN");
	}

	public void clickReviewButton(String engagementNumber) {
		By byReviewButton = By.xpath("//tbody/tr/td[*[contains(text(),'" + engagementNumber
				+ "')]]/following-sibling::*/button[text()='Review']");
		sendKeysToButton(byReviewButton, "Review", "Keys.RETURN");
	}

	public void waitForSpinnerToHide() {
		try {
			Thread.sleep(5000);
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='spinner'
			// and contains(@style,'display: block')]")));
			longWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@class='spinner' and contains(@style,'display: none')]")));
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	By byPrincipalManager = By.xpath("//form//input");

	public void setPrincipalManager(String principalManager) {
		scrollUpDownToElement(driver, byPrincipalManager, "Principal Manager", -200);
		setTextBoxText(byPrincipalManager, principalManager, "Principal/Manager");
	}

	By byApproveButton = By.xpath("//button[text()='Approve']");

	public void clickApproveButton() {
		scrollUpDownToElement(driver, byApproveButton, "Approve Button");
		clickButton(byApproveButton, "Approve");
	}

	By byGoToHomePageButton = By.xpath("//button[text()='Go to home page']");

	public void clickGoToHomePageButton() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id='SubmitNotificationPrinciple' and contains(@style,'display: block')]")));
		clickButton(byGoToHomePageButton, "Go to home page");
	}

}

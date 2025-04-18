package com.hl.pages.WPF;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hl.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CoverSheetPage extends TestBase {

	By byGuidelines = By.xpath("//*[contains(text(),'guidelines')]");

	public void checkGuidelines() {
		if (isElementVisible(byGuidelines, "Guidelines check box")) {
//			try {
			// Thread.sleep(500);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[@id='guidelinespopup' and contains(@style,'display: block')]")));
			wait.until(ExpectedConditions.elementToBeClickable(byGuidelines));
			clickElement(byGuidelines, "Guidelines check box");
			// new Actions(driver).moveToElement(getWebElement(byGuidelines, "Guidelines
			// check box")).click().perform();
			log.info("Checked Guidelines check box");
			test.log(LogStatus.INFO, "Checked Guidelines check box");
		}
	}

	By byDocumentNameAll = By
			.xpath("//i[contains(@id,'upload')]/parent::*//preceding-sibling::label");
	
	public List<WebElement> getDocumentNameAllElements() {
		List<WebElement> elements = new ArrayList<WebElement>();
		if (isNotElementVisible(By.xpath("//h1[text()='No Documents Available']"),"No Documents Available")) {
			elements = getWebElements(byDocumentNameAll, "Document Name");
		}
		return elements;
	}
	
	By byShowFileUploadDialogAllFiles = By
			.xpath("//label//following-sibling::*//i[contains(@id,'upload')]");

	public List<WebElement> getShowFileUploadDialogAllFilesElements() {
		List<WebElement> elements = new ArrayList<WebElement>();
		if (isNotElementVisible(By.xpath("//h1[text()='No Documents Available']"),"No Documents Available")) {
			elements = getWebElements(byShowFileUploadDialogAllFiles, "Show File Upload Dialog");
		}
		return elements;
	}

	By byShowFileUploadDialogRequiredFiles = By
			.xpath("//label[contains(@class,'required')]//following-sibling::*//i[contains(@id,'upload')]");

	public List<WebElement> getShowFileUploadDialogRequiredFilesElements() {
		List<WebElement> elements = new ArrayList<WebElement>();
		if (isNotElementVisible(By.xpath("//h1[text()='No Documents Available']"),"No Documents Available")) {
			elements = getWebElements(byShowFileUploadDialogRequiredFiles, "Show File Upload Dialog");
		}
		return elements;
	}

	By byShowFileUploadDialogNotRequiredFiles = By
			.xpath("//label[contains(@class,'required')]//following-sibling::*//i[contains(@id,'upload')]");

	public List<WebElement> getShowFileUploadDialogNotRequiredFilesElements() {
		List<WebElement> elements = new ArrayList<WebElement>();
		if (isNotElementVisible(By.xpath("//h1[text()='No Documents Available']"),"No Documents Available")) {
			elements = getWebElements(byShowFileUploadDialogNotRequiredFiles, "Show File Upload Dialog");
		}
		return elements;
	}

	By byDocumentTabs = By.xpath("//div[contains(@class,'doc-section')]//ul/li");

	public List<WebElement> getDocumentTabs() {
		List<WebElement> elements;
		elements = getWebElements(byDocumentTabs, "Document Tabs");
		return elements;
	}

	By byFileBrowseButton = By.id("fileBrowseButton");

	public void clickSelectFilesToBeUploadedButton() {
		clickButton(byFileBrowseButton, "Select files to be uploaded...");
	}

	By byUploadFilesButton = By.id("uploadFiles");

	public void clickUploadFilesButton() {
		clickButton(byUploadFilesButton, "Upload Files");
	}

	By byContinueButton = By.xpath("//*[text()='Continue']");

	public void clickContinueButton() {
		clickButton(byContinueButton, "CONTINUE");
	}

	By byReviewerDropdown = By.id("contactId");

	public void setReviewerDropdown(String reviewer) {
		// selectDropDownByVisibleText(byReviewerDropdown, reviewer, "'Send Review
		// Request To' Dropdown");
		scrollUpDownToElement(driver, byReviewerDropdown, "Reviewer Dropdown");
		selectDropDownByIndex(byReviewerDropdown, 1, "'Send Review Request To'");
	}

	By bySubmitCoverSheetButton = By.xpath("//button[text()='Submit']");

	public void clickSubmitCoverSheetButton() {
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='intialSubmitWarning'
		// and contains(@style,'display: block')]")));
		clickButton(bySubmitCoverSheetButton, "SUBMIT Cover Sheet");
	}

	By bySubmitWPFButton = By.xpath("(//button[text()='Submit'])[2]");
	// //descendant::button[text()='Submit'][2]

	public void clickSubmitWPFButton() {
		clickButton(bySubmitWPFButton, "SUBMIT WPF");
	}

	By byCloseButton = By.xpath("//*[@id='fileUploadDialogContainer']//button[text()='Close']");

	public void clickCloseButton() {
		scrollUpDownToElement(driver, byCloseButton, "CLOSE Button",-200);
		// Thread.sleep(1000);
		clickButton(byCloseButton, "CLOSE");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byCloseButton));
	}

	By byGoToHomePageButton = By.xpath("//button[text()='Go to home page']");

	public void clickGoToHomePageButton() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id='SubmitNotification' and contains(@style,'display: block')]")));
		clickButton(byGoToHomePageButton, "GO TO HOME PAGE");
	}
}

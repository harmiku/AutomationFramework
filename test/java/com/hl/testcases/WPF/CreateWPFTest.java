package com.hl.testcases.WPF;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.WPF.CoverSheetPage;
import com.hl.pages.WPF.HomePage;
import com.hl.pages.WPF.ReviewWPFPage;
import com.hl.utilities.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class CreateWPFTest extends TestBase {

	public HomePage homePage;
	public CoverSheetPage coverSheetPage;
	public ReviewWPFPage reviewWPFPage;

	@BeforeMethod
	void SetupClass() {
		String testURL = config.getProperty("baseurl_WPF");
		driver.get(testURL);
		log.debug("Navigated to : " + testURL);
		homePage = new HomePage();
		coverSheetPage = new CoverSheetPage();
		reviewWPFPage = new ReviewWPFPage();
		homePage.waitForSpinnerToHide();
	}

	@Test(priority = 0, enabled = false)
	public void Upload_Corrupted_File_For_All_Documents() {
		String engagementNumber;

		homePage.setStatusDropdown("Pending");
		engagementNumber = homePage.getFirstEngagementNumber();
		excel.setCellData("WPF", "Eng1", 2, engagementNumber);
		Upload_Invalid_File_For_All_Documents("Corrupted", engagementNumber);
		System.out.println("End of Corrupted");
	}

	@Test(priority = 1, enabled = false)
	public void Upload_Password_Protected_File_For_All_Documents() {
		String engagementNumber;

		homePage.setStatusDropdown("Pending");
		engagementNumber = homePage.getFirstEngagementNumber();
		excel.setCellData("WPF", "Eng1", 2, engagementNumber);
		Upload_Invalid_File_For_All_Documents("Password_Protected", engagementNumber);
	}

	@Test(priority = 2, enabled = false)
	public void Upload_Over_Size_File_For_All_Documents() {
		String engagementNumber;

		homePage.setStatusDropdown("Pending");
		engagementNumber = homePage.getFirstEngagementNumber();
		excel.setCellData("WPF", "Eng1", 2, engagementNumber);
		Upload_Invalid_File_For_All_Documents("Over_Size", engagementNumber);
	}

	@Test(priority = 3, enabled = false)
	public void Upload_Duplicate_File_For_All_Documents() {
		String engagementNumber;

		homePage.setStatusDropdown("Pending");
		engagementNumber = homePage.getFirstEngagementNumber();
		excel.setCellData("WPF", "Eng1", 2, engagementNumber);
		Upload_Invalid_File_For_All_Documents("Duplicate", engagementNumber);
	}

	public void Upload_Invalid_File_For_All_Documents(String fileType, String... engagementNum) {
		String engagementNumber;
		if (engagementNum.length == 0)
			engagementNumber = excel.getCellData("WPF", "Eng1", 2);
		else
			engagementNumber = engagementNum[0];

		homePage.setEngagementNumberFilter(engagementNumber);

		homePage.clickCreateButton(engagementNumber);

		homePage.waitForSpinnerToHide();

		coverSheetPage.checkGuidelines();

		coverSheetPage.clickContinueButton();

		List<WebElement> showFileUploadDialogAllFiles;
		List<WebElement> showFileUploadDialogAllFilesClicked = new ArrayList<>();

		showFileUploadDialogAllFiles = coverSheetPage.getShowFileUploadDialogAllFilesElements();

		if (showFileUploadDialogAllFiles.isEmpty()) {
			log.warn("There is no Document");
			test.log(LogStatus.WARNING, "There is no Document");
			return;
		}

		List<WebElement> documentTabs;
		documentTabs = coverSheetPage.getDocumentTabs();
		String documentName;

		if (fileType == "Duplicate")
			uploadOneFile(documentTabs.get(0), showFileUploadDialogAllFiles.get(0));

		for (WebElement documentTab : documentTabs) {

			if (!showFileUploadDialogAllFiles.isEmpty()) {

				for (WebElement showFileUploadDialogElement : showFileUploadDialogAllFiles) {

					scrollUpDownToElement(driver, documentTab, -200);
					documentTab.click();

					try {
						showFileUploadDialogElement.click();
						documentName = getElementText(
								By.xpath("//div[contains(@class,'fileListDocumentInfoDisplay')]//h4"), "document name");
						System.out.println(documentName);
					} catch (Exception e) {
						break;
					}
					showFileUploadDialogAllFilesClicked.add(showFileUploadDialogElement);

					coverSheetPage.clickSelectFilesToBeUploadedButton();

					String filePath = excel.getCellData("WPF", "File_Path", 2);
					String fileName = null;

					switch (fileType) {
					case "Corrupted":
						fileName = excel.getCellData("WPF", "Corrupted", 2);
						break;
					case "Password_Protected":
						fileName = excel.getCellData("WPF", "Password_Protected", 2);
						break;
					case "Over_Size":
						fileName = excel.getCellData("WPF", "Over_Size", 2);
						break;
					case "Duplicate":
						fileName = excel.getCellData("WPF", "Duplicate", 2);
						break;
					}

					try {
						TestUtilities.copyPaste(filePath + fileName);
						// Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}

					switch (fileType) {
					case "Corrupted":
						test.log(LogStatus.INFO,
								"Selected Corrupted file: [" + fileName + "] for Document: [" + documentName + "]");
						break;
					case "Password_Protected":
						test.log(LogStatus.INFO, "Selected Password_Protected file: [" + fileName + "] for Document: ["
								+ documentName + "]");
						break;
					case "Over_Size":
						test.log(LogStatus.INFO,
								"Selected Over_Size file: [" + fileName + "] for Document: [" + documentName + "]");
						break;
					case "Duplicate":
						test.log(LogStatus.INFO,
								"Selected Duplicate file: [" + fileName + "] for Document: [" + documentName + "]");
						break;
					}

					String alertMessage = null;
					try {
						wait.until(ExpectedConditions.alertIsPresent());
						alertMessage = driver.switchTo().alert().getText();
						Thread.sleep(1000);
						switch (fileType) {
						case "Corrupted":
							Assert.assertEquals(alertMessage,
									"Error in submitting the data: Corrupt files. Please generate a new file for this submission; this file(s) is damaged.\n\n"
											+ fileName + "\n",
									"Validation message is not displayed");
							break;
						case "Password_Protected":
							Assert.assertEquals(alertMessage,
									"The following file(s) selected for upload is password protected. Please remove the password and upload the file again. Send an email to RMSSupport@HL.com if there are any questions.\n\n"
											+ fileName + "\n",
									"Validation message is not displayed");
							break;
						case "Over_Size":
							Assert.assertEquals(alertMessage,
									"File(s) to be uploaded cannot be more than 2GB in size. Please send an email to RMSSupport@hl.com to upload the file manually.\n\nThe following files have been skipped:\n    - \""
											+ fileName + "\" (Size: 2121 MB)",
									"Validation message is not displayed");
							break;
						case "Duplicate":
							Assert.assertEquals(alertMessage, "The file " + fileName
									+ " already exists. Please rename the file for upload or upload a different file.",
									"Validation message is not displayed");
							break;
						}

						test.log(LogStatus.PASS, "Validation message is displayed: " + alertMessage);
					} catch (org.openqa.selenium.NoAlertPresentException e) {
						switch (fileType) {
						case "Corrupted":
							TestUtilities.logFailedWithScreenshot(
									"Missing validation popup: [Error in submitting the data: Corrupt files. Please generate a new file for this submission; this file(s) is damaged. "
											+ fileName + "]");
							break;
						case "Password_Protected":
							TestUtilities.logFailedWithScreenshot(
									"Missing validation popup: [The following file(s) selected for upload is password protected. Please remove the password and upload the file again. Send an email to RMSSupport@hl.com if there are any questions. "
											+ fileName + "]");
							break;
						case "Over_Size":
							TestUtilities.logFailedWithScreenshot(
									"Missing validation popup: [File(s) to be uploaded cannot be more than 2GB in size. Please send an email to RMSSupport@hl.com to upload the file manually.\\n\\nThe following files have been skipped: "
											+ fileName + "]");
							break;
						case "Duplicate":
							TestUtilities.logFailedWithScreenshot("Missing validation popup: [The file " + fileName
									+ " already exists. Please rename the file for upload or upload a different file.");
							break;
						}

					} catch (java.lang.AssertionError e) {
						switch (fileType) {
						case "Corrupted":
							test.log(LogStatus.FAIL,
									"Expected validation message: [Error in submitting the data: Corrupt files. Please generate a new file for this submission; this file(s) is damaged. "
											+ fileName + "]. Actual validation message: [" + alertMessage + "]");
							break;
						case "Password_Protected":
							test.log(LogStatus.FAIL,
									"Expected validation message: [The following file(s) selected for upload is password protected. Please remove the password and upload the file again. Send an email to RMSSupport@hl.com if there are any questions. "
											+ fileName + "]. Actual validation message: [" + alertMessage + "]");
							break;
						case "Over_Size":
							test.log(LogStatus.FAIL,
									"Expected validation message: [File(s) to be uploaded cannot be more than 2GB in size. Please send an email to RMSSupport@hl.com to upload the file manually.\\n\\nThe following files have been skipped: "
											+ fileName + "]. Actual validation message: [" + alertMessage + "]");
							break;
						case "Duplicate":
							test.log(LogStatus.FAIL, "Expected validation message: [The file " + fileName
									+ " already exists. Please rename the file for upload or upload a different file.]");
							break;
						}
					} catch (Throwable t) {
						TestUtilities.logFailedWithScreenshot(
								"FAIL=> Verification failed with exception : " + t.getMessage());
					}

					driver.switchTo().alert().accept();
					coverSheetPage.clickCloseButton();
				}
				for (WebElement element : showFileUploadDialogAllFilesClicked) {
					showFileUploadDialogAllFiles.remove(element);
				}
			} else
				break;
		}

	}

	void uploadOneFile(WebElement documentTab, WebElement showFileUploadDialogElement) {
		String documentName = null;
		if (showFileUploadDialogElement != null) {

			scrollUpDownToElement(driver, documentTab, -200);
			documentTab.click();

			try {
				showFileUploadDialogElement.click();
				documentName = getElementText(By.xpath("//div[contains(@class,'fileListDocumentInfoDisplay')]//h4"),
						"document name");
				System.out.println(documentName);
			} catch (Exception e) {
				e.printStackTrace();
			}

			coverSheetPage.clickSelectFilesToBeUploadedButton();

			String filePath = excel.getCellData("WPF", "File_Path", 2);
			String fileName = excel.getCellData("WPF", "Duplicate", 2);

			try {
				TestUtilities.copyPaste(filePath + fileName);
				// Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

			test.log(LogStatus.INFO, "Selected: [" + fileName + "] for Document: [" + documentName + "]");
			coverSheetPage.clickUploadFilesButton();
			coverSheetPage.clickCloseButton();
		}
	}

	@Test(priority = 4, enabled = true)
	public void CreateWPFDocumentsTest() {
		String engagementNumber;

		homePage.setStatusDropdown("Pending");
		engagementNumber = homePage.getFirstEngagementNumber();
		excel.setCellData("WPF", "Eng1", 2, engagementNumber);
		excel.setCellData("WPF", "Eng2", 2, engagementNumber);
		excel.setCellData("WPF", "Eng3", 2, engagementNumber);
		CreateWPFDocuments(engagementNumber);
	}

	public void CreateWPFDocuments(String... engagementNum) {
		String engagementNumber;
		if (engagementNum.length == 0)
			engagementNumber = excel.getCellData("WPF", "Eng1", 2);
		else
			engagementNumber = engagementNum[0];

		homePage.setEngagementNumberFilter(engagementNumber);

		homePage.clickCreateButton(engagementNumber);

		homePage.waitForSpinnerToHide();

		coverSheetPage.checkGuidelines();

		coverSheetPage.clickContinueButton();

		List<WebElement> showFileUploadDialogRequiredFiles;
		List<WebElement> showFileUploadDialogRequiredFilesClicked = new ArrayList<>();
		showFileUploadDialogRequiredFiles = coverSheetPage.getShowFileUploadDialogRequiredFilesElements();

		if (showFileUploadDialogRequiredFiles.isEmpty()) {
			return;
		}

		List<WebElement> documentTabs;
		documentTabs = coverSheetPage.getDocumentTabs();

		for (WebElement documentTab : documentTabs) {

			if (!showFileUploadDialogRequiredFiles.isEmpty()) {

				for (WebElement element : showFileUploadDialogRequiredFiles) {

					scrollUpDownToElement(driver, documentTab, -200);
					documentTab.click();

					try {
						element.click();
					} catch (Exception e) {
						break;
					}
					showFileUploadDialogRequiredFilesClicked.add(element);

					coverSheetPage.clickSelectFilesToBeUploadedButton();

					//String fileName = excel.getCellData("WPF","FileName" + showFileUploadDialogRequiredFilesClicked.size(), 2);
					String fileName = excel.getCellData("WPF","Valid_File_Path", 2)+"File"+showFileUploadDialogRequiredFilesClicked.size()+".pdf";
					try {
						Thread.sleep(1000);
						TestUtilities.copyPaste(fileName);
					} catch (AWTException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					coverSheetPage.clickUploadFilesButton();

					coverSheetPage.clickCloseButton();

				}
				for (WebElement element : showFileUploadDialogRequiredFilesClicked) {
					showFileUploadDialogRequiredFiles.remove(element);
				}
			} else
				break;
		}

		coverSheetPage.setReviewerDropdown("Abe Bui");

		coverSheetPage.clickSubmitCoverSheetButton();

		coverSheetPage.clickSubmitWPFButton();

		homePage.waitForSpinnerToHide();

		// clickButton(By.xpath("(//button[@type='submit'])[4]"), "Go to home page");
		coverSheetPage.clickGoToHomePageButton();

		homePage.waitForSpinnerToHide();
		homePage.setEngagementNumberFilter(engagementNumber);
	}

	@Test(priority = 5, enabled = true)
	public void PrincipalManagerApprove() {
		String engagementNumber;

		engagementNumber = excel.getCellData("WPF", "Eng2", 2);

		homePage.setEngagementNumberFilter(engagementNumber);

		homePage.clickReviewApproveButton(engagementNumber);

		homePage.waitForSpinnerToHide();

		homePage.setPrincipalManager("PrincipalManager");

		homePage.clickApproveButton();

		homePage.clickGoToHomePageButton();
		homePage.waitForSpinnerToHide();
		homePage.setEngagementNumberFilter(engagementNumber);
	}

	@Test(priority = 6, enabled = true)
	public void RecordsReview() {
		String engagementNumber;

		engagementNumber = excel.getCellData("WPF", "Eng3", 2);

		homePage.waitForSpinnerToHide();
		homePage.setEngagementNumberFilter(engagementNumber);

		homePage.clickReviewButton(engagementNumber);

		homePage.waitForSpinnerToHide();

		List<WebElement> requiredDocumentStatusDropdownElements;
		requiredDocumentStatusDropdownElements = reviewWPFPage.getRequiredDocumentStatusDropdownElements();
		if (!requiredDocumentStatusDropdownElements.isEmpty()) {
			for (WebElement requiredDocumentStatusDropdownElement : requiredDocumentStatusDropdownElements) {
				reviewWPFPage.setRequiredDocumentStatusDropdown(requiredDocumentStatusDropdownElement, "Received");
			}
		}

		reviewWPFPage.clickApproveButton();

		coverSheetPage.clickGoToHomePageButton();

		homePage.waitForSpinnerToHide();
		homePage.setEngagementNumberFilter(engagementNumber);
	}

	void CreateWPF() throws InterruptedException, AWTException {
		// CreateWPFDocuments("engagementNumber");
		// PrincipalManagerApprove("25398");
		// RecordsReview("118863");
	}
}

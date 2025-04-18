package com.hl.pages.companydirectory;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.hl.base.TestBase;
import com.hl.utilities.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class SearchPage extends TestBase {

	By byEmployeeName = By.id("employee-name");

	public void setEmployeeName(String employeeName) {
		setTextBoxText(byEmployeeName, employeeName, "Employee Name");
	}

	By byOffice = By.id("office-location");

	public void setOffice(String office) {
		selectDropDownOption(byOffice, office, "Office");
	}

	By byOfficeFilter = By.xpath("//*[@id='office-location']//input[not(@readonly)]");

	public void setOfficeFilter(String officeFilter) {
		clickElement(byOffice, "Office");
		setTextBoxText(byOfficeFilter, officeFilter, "Office Filter");
	}

	By byStaffType = By.id("staff-type");

	public boolean setStaffType(String staffType, ExceptionHandling... exceptionHandling) {
		return selectDropDownOption(byStaffType, staffType, "Staff Type", exceptionHandling);
	}

	By byStaffTypeFilter = By.xpath("//*[@id='staff-type']//input[not(@readonly)]");

	public void setStaffTypeFilter(String staffTypeFilter) {
		clickElement(byStaffType, "Staff Type");
		setTextBoxText(byStaffTypeFilter, staffTypeFilter, "Staff Type Filter");
	}

	By byTitle = By.id("job-title");

	public void setTitle(String title) {
		selectDropDownOption(byTitle, title, "Title");
	}

	By byTitleFilter = By.xpath("//*[@id='job-title']//input[not(@readonly)]");

	public void setTitleFilter(String titleFilter) {
		clickElement(byTitle, "Title");
		setTextBoxText(byTitleFilter, titleFilter, "Title Filter");
	}

	By byDepartment = By.id("department");

	public void setDepartment(String department) {
		selectDropDownOption(byDepartment, department, "Department");
	}

	By byDepartmentFilter = By.xpath("//*[@id='department']//input[not(@readonly)]");

	public void setDepartmentFilter(String departmentFilter) {
		clickElement(byDepartment, "Department");
		setTextBoxText(byDepartmentFilter, departmentFilter, "Department Filter");
	}

	By byIndustryGroup = By.id("industry");

	public void setIndustryGroup(String industryGroup) {
		selectDropDownOption(byIndustryGroup, industryGroup, "Industry Group");
	}

	By byIndustryGroupFilter = By.xpath("//*[@id='industry']//input[not(@readonly)]");

	public void setIndustryGroupFilter(String industryGroupFilter) {
		clickElement(byIndustryGroup, "byIndustryGroup");
		setTextBoxText(byIndustryGroupFilter, industryGroupFilter, "Industry Group Filter");
	}

	By byProductSpecialty = By.id("product");

	public void setProductSpecialty(String productSpecialty) {
		selectDropDownOption(byProductSpecialty, productSpecialty, "Product Specialty");
	}

	By byProductSpecialtyFilter = By.xpath("//*[@id='product']//input[not(@readonly)]");

	public void setProductSpecialtyFilter(String productSpecialtyFilter) {
		clickElement(byProductSpecialty, "ProductSpecialty");
		setTextBoxText(byProductSpecialtyFilter, productSpecialtyFilter, "Product Specialty Filter");
	}

	By byExportButton = By.xpath("//span[text()='Export']");

	public void clickExportButton() {
		clickButton(byExportButton, "Export");
	}

	By bySearchButton = By.xpath("//span[text()='Search']");

	public void clickSearchButton() {
		clickButton(bySearchButton, "Search");
	}

	By byClearButton = By.xpath("//span[text()='Clear']");

	public void clickClearButton() {
		clickButton(byClearButton, "Clear");
	}

	By byNotaryOnlyCheckBoxValue = By.xpath("//input[@type='checkbox']");
	By byNotaryOnlyCheckBox = By.xpath("//div[div[input[@type='checkbox']]]");

	public void checkNotaryOnlyCheckBox() {
		if (isElementVisible(byNotaryOnlyCheckBox, "Notary Only Check Box")) {
			WebElement notaryOnlyCheckBox = driver.findElement(byNotaryOnlyCheckBoxValue);
			boolean isChecked = notaryOnlyCheckBox.getAttribute("aria-checked").equals("true");
			if (!isChecked)
				// driver.findElement(By.xpath("//label[text()='Notary Only?']")).click();
				driver.findElement(byNotaryOnlyCheckBox).click();
			log.info("Checked Notary Only? Check Box");
			test.log(LogStatus.INFO, "Checked Notary Only? Check Box");
		}
	}

	public void unCheckNotaryOnlyCheckBox() {
		if (isElementVisible(byNotaryOnlyCheckBox, "Notary Only Check Box")) {
			WebElement notaryOnlyCheckBox = driver.findElement(byNotaryOnlyCheckBoxValue);
			boolean isChecked = notaryOnlyCheckBox.getAttribute("aria-checked").equals("true");
			if (isChecked)
				driver.findElement(byNotaryOnlyCheckBox).click();
			log.info("UnChecked Notary Only? Check Box");
			test.log(LogStatus.INFO, "UnChecked Notary Only? Check Box");
		}
	}

	By byTableViewRadioButton = By.xpath("/descendant::div[contains(@class,'p-radiobutton-box')][1]");

	public void selectTableViewRadioButton() {
		// wait.until(ExpectedConditions.visibilityOfElementLocated(byTableViewRadioButton));
		selectRadioButton(byTableViewRadioButton, "TableView");
	}

	By byCardViewRadioButton = By.xpath("//div[@class='p-radiobutton-box']");

	public void selectCardViewRadioButton() {
		selectRadioButton(byCardViewRadioButton, "CardView");
	}

	public void verifyNamesFromTable1(String nameSearch) {
		List<WebElement> allLinks = driver.findElements(By.xpath("//td[1]/a"));
		assertTrue(!allLinks.isEmpty(), "No Results Found.");
		// if (allLinks.isEmpty()) throw new AssertionError ("No Results Found.");
		for (WebElement name : allLinks) {
			assertTrue((name.getText().toUpperCase()).contains(nameSearch.toUpperCase()),
					"Search result does not contain: " + nameSearch);
		}
	}

	public void verifySearchFieldFromTable(String searchField, By by) {
		List<WebElement> allSearchFields = driver.findElements(by);
		assertTrue(!allSearchFields.isEmpty(), "No Results Found.");
		for (WebElement searchElement : allSearchFields) {
			assertTrue((searchElement.getText().toUpperCase()).contains(searchField.toUpperCase()),
					"Search result does not contain: " + searchField);
		}
		TestUtilities.captureScreenshot();
		log.debug("PASS=> Search result contains: " + searchField);
		test.log(LogStatus.PASS, "PASS=> Search result contains: " + searchField);
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtilities.screenshotName));
	}

	By byNameOnTable = By.xpath("//td[1]/a");
	By byTitleOnTable = By.xpath("//td[2]/div");
	By byPhoneOnTable = By.xpath("//td[3]/div");
	By byEmailOnTable = By.xpath("//td[4]//a");
	By byStaffTypeOnTable = By.xpath("//td[5]/div");
	By byDepartmentOnTable = By.xpath("//td[6]/div");
	By byIndustryGroupOnTable = By.xpath("//td[7]/div");
	By byProductSpecialtyOnTable = By.xpath("//td[7]/div");
	By byOfficeOnTable = By.xpath("//td[8]/div");

	public void verifyNamesFromTable(String nameSearch) {
		verifySearchFieldFromTable(nameSearch, byNameOnTable);
	}

	By byNameOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[1]");
	By byTitleOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[2]");
	By byDepartmentOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[5]");
	By byIndustryGroupOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[4]");
	By byProductSpecialtyOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[4]");
	By byOfficeOnCard = By.xpath("//div[contains(@class,'grid')]/div/div/div/div[2]/span[6]");

	public void verifyNamesFromCard(String nameSearch) {
		verifySearchFieldFromTable(nameSearch, byNameOnCard);
	}
	// Card View
	// Name: //div[contains(@class,'grid')]/div/div/div/div[2]/span[1]
	// Title: //div[contains(@class,'grid')]/div/div/div/div[2]/span[2]
	// Industry Group / Product Specialty /span[4]
	// Department /span[5]
	// Office /span[6]
}

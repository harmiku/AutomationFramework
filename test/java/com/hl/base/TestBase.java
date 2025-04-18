package com.hl.base;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.hl.utilities.DbManager;
import com.hl.utilities.ExcelReader;
import com.hl.utilities.TestConfig;
import com.hl.utilities.TestUtilities;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("testLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait, longWait;
	public static ExtentReports extRepo;
	public static ExtentTest test;
	public static String browser;
	public static ResultSet resultSet = null;
	public static String selectSQL,updateSQL,deleteSQL;
	public static SoftAssert softAssert = new SoftAssert();
	public enum ExceptionHandling {
		STOP, FAIL, WARNING;
	}

	@BeforeSuite( groups = {"upload","sanity"})
	public void setupSuite() {
		wait = new WebDriverWait(driver, 15);
		longWait = new WebDriverWait(driver, 600);
/*		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(15, TimeUnit.SECONDS)
			       .pollingEvery(1, TimeUnit.SECONDS);
		FluentWait<WebDriver> longWait = new FluentWait<WebDriver>(driver)
			       .withTimeout(600, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS);
*/
	}

	@AfterSuite
	public void tearDownSuite() {
	}

	public static boolean isElementPresent(By by, String webElementName, ExceptionHandling... exceptionHandling) {
		if (exceptionHandling.length != 0) {
			switch (exceptionHandling[0].name()) {
			case "STOP":
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(by));
					return true;
				} catch (Exception e) {
					Assert.fail("Error => " + webElementName + " is not present");
					return false;
				}
			case "FAIL":
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(by));
					return true;
				} catch (org.openqa.selenium.TimeoutException e) {
					TestUtilities.captureScreenshot();
					log.error("Error => " + e);
					test.log(LogStatus.FAIL, "Error => '" + webElementName + "' is not present");
					test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
					softAssert.fail("Error => '" + webElementName + "' is not present");		
					return false;
				} catch (Exception e) {
					TestUtilities.captureScreenshot();
					log.error("Unexpected error => " + e);
					test.log(LogStatus.FAIL, "Unexpected error => " + e);
					test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
					softAssert.fail("Unexpected error => " + e);		
					return false;
				}
			case "WARNING":
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(by));
					return true;
				} catch (org.openqa.selenium.TimeoutException e) {
					TestUtilities.captureScreenshot();
					log.warn("Warning => " + e);
					test.log(LogStatus.WARNING, "Warning => '" + webElementName + "' is not present");
					test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
					return false;
				} catch (Exception e) {
					TestUtilities.captureScreenshot();
					log.warn("Warning => " + e);
					test.log(LogStatus.WARNING, "Warning => " + e);
					test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
					return false;
				}
			default:
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(by));
					return true;
				} catch (Exception e) {
					Assert.fail("Error => " + webElementName + " is not present");
					return false;
				}
			}
		} else {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
				return true;
			} catch (Exception e) {
				Assert.fail("Error => " + webElementName + " is not present");
				return false;
			}
		}
	}

	public static boolean isElementVisible(By by, String webElementName, ExceptionHandling... exceptionHandling) {
			if (exceptionHandling.length != 0) {
				switch (exceptionHandling[0].name()) {
				case "STOP":
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(by));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is not visible ( "+by+" )");
						return false;
					}
				case "FAIL":
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(by));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.error("Error => " + e);
						test.log(LogStatus.FAIL, "Error => '" + webElementName + "' is not visible ( "+by+" )");
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Error => '" + webElementName + "' is not visible ( "+by+" )");	
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Unexpected error => " + e);
						test.log(LogStatus.FAIL, "Unexpected error => " + e);
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Unexpected error => " + e);		
						return false;
					}
				case "WARNING":
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(by));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => '" + webElementName + "' is not visible ( "+by+" )");	
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => " + e);
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					}
				default:
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(by));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is not visible ( "+by+" )");	
						return false;
					}
				}
			} else {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(by));
					return true;
				} catch (Exception e) {
					Assert.fail("Error => '" + webElementName + "' is not visible ( "+by+" )");	
					return false;
				}
			}
	}

	public static boolean isNotElementVisible(By by, String webElementName, ExceptionHandling... exceptionHandling) {
		try {
			if (driver.findElement(by).isDisplayed()) {
				if (exceptionHandling.length != 0) {
					switch (exceptionHandling[0].name()) {
					case "STOP":
						Assert.fail("Error => '" + webElementName + "' is visible ( "+by+" )");	
						return false;
					case "FAIL":
						TestUtilities.captureScreenshot();
						log.error("Error => '" + webElementName + "' is visible ( "+by+" )");	
						test.log(LogStatus.FAIL, "Error => '" + webElementName + "' is visible ( "+by+" )");	
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					case "WARNING":
						TestUtilities.captureScreenshot();
						log.warn("Warning => '" + webElementName + "' is visible ( "+by+" )");	
						test.log(LogStatus.WARNING, "Warning => '" + webElementName + "' is visible ( "+by+" )");	
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					default:
						Assert.fail("Error => '" + webElementName + "' is visible ( "+by+" )");	
						return false;
					}
				} else
					Assert.fail("Error => '" + webElementName + "' is visible ( "+by+" )");	
				return false;
			} else
				return true;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean isElementEnabled(By by, String webElementName, ExceptionHandling... exceptionHandling) {
		if (isElementVisible(by, webElementName, exceptionHandling)) {
			if (exceptionHandling.length != 0) {
				switch (exceptionHandling[0].name()) {
				case "STOP":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is not enabled");
						return false;
					}
				case "FAIL":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.error("Error => " + e);
						test.log(LogStatus.FAIL, "Error => '" + webElementName + "' is not enabled");
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Error => '" + webElementName + "' is not enabled");		
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Unexpected error => " + e);
						test.log(LogStatus.FAIL, "Unexpected error => " + e);
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Unexpected error => " + e);		
						return false;
					}
				case "WARNING":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => '" + webElementName + "' is not enabled");
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => " + e);
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					}
				default:
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is not enabled");
						return false;
					}
				}
			} else {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(by));
					return true;
				} catch (Exception e) {
					Assert.fail("Error => '" + webElementName + "' is not enabled");
					return false;
				}
			}
		} else
			return false;
	}

	public static boolean isElementDisabled(By by, String webElementName, ExceptionHandling... exceptionHandling) {
		if (isElementVisible(by, webElementName, exceptionHandling)) {
			if (exceptionHandling.length != 0) {
				switch (exceptionHandling[0].name()) {
				case "STOP":
					try {
						wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is enabled");
						return false;
					}
				case "FAIL":
					try {
						wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.error("Error => " + e);
						test.log(LogStatus.FAIL, "Error => '" + webElementName + "' is enabled");
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Error => '" + webElementName + "' is enabled");		
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Unexpected error => " + e);
						test.log(LogStatus.FAIL, "Unexpected error => " + e);
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Unexpected error => " + e);		
						return false;
					}
				case "WARNING":
					try {
						wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => '" + webElementName + "' is enabled");
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => " + e);
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					}
				default:
					try {
						wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
						return true;
					} catch (Exception e) {
						Assert.fail("Error => '" + webElementName + "' is enabled");
						return false;
					}
				}
			} else {
				try {
					wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
					return true;
				} catch (Exception e) {
					Assert.fail("Error => '" + webElementName + "' is enabled");
					return false;
				}
			}
		} else
			return false;
	}

	public static WebElement getPresentWebElement(By by, String webElementName,ExceptionHandling... exceptionHandling) {
		if (isElementPresent(by, webElementName, exceptionHandling)) {
			try {
				return driver.findElement(by);
			} catch (Exception e) {
				Assert.fail("Error => '" + webElementName + "' not present");
				return null;
			}
		} else
			return null;
	}

	public static WebElement getWebElement(By by, String webElementName,ExceptionHandling... exceptionHandling) {
		if (isElementVisible(by, webElementName, exceptionHandling)) {
				try {
					return driver.findElement(by);
				} catch (Exception e) {
					Assert.fail("Error => '" + webElementName + "' not visible");
					return null;
				}
		} else
			return null;
	}

	public static List<WebElement> getPresentWebElements(By by, String webElementName,
			ExceptionHandling... exceptionHandling) {
		if (isElementPresent(by, webElementName, exceptionHandling)) {
				try {
					return driver.findElements(by);
				} catch (Exception e) {
					Assert.fail("Error => " + webElementName + " not present");
					return null;
				}
		} else
			return null;
	}

	public static List<WebElement> getWebElements(By by, String webElementName,
			ExceptionHandling... exceptionHandling) {
		if (isElementVisible(by, webElementName, exceptionHandling)) {
				try {
					return driver.findElements(by);
				} catch (Exception e) {
					Assert.fail("Error => " + webElementName + " not visible");
					return null;
				}
		} else
			return null;
	}

	public static boolean clickElement(By by, String elementName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, elementName, exceptionHandling);
		if (webElement != null) {
			if (exceptionHandling.length != 0) {
				switch (exceptionHandling[0].name()) {
				case "STOP":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.click();
						log.info("Clicked " + elementName);
						test.log(LogStatus.INFO, "Clicked " + elementName);
						return true;
					} catch (org.openqa.selenium.ElementClickInterceptedException e) {
						Assert.fail("Error =>  Element click intercepted: Element '" + elementName + "' is not clickable at point. Other element would receive the click");
						return false;
					} catch (Exception e) {
						Assert.fail("Error => " + elementName + " is not clickable");
						return false;
					}
				case "FAIL":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.click();
						log.info("Clicked " + elementName);
						test.log(LogStatus.INFO, "Clicked " + elementName);
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.error("Error => " + e);
						test.log(LogStatus.FAIL, "Error => '" + elementName + "' is not clickable");
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Error => '" + elementName + "' is not clickable");		
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Unexpected error => " + e);
						test.log(LogStatus.FAIL, "Unexpected error => " + e);
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Unexpected error => " + e);		
						return false;
					}
				case "WARNING":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.click();
						log.info("Clicked " + elementName);
						test.log(LogStatus.INFO, "Clicked " + elementName);
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => '" + elementName + "' is not clickable");
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => " + e);
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					}
				default:
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.click();
						log.info("Clicked " + elementName);
						test.log(LogStatus.INFO, "Clicked " + elementName);
						return true;
					} catch (Exception e) {
						Assert.fail("Error => " + elementName + " is not clickable");
						return false;
					}
				}
			} else {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(by));
					webElement.click();
					log.info("Clicked " + elementName);
					test.log(LogStatus.INFO, "Clicked " + elementName);
					return true;
				} catch (org.openqa.selenium.ElementClickInterceptedException e) {
					Assert.fail("Error =>  Element click intercepted: Element '" + elementName + "' is not clickable at point. Other element would receive the click");
					return false;
				} catch (Exception e) {
					Assert.fail("Error => " + elementName + " is not clickable");
					return false;
				}
			}
		} else
			return false;
	}

	public static boolean selectDropDownOption(By by, String visibleText, String dropDownName,ExceptionHandling... exceptionHandling) {
		if (clickElement(by, dropDownName, exceptionHandling)) {
			By byDropDownOption = By.xpath("//*[text()='" + visibleText + "']");
			if (clickElement(byDropDownOption, visibleText, exceptionHandling)) {
				log.info("Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
				test.log(LogStatus.INFO, "Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static boolean selectDropDownOption(By by, String visibleText, By byDropDownOption, String dropDownName,ExceptionHandling... exceptionHandling) {
		if (clickElement(by, dropDownName, exceptionHandling)) {
			if (clickElement(byDropDownOption, visibleText, exceptionHandling)) {
				log.info("Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
				test.log(LogStatus.INFO, "Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static boolean selectDropDownByVisibleText(WebElement webElement, String visibleText, String dropDownName,
			ExceptionHandling... exceptionHandling) {
		if (webElement != null) {
			Select select = new Select(webElement);
			select.selectByVisibleText(visibleText);
			log.info("Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
			test.log(LogStatus.INFO, "Selected '" + visibleText + "' from '" + dropDownName + "' drop down");
			return true;
		} else
			return false;
	}

	public static boolean selectDropDownByVisibleText(By by, String visibleText, String dropDownName,
			ExceptionHandling... exceptionHandling) {
		dropDownName = "' "+dropDownName + "' drop down";
		WebElement webElement = getWebElement(by, dropDownName, exceptionHandling);
		return selectDropDownByVisibleText(webElement, visibleText, dropDownName + " drop down", exceptionHandling);
	}

	public static boolean selectDropDownByValue(By by, String value, String dropDownName,
			ExceptionHandling... exceptionHandling) {
		dropDownName = "' "+dropDownName + "' drop down";
		WebElement webElement = getWebElement(by, dropDownName, exceptionHandling);
		if (webElement != null) {
			Select select = new Select(webElement);
			select.selectByValue(value);
			log.info("Selected value '" + value + "' from " + dropDownName);
			test.log(LogStatus.INFO, "Selected value '" + value + "' from " + dropDownName);
			return true;
		} else
			return false;
	}

	public static boolean selectDropDownByIndex(By by, int index, String dropDownName,
			ExceptionHandling... exceptionHandling) {
		dropDownName = "' "+dropDownName + "' drop down";
		WebElement webElement = getWebElement(by, dropDownName, exceptionHandling);
		if (webElement != null) {
			Select select = new Select(webElement);
			select.selectByIndex(index);
			log.info("Selected index '" + index + "' from " + dropDownName);
			test.log(LogStatus.INFO, "Selected index '" + index + "' from " + dropDownName);
			return true;
		} else
			return false;
	}

	public static String getDropDownSelectedOption(By by, String dropDownName, ExceptionHandling... exceptionHandling) {
		dropDownName = "' "+dropDownName + "' drop down";
		WebElement webElement = getWebElement(by, dropDownName, exceptionHandling);
		if (webElement != null) {
			String value = webElement.getAttribute("value");
			log.info("Got " + dropDownName + " selected option = " + value);
			test.log(LogStatus.INFO, "Got " + dropDownName + " selected option = " + value);
			return value;
		} else
			return null;
	}

	public static boolean setTextBoxText(By by, String value, String textBoxName,
			ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, textBoxName + " text box", exceptionHandling);
		if (webElement != null) {
			webElement.clear();
			webElement.sendKeys(value);
			log.info("Set " + textBoxName + " = " + value);
			test.log(LogStatus.INFO, "Set " + textBoxName + " = " + value);
			return true;
		} else
			return false;

	}

	public static String getTextBoxText(By by, String textBoxName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, textBoxName + " text box", exceptionHandling);
		if (webElement != null) {
			scrollUpDownToElement(driver,by,textBoxName,-200);
			String value = webElement.getAttribute("value");
			log.info("Got " + textBoxName + " = " + value);
			test.log(LogStatus.INFO, "Got " + textBoxName + " = " + value);
			return value;
		} else
			return null;
	}

	public static String getElementText(By by, String elementName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, elementName, exceptionHandling);
		scrollUpDownToElement(driver,by,elementName, -200);
		return getElementText(webElement, elementName, exceptionHandling);
	}

	public static String getElementText(WebElement webElement, String elementName,
			ExceptionHandling... exceptionHandling) {
		if (webElement != null) {
			String value = webElement.getText();
			log.info("Got " + elementName + " text = " + value);
			test.log(LogStatus.INFO, "Got " + elementName + " text = " + value);
			return value;
		} else
			return null;
	}

	public static String getHyperLinkText(By by, String hyperlinkName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, hyperlinkName + " hyperlink", exceptionHandling);
		if (webElement != null) {
			String value = webElement.getAttribute("innerHTML");
			// value = webElement.getText();
			log.info("Got " + hyperlinkName + " hyperlink" + " text = " + value);
			test.log(LogStatus.INFO, "Got " + hyperlinkName + " hyperlink" + " text = " + value);
			return value;
		} else
			return null;
	}

	public static boolean checkCheckBox(By by, String checkBoxName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, checkBoxName + " check box", exceptionHandling);
		if (webElement != null) {
			if (!webElement.isSelected())
				webElement.click();
			log.info("Check " + checkBoxName + " check box");
			test.log(LogStatus.PASS, "Check " + checkBoxName + " check box");
			return true;
		} else
			return false;
	}

	public static boolean unCheckCheckBox(By by, String checkBoxName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, checkBoxName + " check box", exceptionHandling);
		if (webElement != null) {
			if (webElement.isSelected())
				webElement.click();
			log.info("Uncheck " + checkBoxName + " check box");
			test.log(LogStatus.PASS, "Uncheck " + checkBoxName + " check box");
			return true;
		}
		return false;
	}
	
	public static boolean isWebElementSelected(By by, String webElementName, ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by,webElementName,exceptionHandling);
		return webElement.isSelected();
	}


	public static boolean clickButton(By by, String buttonName, ExceptionHandling... exceptionHandling) {
		return clickElement(by, buttonName + " button", exceptionHandling);
	}

	public static boolean sendKeysToButton(By by, String buttonName, String sendKeys,
			ExceptionHandling... exceptionHandling) {
		WebElement webElement;
		webElement = getWebElement(by, buttonName + " button", exceptionHandling);
		if (webElement != null) {
			if (exceptionHandling.length != 0) {
				switch (exceptionHandling[0].name()) {
				case "STOP":
					wait.until(ExpectedConditions.elementToBeClickable(by));
					webElement.sendKeys(Keys.RETURN);
					log.info("Click " + buttonName + " button");
					test.log(LogStatus.INFO, "Click " + buttonName + " button");
					return true;
				case "FAIL":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.sendKeys(Keys.RETURN);
						log.info("Click " + buttonName + " button");
						test.log(LogStatus.INFO, "Click " + buttonName + " button");
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.error("Error => " + e);
						test.log(LogStatus.FAIL, "Error => '" + buttonName + " button" + "' is not clickable");
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Error => '" + buttonName + "' is not clickable");		
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Unexpected error => " + e);
						test.log(LogStatus.FAIL, "Unexpected error => " + e);
						test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
						softAssert.fail("Unexpected error => " + e);		
						return false;
					}
				case "WARNING":
					try {
						wait.until(ExpectedConditions.elementToBeClickable(by));
						webElement.sendKeys(Keys.RETURN);
						log.info("Click " + buttonName + " button");
						test.log(LogStatus.INFO, "Click " + buttonName + " button");
						return true;
					} catch (org.openqa.selenium.TimeoutException e) {
						TestUtilities.captureScreenshot();
						log.warn("Warning => " + e);
						test.log(LogStatus.WARNING,
								"Warning => '" + buttonName + " button" + "' is not clickable");
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					} catch (Exception e) {
						TestUtilities.captureScreenshot();
						log.error("Warning => " + e);
						test.log(LogStatus.WARNING, "Warning => " + e);
						test.log(LogStatus.WARNING, test.addScreenCapture(TestUtilities.screenshotName));
						return false;
					}
				default:
					wait.until(ExpectedConditions.elementToBeClickable(by));
					webElement.sendKeys(Keys.RETURN);
					log.info("Click " + buttonName + " button");
					test.log(LogStatus.INFO, "Click " + buttonName + " button");
					return true;
				}
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(by));
				webElement.sendKeys(Keys.RETURN);
				log.info("Click " + buttonName + " button");
				test.log(LogStatus.INFO, "Click " + buttonName + " button");
				return true;
			}
		} else
			return false;
	}

	public static boolean selectRadioButton(By by, String radioButtonName, ExceptionHandling... exceptionHandling) {
		return clickElement(by, radioButtonName + " radio button", exceptionHandling);
	}

	public static boolean verifyEquals1(String expected, String actual, String passMessage, String failMessage) {
		if (expected.equals(actual)) {  
			TestUtilities.logPassWithScreenshot(passMessage);
			return true;
		}
		else {
			TestUtilities.logFailedWithScreenshot(failMessage);
			softAssert.fail(failMessage);	
			return false;
		}
	}

	public static boolean verifyEquals(String expected, String actual, String... message) {
		TestUtilities.captureScreenshot();
		try {
			Assert.assertEquals(actual, expected);
			if(message.length!=0) {
				log.info("Expected: " + expected+" equals Actual : "+actual);
				test.log(LogStatus.PASS, "Expected: " + expected+" equals Actual : "+actual);
				test.log(LogStatus.PASS, test.addScreenCapture(TestUtilities.screenshotName));
			}
			return true;
		} catch (Throwable t) {
			//log.error("Error=> " + t.getMessage());
			//test.log(LogStatus.FAIL, "Error=> " + t.getMessage());
			log.error("Error=> Expected: " + expected+" but Actual is: "+actual);
			test.log(LogStatus.FAIL, "Error=> Expected: " + expected+" but Actual is: "+actual);
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
			softAssert.fail("Error=> " + t.getMessage());		
			return false;
		}

	}

	public static boolean scrollUpDownToElement(WebDriver driver, WebElement element, int... UpDown) {
		try {
			Thread.sleep(300);
			if (UpDown.length != 0)
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].scrollIntoView(true);" + "window.scrollBy(0," + UpDown[0] + ");", element);
			else
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(300);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean scrollUpDownToElement1(WebDriver driver, By by, String elementName, int... UpDown) {
		WebElement element = getWebElement(by, elementName);
		if (element != null)
			return scrollUpDownToElement(driver, element, UpDown);
		else
			return false;
	}

	public static boolean scrollUpDownToElement(WebDriver driver, By by, String elementName, int... UpDown) {
		WebElement element = getWebElement(by, elementName);
		if (element != null)
			return scrollUpDownToElement(driver, element, UpDown);
		else
			return false;
	}

	public static void scrollToTheBottomOfPage() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void terminateTest(String message) throws Exception {
		throw new Exception(message);
	}

	public static void setDefaultUser() {
		deleteAllRolesForCurrentApp();
		executeSQLUpdateStaff("CORP","6 Associate","2022-03-14 00:00:00.000","T");
	}
	
	public static int executeSQLUpdateStaff(String staffType, String managementLevel, String hireDate, String...employed) {
		String SQLString =""; 
		if (staffType.length()>0)
			SQLString=SQLString+"update hs set STAFF_TYPE='"+staffType+"' FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik';";
		if (managementLevel.length()>0)
			SQLString=SQLString	+ "update hs set MANAGEMENT_LEVEL='"+managementLevel+"' FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik';";
		if (hireDate.length()>0)
			SQLString=SQLString	+"update hs set HIRE_DATE='"+hireDate+"' FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik';";
		if (employed.length>0)
			SQLString=SQLString	+"update hs set EMPLOYED='"+employed[0]+"' FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik';";
		return executeSQLUpdate(SQLString);
	}

	public static int executeSQLDeleteStaffAllMatchingRequests() {
		String SQLString =
				"IF OBJECT_ID(N'tempdb..#REQUEST_ID') IS NOT NULL\r\n"
						+ "BEGIN\r\n"
						+ "DROP TABLE #REQUEST_ID\r\n"
						+ "END;\r\n"
						+ "\r\n"
						+ "declare @staffid int = (SELECT  staffid FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik');\r\n"
						+ "\r\n"
						+ "SELECT  FR.HL_FOUNDATION_REQUEST_ID, CC.CHARITY_ID, SC.STAFF_CHARITY_ID\r\n"
						+ "INTO #REQUEST_ID\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[CHARITY_CONTACT] CC ON CC.CHARITY_ID = SC.CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[MATCHING_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "where   STAFF_ID=@staffid;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].HL_FOUNDATION_RECEIPT FROM [HL_FOUNDATION].[dbo].HL_FOUNDATION_RECEIPT R JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID =  R.HL_FOUNDATION_REQUEST_ID;"						
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST_NOTE]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST_NOTE] N \r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID =  N.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[MATCHING_REQUEST]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[MATCHING_REQUEST] MR \r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID = MR.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[STAFF_CHARITY]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC \r\n"
						+ "JOIN #REQUEST_ID T ON T.STAFF_CHARITY_ID = SC.STAFF_CHARITY_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[CHARITY_CONTACT]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[CHARITY_CONTACT] CC \r\n"
						+ "JOIN #REQUEST_ID T ON T.CHARITY_ID = CC.CHARITY_ID;";
		TestUtilities.logInfo("Delete All Matching Requests for Harmik Uchian");

		int i= executeSQLUpdate(SQLString);
		return i;
	}

	public static int executeSQLDeleteStaffAllGADRequests() {
		String SQLString =
				"IF OBJECT_ID(N'tempdb..#REQUEST_ID') IS NOT NULL\r\n"
						+ "BEGIN\r\n"
						+ "DROP TABLE #REQUEST_ID\r\n"
						+ "END;\r\n"
						+ "\r\n"
						+ "declare @staffid int = (SELECT  staffid FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik');\r\n"
						+ "\r\n"
						+ "SELECT  FR.HL_FOUNDATION_REQUEST_ID, CC.CHARITY_ID, SC.STAFF_CHARITY_ID\r\n"
						+ "INTO #REQUEST_ID\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[CHARITY_CONTACT] CC ON CC.CHARITY_ID = SC.CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[GIVEADAY_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "where   STAFF_ID=@staffid;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].HL_FOUNDATION_RECEIPT FROM [HL_FOUNDATION].[dbo].HL_FOUNDATION_RECEIPT R JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID =  R.HL_FOUNDATION_REQUEST_ID;"						
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST_NOTE]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST_NOTE] N \r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID =  N.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[GIVEADAY_REQUEST]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[GIVEADAY_REQUEST] MR \r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID = MR.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN #REQUEST_ID T ON T.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[STAFF_CHARITY]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC \r\n"
						+ "JOIN #REQUEST_ID T ON T.STAFF_CHARITY_ID = SC.STAFF_CHARITY_ID;\r\n"
						+ "\r\n"
						+ "DELETE [HL_FOUNDATION].[dbo].[CHARITY_CONTACT]\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[CHARITY_CONTACT] CC \r\n"
						+ "JOIN #REQUEST_ID T ON T.CHARITY_ID = CC.CHARITY_ID;";
		TestUtilities.logInfo("Delete All GIVEADAY Requests for Harmik Uchian");

		int i= executeSQLUpdate(SQLString);
		return i;
	}

	public static int executeSQLUpdateAllMatchingRequestsStatus(String statusID) {
		String statusDescription="";
		switch (statusID) {
		case "9": 
			statusDescription="Pending Initial Approval";
			break;
		case "10":
			statusDescription="Pending Final Approval";
			break;
		case "11":
			statusDescription="Need Info";
			break;
		case "12":
			statusDescription="Approved";
			break;
		case "13":
			statusDescription="Rejected";
			break;
		case "14":
			statusDescription="Cancelled";
			break;
		case "15":
			statusDescription="PendingDocumentSubmission";
			break;
		default:
			statusDescription="";
		}

		String SQLString =
				"UPDATE FR SET STATUS_ID = "+statusID+"\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[MATCHING_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "WHERE SC.STAFF_ID=16348";
		TestUtilities.logInfo("Updated All GAD Requests Status to: "+statusDescription);
		int i= executeSQLUpdate(SQLString);
		return i;
	}

		public static int executeSQLUpdateAllGADRequestsStatus(String statusID) {
			String statusDescription="";
			switch (statusID) {
			case "1": 
				statusDescription="Pending Initial Pre Approval";
				break;
			case "2":
				statusDescription="Pending Final Pre Approval";
				break;
			case "3": 
				statusDescription="Pending Initial Approval";
				break;
			case "4":
				statusDescription="Pending Final Approval";
				break;
			case "5":
				statusDescription="Need Info";
				break;
			case "6":
				statusDescription="PendingDocumentSubmission";
				break;
			case "7":
				statusDescription="Approved";
				break;
			case "8":
				statusDescription="Rejected";
				break;
			default:
				statusDescription="";
			}

		String SQLString =
				"UPDATE FR SET STATUS_ID = "+statusID+"\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[GIVEADAY_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "WHERE SC.STAFF_ID=16348";
		TestUtilities.logInfo("Updated All GAD Requests Status to: "+statusDescription);
		int i= executeSQLUpdate(SQLString);
		return i;
	}	
	
	public static int executeSQLUpdateAllMatchingRequestsDate(String date) {
		String SQLString =
				"UPDATE FR SET [REQUEST_DATE] = '"+date+"'\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[MATCHING_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "WHERE SC.STAFF_ID=16348";
		TestUtilities.logInfo("Updated All Matching Requests Date to "+date);

		int i= executeSQLUpdate(SQLString);
		return i;
	}

	public static int executeSQLUpdateAllMatchingRequestsAmount(String amount) {
		String SQLString =
				"UPDATE MR SET [REQUEST_AMOUNT] = "+amount+"\r\n"
						+ "FROM [HL_FOUNDATION].[dbo].[HL_FOUNDATION_REQUEST] FR\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[STAFF_CHARITY] SC ON SC.STAFF_CHARITY_ID = FR.STAFF_CHARITY_ID\r\n"
						+ "JOIN [HL_FOUNDATION].[dbo].[MATCHING_REQUEST] MR ON MR.HL_FOUNDATION_REQUEST_ID = FR.HL_FOUNDATION_REQUEST_ID\r\n"
						+ "WHERE SC.STAFF_ID=16348";
		TestUtilities.logInfo("Updated All Matching Requests Amount to "+amount);

		int i= executeSQLUpdate(SQLString);
		return i;
	}

	public static int executeSQLUpdateAssignApproverRole(String approver) {
		String SQLString =
				"USE HL_DATA\r\n"
				+ "DECLARE @STAFFID AS INT\r\n"
				+ "SET @STAFFID = 16348\r\n"
				+ "DECLARE @SEC_ROLE VARCHAR(20)\r\n"
				+ "SET @SEC_ROLE = '"+approver+"'\r\n"
				+ "DECLARE @APPLICATION_ID VARCHAR(20)\r\n"
				+ "SET @APPLICATION_ID = 'HLFOUND001'\r\n"
				+ "SELECT * FROM dbo.USER_SECURITY WHERE STAFFID = @STAFFID\r\n"
				+ "DELETE FROM dbo.USER_SECURITY WHERE STAFFID = @STAFFID AND APPLICATION_ID = @APPLICATION_ID\r\n"
				+ "IF NOT EXISTS(SELECT * FROM dbo.USER_SECURITY WHERE STAFFID = @STAFFID AND APPLICATION_ID = @APPLICATION_ID AND ROLE_ID = @SEC_ROLE)\r\n"
				+ "BEGIN\r\n"
				+ "INSERT INTO dbo.USER_SECURITY(STAFFID,REFERENCE_KEY,HC_STAFFID,APPLICATION_ID,ROLE_ID,REPORT_ID,PROPERTY_ID,PROPERTY_VALUES,ACTIVE,REQUEST_USER,CREATE_USER,CREATE_DATE,MODIFY_USER,MODIFY_DATE,OLD_PROPERTY_VALUES)\r\n"
				+ "VALUES(@STAFFID,CONCAT(@APPLICATION_ID,'-',@SEC_ROLE,'-',@STAFFID),'',@APPLICATION_ID,@SEC_ROLE,NULL,1,NULL,'T',@STAFFID,@STAFFID,GETDATE(),@STAFFID,GETDATE(),NULL)\r\n"
				+ "END";
		TestUtilities.logInfo("Assigned "+approver+" role to current user");

		int i= executeSQLUpdate(SQLString);
		return i;
	}
	
	public static void assignApprover1() {
		executeSQLUpdateAssignApproverRole("HLFAPPROVERONE");
	}

	public static void assignApprover2() {
		executeSQLUpdateAssignApproverRole("HLFAPPROVERTWO");
	}

	public static void assignAdmin() {
		executeSQLUpdateAssignApproverRole("HLFADMIN");
	}

	public static int executeSQLUpdate(String SQLString) {
		int i=-1;
		try (Connection connection = DbManager.getDBConnection(TestConfig.dbConnectionUrlHL_DATA);
				Statement statement = connection.createStatement();) {
			PreparedStatement preparedStatement= connection.prepareStatement(SQLString);
			i =preparedStatement.executeUpdate();
			connection.close();
			statement.close();
			return i;
		} catch (SQLException se) {
			se.printStackTrace();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}

	public static String executeSQLSelectName() {
		String SQLString =
				"SELECT  HS.FULL_NAME\r\n"
						+ "FROM HL_DATA.dbo.HL_STAFF AS HS\r\n"
						+ "where HS.FULL_NAME ='uchian, harmik'";
		String result= executeSQLSelect(SQLString,"FULL_NAME");
		TestUtilities.logInfo("Get Full Name from Database = "+result);
		return result;
	}

	public static String executeSQLSelectOfficeLocation() {
		String SQLString =
				"SELECT  HS.OFFICE\r\n"
						+ "FROM HL_DATA.dbo.HL_STAFF AS HS\r\n"
						+ "where HS.FULL_NAME ='uchian, harmik'";
		String result= executeSQLSelect(SQLString,"OFFICE");
		TestUtilities.logInfo("Get Office Location from Database = "+result);
		return result;
	}
	
	public static int deleteAllRolesForCurrentApp() {
		String SQLString =
				"DELETE FROM dbo.USER_SECURITY \r\n"
				+ "WHERE STAFFID = '16348'\r\n"
				+ "AND APPLICATION_ID = 'HLFOUND001'\r\n"
				+ "";
		int i= executeSQLUpdate(SQLString);
		return i;
	}
		
	public static String executeSQLSelectTitle() {
		String SQLString =
				"SELECT  HS.TITLE\r\n"
						+ "FROM HL_DATA.dbo.HL_STAFF AS HS\r\n"
						+ "where HS.FULL_NAME ='uchian, harmik'";
		String result= executeSQLSelect(SQLString,"TITLE");
		TestUtilities.logInfo("Get Title from Database = "+result);
		return result;
	}

	public static String executeSQLSelect(String SQLString, String columnName) {
		ResultSet results = null;	
		String resultValue="";
		try (Connection connection = DbManager.getDBConnection(TestConfig.dbConnectionUrlHL_DATA);
				Statement statement = connection.createStatement();) {
			results = statement.executeQuery(SQLString);
			while (results.next()) {
				resultValue = results.getString(columnName);
			}				
			connection.close();
			statement.close();
			return resultValue;
		} catch (SQLException se) {
			se.printStackTrace();
			return resultValue;
		} catch (Exception e) {
			e.printStackTrace();
			return resultValue;
		}

	}

	public static void refreshPage() {
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		TestUtilities.logInfo("Refresh Page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Uchian, Harmik']")));
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	}
}



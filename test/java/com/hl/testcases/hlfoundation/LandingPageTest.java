package com.hl.testcases.hlfoundation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hl.base.TestBase;
import com.hl.pages.hlfoundation.LandingPage;
import com.hl.pages.hlfoundation.NewMatchingRequestPage;

import io.github.bonigarcia.wdm.WebDriverManager;



public class LandingPageTest extends TestBase{

	public LandingPage landingPage;
	public NewMatchingRequestPage newMatchingRequest;
	
	@BeforeClass
	void SetupC() {
		setDefaultUser();
	}
	
	
	void SetupClass() {
		String testURL = config.getProperty("baseurl_HLFoundation");
		driver.get(testURL);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Navigated to : " + testURL);
		landingPage=new LandingPage(driver);
		newMatchingRequest=new NewMatchingRequestPage(driver);
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byLogo));
	}

	@AfterMethod
	void TearDownClass() {
		driver.manage().deleteAllCookies();
		driver.quit();
//		if (driver == null) {
			PropertyConfigurator
					.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			browser = (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
					? System.getenv("browser")
					: config.getProperty("browser");

			if (browser.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if (browser.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				log.debug("Chrome Lunched.");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
//		}
	}
	
	@Test(priority = 0, enabled = true)
	public void Verify_More_Than_A_Year_User() {
		executeSQLUpdateStaff("ADMIN","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isNotLessThanOneYearMessageVisible();
		landingPage.isCreateNewMatchingEnable(TestBase.ExceptionHandling.FAIL);
	}

	@Test(priority = 1, enabled = true)
	public void Verify_Less_Than_A_Year_User() {
		executeSQLUpdateStaff("ADMIN","6 Associate","2023-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		landingPage.isLessThanOneYearMessageVisible();
	}


	@Test(priority = 2, enabled = true)
	public void Verify_Admin_User() {
		executeSQLUpdateStaff("ADMIN","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isCreateNewMatchingEnable(TestBase.ExceptionHandling.FAIL);
	}

	@Test(priority = 3, enabled = true)
	public void Verify_CORP_User() {
		executeSQLUpdateStaff("CORP","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isCreateNewMatchingEnable(TestBase.ExceptionHandling.FAIL);
	}

	@Test(priority = 4, enabled = true)
	public void Verify_FIN_User() {
		executeSQLUpdateStaff("FIN","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isCreateNewMatchingEnable(TestBase.ExceptionHandling.FAIL);
	}

	@Test(priority = 5, enabled = true)
	public void Verify_AG_TEMP_User() {
		executeSQLUpdateStaff("AG-TEMP","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isFullTimeMessageVisible();
	}

	@Test(priority = 6, enabled = true)
	public void Verify_HL_TEMP_User() {
		executeSQLUpdateStaff("HL-TEMP","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isFullTimeMessageVisible();
	}

	@Test(priority = 7, enabled = true)
	public void Verify_INTERN_User() {
		executeSQLUpdateStaff("INTERN","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isFullTimeMessageVisible();
	}

	@Test(priority = 8, enabled = true)
	public void Verify_Blank_Staff_Type_User() {
		executeSQLUpdateStaff("","6 Associate","2022-03-14 00:00:00.000");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isFullTimeMessageVisible();
	}

	@Test(priority = 9, enabled = true)
	public void Verify_Employed_F_User() {
		executeSQLUpdateStaff("ADMIN","6 Associate","2022-03-14 00:00:00.000","F");
		SetupClass();
		//driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.byUserName));
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		landingPage.isEmployeeMessageVisible();
	}

}

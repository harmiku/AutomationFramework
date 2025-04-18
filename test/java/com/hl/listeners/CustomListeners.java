package com.hl.listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.hl.base.TestBase;
import com.hl.utilities.ExtentManager;
import com.hl.utilities.TestUtilities;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener, WebDriverEventListener, IInvokedMethodListener  {

	String sheetName = "TestResults";

	public void onTestStart(ITestResult result) {
		test = extRepo.startTest(result.getName());
		// if (!TestUtilities.isTestRunnable(result.getName(),excel)) {
		// throw new SkipException("Skipping the test "+result.getName());
		// }
		softAssert = new SoftAssert();
	}

	public void onTestSuccess(ITestResult result) {

		TestUtilities.captureScreenshot();
		log.info(result.getName() + " - Test completed");
		test.log(LogStatus.PASS, "Test completed");
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtilities.screenshotPath + TestUtilities.screenshotName));

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ "><img src=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ " height=200 width=200></img></a>");

		extRepo.endTest(test);
		extRepo.flush();

		int rows = excel.getRowCount(sheetName);
		excel.setCellData(sheetName, "TestName", rows + 1, result.getName());
		excel.setCellData(sheetName, "TestResult", rows + 1, "Pass");
		excel.setCellData(sheetName, "RunDate", rows + 1, new Date().toLocaleString());
	}

	public void onTestFailure(ITestResult result) {

		TestUtilities.captureScreenshot();
		log.error("Unexpected error => '" + result.getThrowable());
		test.log(LogStatus.FAIL, "Test Failed: " + result.getThrowable().getMessage());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotPath + TestUtilities.screenshotName));

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ "><img src=" + TestUtilities.screenshotPath + TestUtilities.screenshotName
				+ " height=200 width=200></img></a>");

		extRepo.endTest(test);
		extRepo.flush();

		int rows = excel.getRowCount(sheetName);
		excel.setCellData(sheetName, "TestName", rows + 1, result.getName());
		excel.setCellData(sheetName, "TestResult", rows + 1, "Fail");
		excel.setCellData(sheetName, "RunDate", rows + 1, new Date().toLocaleString());
	}

	public void onTestSkipped(ITestResult result) {
		log.info(" SKIPPED the test : " + result.getName());
		test.log(LogStatus.SKIP, " SKIPPED the test : " + result.getName());
		extRepo.endTest(test);
		extRepo.flush();

		int rows = excel.getRowCount(sheetName);
		excel.setCellData(sheetName, "TestName", rows + 1, result.getName());
		excel.setCellData(sheetName, "TestResult", rows + 1, "Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		extRepo = ExtentManager.getInstance(context.getName());
		//extRepo.config().reportName("GAD");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onStart(ISuite suite) {
		if (driver == null) {
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

			// if (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
			// {

			// browser = System.getenv("browser");
			// } else {

			// browser = config.getProperty("browser");

			// }

			browser = (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
					? System.getenv("browser")
					: config.getProperty("browser");

			// config.setProperty("browser", browser);

			if (browser.contains("chrome")) {
				// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") +
				// "\\src\\test\\resources\\executables\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if (browser.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				log.debug("Chrome Lunched.");
			}

			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}
	}

	public void onFinish(ISuite suite) {
		if (driver != null)
			driver.quit();
		log.debug("Suite test execution completed.");
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(testResult.getName().equals("setupSuite") || testResult.getName().equals("setupClass")) return;
		/*		TestUtilities.captureScreenshot();
		log.info(testResult.getName() + " - Test completed");
		test.log(LogStatus.PASS, "Test completed");
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtilities.screenshotPath + TestUtilities.screenshotName));
*/		
		if(testResult.getStatus()!=2)
			softAssert.assertAll();
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		 //System.out.println("Before Invocation Method Started For: " + method.getTestMethod().getMethodName() + "of Class:" + testResult.getTestClass());		
	}

}

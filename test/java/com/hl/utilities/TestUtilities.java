package com.hl.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.LogStatus;
import com.hl.base.TestBase;

public class TestUtilities extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		screenshotPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\";
		try {
			FileUtils.copyFile(srcFile, new File(screenshotPath + screenshotName));
		} catch (IOException e) {
			test.log(LogStatus.FAIL, "FAIL=> Can not take a Screenshot");
		}

	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	public static Hashtable<String, String> getData(String sheetName) {
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Hashtable<String, String> table = new Hashtable<String, String>();
		for (int i = 2; i <= rows; i++)
			for (int j = 0; j < cols; j++) {
				table.put(excel.getCellData(sheetName, j, 1), excel.getCellData(sheetName, j, i));
			}
		return table;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "TestSuite";
		int rows = excel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			String testCase = excel.getCellData(sheetName, "TestName", rowNum);
			if (testName.equalsIgnoreCase(testCase)) {
				String runMode = excel.getCellData(sheetName, "RunMode", rowNum);
				if (runMode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static String convertToPhoneFormat(String phoneNumber) {
		return "(" + phoneNumber.substring(0, 3) + ") " + phoneNumber.substring(3, 6) + " - "
				+ phoneNumber.substring(6, 10);
	}

	public static String convertToSSNFormat(String SSNNumber) {
		return SSNNumber.substring(0, 3) + "-" + SSNNumber.substring(3, 5) + "-" + SSNNumber.substring(5, 9);
	}
	
	public static void copyPaste(String copyPasteString) throws AWTException, InterruptedException {
		StringSelection ss = new StringSelection(copyPasteString);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(500);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	}
			
	public static void logInfo(String infoMessage) {
		log.info(infoMessage);
		test.log(LogStatus.INFO, infoMessage);
	}
	
	public static void logInfoWithScreenshot(String infoMessage) {
		TestUtilities.captureScreenshot();
		logInfo(infoMessage);
		test.log(LogStatus.INFO, test.addScreenCapture(TestUtilities.screenshotName));
	}

	public static void logPass(String passMessage) {
		log.info(passMessage);
		test.log(LogStatus.PASS, passMessage);
	}
	
	public static void logPassWithScreenshot(String passMessage) {
		TestUtilities.captureScreenshot();
		logPass(passMessage);
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtilities.screenshotName));
	}

	public static void logFailed(String errorMessage) {
		log.error(errorMessage);
		test.log(LogStatus.FAIL, errorMessage);
		softAssert.fail("Error => '" + errorMessage);		
	}
	
	public static void logFailedWithScreenshot(String errorMessage) {
		TestUtilities.captureScreenshot();
		logFailed(errorMessage);
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
	}

	public static void logWarning(String errorMessage) {
		log.warn(errorMessage);
		test.log(LogStatus.WARNING, errorMessage);
	}
	
	public static String addToCurrentDate(int year, int month, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		if(year!=0) c.add(Calendar.YEAR, year);
		if(month!=0) c.add(Calendar.MONTH, month);
		if(day!=0) c.add(Calendar.DATE, day);
		dt = c.getTime();
		return sdf.format(dt);
	}

	public static String addToCurrentDate(int year, int month, int day, String...format) {
		SimpleDateFormat sdf;
		if(format.length==0) 
			sdf = new SimpleDateFormat("MM/dd/yyyy");
		else
			sdf = new SimpleDateFormat(format[0]);
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		if(year!=0) c.add(Calendar.YEAR, year);
		if(month!=0) c.add(Calendar.MONTH, month);
		if(day!=0) c.add(Calendar.DATE, day);
		dt = c.getTime();
		return sdf.format(dt);
	}
}

package com.hl.utilities;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {

	private static ExtentReports extent;
	public static String suiteName;

	public static ExtentReports getInstance(String reportName) {

		// One seperate Report File for each <test name=""> in testng.xml
		/*	Date d = new Date();
			reportName += "_"+d.toString().replace(":","_").replace(" ", "_") + ".html";
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+reportName, true,
					DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ExtentReportConfig.xml"));
		*/
		
		// Only one Report File for all tests 
		if (extent == null) {
			reportName = "ExtentReport.html";
			//reportName += "_"+(new Date()).toString().replace(":","_").replace(" ", "_") + ".html";
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+reportName, true,
					DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ExtentReportConfig.xml"));

		}
		
		return extent;
	}

}

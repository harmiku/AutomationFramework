package com.hl.rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


// Sequel Pro - for Database
// Driver Download - https://dev.mysql.com/downloads/connector/odbc/
public class DatabaseTesting {
	// Connection object
	static Connection conn = null;
	// Statement object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL
	public static String DB_URL = "jdbc:jtds:sqlserver://192.168.1.254;DatabaseName=HL_DATA";

	// Constant for Database Username
	public static String DB_USER = "hlhz\\huchian";
	// Constant for Database Password
	public static String DB_PASSWORD = "Serlico1235";
	// Driver
	public static String driver = "net.sourceforge.jtds.jdbc.Driver";
	
	// WebDriver
	// public static WebDriver dv;

	@BeforeClass
	public void beforeClass() {
		// Intialize WebDriver
		// dv = new FirefoxDriver();
		
		// Properties for creating connection to database
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "");
	    
		try {
			// STEP 1: Register JDBC driver
			//Object newInstance = Class.forName(driver).newInstance();
			
			// STEP 2: Get connection to DB
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// conn = DriverManager.getConnection(DB_URL, props);		
			System.out.println("Connected database successfully...");
			
			// STEP 3: Statement object to send the SQL statement to the Database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws SQLException {
        String query = "select * from HL_STAFF";
        try {
        	// STEP 4: Extract data from result set
        	results = stmt.executeQuery(query);
        	while (results.next()) {
        		int id = results.getInt("staffid");
        		String last = results.getString("lname");
        		String first = results.getString("fname");
        		String office = results.getString("office");
        		
        		// Display Values
        		System.out.println("STAFFID: " + id);
        		System.out.println("Last Name: " + last);
        		System.out.println("First Name: " + first);
        		System.out.println("Office: " + office);
        		
        		// From GUI
//        		WebElement element = dv.findElement(By.id("uname"));
//        		String actualUserName = element.getText();
//        		Assert.assertEquals(actualUserName, first);
            }
        	results.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				conn.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
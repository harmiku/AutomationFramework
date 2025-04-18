package com.hl.rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hl.utilities.DbManager;
import com.hl.utilities.TestConfig;

public class DatabaseTest {
	private static ResultSet resultSet = null;
	static String selectSql;

	
	
	// Connection object
	static Connection conn = null;
	// Statement object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL

	//	public static String DB_URL = "jdbc:jtds:sqlserver://192.168.1.254;DatabaseName=HL_DATA";
	public static String DB_URL = "jdbc:sqlserver://Slv-haltestdb;DatabaseName=HL_DATA;integratedSecurity=true";
			
	// Constant for Database Username
	public static String DB_USER = "hlhz\\huchian";
	// Constant for Database Password
	public static String DB_PASSWORD = "Serlico1235";
	// Driver

	//public static String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String driver ="net.sourceforge.jtds.jdbc.Driver";
	
	
	
	public static void main(String[] args) {

/*
		try (Connection connection = DbManager.getDBConnection(TestConfig.dbConnectionUrlHL_DATA);
				Statement statement = connection.createStatement();) {

			//selectSql = "update hs set STAFF_TYPE='Admin' FROM HL_DATA.dbo.HL_STAFF AS HS where HS.FULL_NAME ='uchian, harmik'";
			//PreparedStatement preparedStatement= connection.prepareStatement(selectSql);
			//preparedStatement.executeUpdate();

			selectSql = "SELECT * from HL_DATA.dbo.HL_STAFF where FULL_NAME ='uchian, harmik'";
			resultSet = statement.executeQuery(selectSql);

			while (resultSet.next()) {
				// System.out.println(resultSet.getString(1) + " " + resultSet.getString(5));
				int id = resultSet.getInt("staffid");
				String FullName = resultSet.getString("full_name");
				String STAFFTYPE = resultSet.getString("STAFF_TYPE");
				String MANAGEMENTLEVEL = resultSet.getString("MANAGEMENT_LEVEL");

				// Display Values
				System.out.println("STAFFID: " + id);
				System.out.println("Full Name: " + FullName);
				System.out.println("STAFF_TYPE: " + STAFFTYPE);
				System.out.println("MANAGEMENT_LEVEL: " + MANAGEMENTLEVEL);

			}
			resultSet.close();
			connection.close();
			statement.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/


			
				// Properties for creating connection to database
				Properties props = new Properties();
				props.setProperty("user", "");
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
//		        		WebElement element = dv.findElement(By.id("uname"));
//		        		String actualUserName = element.getText();
//		        		Assert.assertEquals(actualUserName, first);
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
}

/*
@AfterClass
public void afterClass() {
	try {
		if (resultSet != null) resultSet.close();
		if (connection != null) connection.close();
		if (statement != null) statement.close();
	} catch (SQLException se) {
		se.printStackTrace();
	}
}
*/

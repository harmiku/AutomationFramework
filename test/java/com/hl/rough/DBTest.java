package com.hl.rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBTest {
	static String selectSql;

	// Connection object
	static Connection conn = null;
	// Statement object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL

	public static String DB_URL = "jdbc:sqlserver://Slv-haltestdb;DatabaseName=HL_DATA;integratedSecurity=true";
			
	public static String DB_USER = "";//"hlhz\\huchian";
	public static String DB_PASSWORD ="";// "Serlico1235";

	
	public static String driver ="net.sourceforge.jtds.jdbc.Driver";
	
	public static void main(String[] args) {
		
				Properties props = new Properties();
				props.setProperty("user", "");
				props.setProperty("password", "");
			    
				try {
					System.out.println("Connecting to a selected database...");
					conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					System.out.println("Connected database successfully...");
					System.out.println("Creating statement...");
					stmt = conn.createStatement();
				} catch (Exception e) {
					e.printStackTrace();
				}

				String query = "select * from HL_STAFF";
		        try {
		        	results = stmt.executeQuery(query);
		        	while (results.next()) {
		        		int id = results.getInt("staffid");
		        		String last = results.getString("lname");
		        		String first = results.getString("fname");
		        		String office = results.getString("office");
		        		
		        		// Display Values
		        		System.out.print("STAFFID: " + id);
		        		System.out.print("         Last Name: " + last);
		        		System.out.print("         First Name: " + first);
		        		System.out.println("       Office: " + office);
		        		
		            }
		        	results.close();
				} catch (SQLException se) {
					se.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}


package com.hl.rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest_CC {
	static String selectSql;

	// Connection object
	static Connection conn = null;
	// Statement object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL
	public static String DB_URL = "jdbc:sqlserver://hl-t-wus2-alzca-sqlsvr-01.database.windows.net;DatabaseName=CONFLICTS_CHECK;integratedSecurity=false";
	public static String DB_USER = "conflictscheckdba";
	public static String DB_PASSWORD ="T3sting123";

	public static String driver ="net.sourceforge.jtds.jdbc.Driver";

	public static void main(String[] args) {

		try {
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connected database successfully...");
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			list_Request();
			list_HL_STAFF();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void list_HL_STAFF(){
		String query = "select top 10 * from HL_STAFF";
		try {
			results = stmt.executeQuery(query);
			while (results.next()) {
				int id = results.getInt("staffid");
				String first = results.getString("fname");
				String last = results.getString("lname");
				String office = results.getString("office");
				// Display Values
				System.out.print("STAFFID: " + id +"         ");
				System.out.print("First Name: " + String.format("%-20s", first));
				System.out.print("Last Name: " + String.format("%-25s", last));
				System.out.println("Office: " + String.format("%-15s", office));
			}
			results.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void list_Request(){
		String query = "select top 10 * from REQUEST";
		try {
			results = stmt.executeQuery(query);
			while (results.next()) {
				int id = results.getInt("REQUEST_ID");
				String REQUEST_DATE = results.getString("REQUEST_DATE");
				// Display Values
				System.out.print("REQUEST_ID: " + id);
				System.out.println("         REQUEST_DATE: " + REQUEST_DATE);
			}
			results.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


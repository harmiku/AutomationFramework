package com.hl.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DbManager {

	public static Connection getDBConnection(String dbConnectionUrl) {
		try {
			// Class.forName(TestConfig.driver);
			return DriverManager.getConnection(dbConnectionUrl);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			// monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
			// TestConfig.subject+" - (Script failed with Error, connection not
			// established)", TestConfig.messageBody,
			// TestConfig.attachmentPath, TestConfig.attachmentName);
			return null;
		}
	}

}

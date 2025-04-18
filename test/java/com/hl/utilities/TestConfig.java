package com.hl.utilities;

public class TestConfig {
	public static String server = "smtp.live.com";
	public static String from = "harmiku@hotmail.com";
	public static String password = "";
	public static String[] to = { "harmiku@hotmail.com" };
	public static String subject = "Automation Project Report";

	public static String messageBody = "TestMessage";
	public static String attachmentPath="c:\\screenshot\\2017_10_3_14_49_9.jpg";
	public static String attachmentName="error.jpg";

	// SQL DATABASE DETAILS
	public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// "net.sourceforge.jtds.jdbc.Driver";
	public static String dbConnectionUrlHL_DATA = "jdbc:sqlserver://Slv-haltestdb;DatabaseName=HL_DATA;integratedSecurity=true";
	public static String dbConnectionUrlHL_FOUNDATION = "jdbc:sqlserver://Slv-haltestdb;DatabaseName=HL_FOUNDATION;integratedSecurity=true";
	public static String dbUserName = "";
	public static String dbPassword = "";

	// MYSQL DATABASE DETAILS
	public static String mysqldriver = "com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";
}

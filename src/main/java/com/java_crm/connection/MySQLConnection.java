package com.java_crm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static final String url = "jdbc:mysql://localhost:3307/java_crm";
	public static final String userName = "root";
	public static final String password = "admin";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Khong tim thay driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Khong tim thay database");
			e.printStackTrace();
		}
		return null;
	}
}

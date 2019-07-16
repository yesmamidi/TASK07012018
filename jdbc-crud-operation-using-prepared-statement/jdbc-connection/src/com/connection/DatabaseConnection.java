package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	static Connection connection = null;

	static public Connection getDatabaseConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");

		} catch (ClassNotFoundException e) {
			System.out.println("error in class loading" + e.getMessage());
		} catch (Exception e) {
			System.out.println("connection error" + e.getMessage());
		}
		return connection;
	}

	static public void closeConnection() { // call this method when you need to close connection
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

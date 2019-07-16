package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DatabaseConnection;

final public class Dao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public final ResultSet getUserRows() {
		connection = DatabaseConnection.getDatabaseConnection();
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from user_tbl");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error when load user data" + e.getMessage());
		}
		return resultSet;
	}

	public final int deleteUser(int userId) {
		connection = DatabaseConnection.getDatabaseConnection();
		int temp = 0;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM user_tbl WHERE user_id=?");
			preparedStatement.setInt(1, userId);
			temp = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			System.out.println("error in deleting user" + e.getMessage());
		} finally {
			DatabaseConnection.closeConnection();
		}
		return temp;
	}

	public final ResultSet getSingleRow(int userID) {
		connection = DatabaseConnection.getDatabaseConnection();
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM user_tbl WHERE user_id=?");
			preparedStatement.setInt(1, userID);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in fetching single row" + e.getMessage());
		}

		return resultSet;
	}

	public int userUpdate(int userID, String name, String email, String password) {
		connection = DatabaseConnection.getDatabaseConnection();
		int temp = 0;
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE user_tbl SET user_name=?,user_email=?,user_password=? WHERE user_id=?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, userID);

			temp = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("error in update user");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return temp;
	}

}

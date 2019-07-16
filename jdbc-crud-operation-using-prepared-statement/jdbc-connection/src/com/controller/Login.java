package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;

@WebServlet("/verify")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userEmail");
		String password = request.getParameter("userPass");
		PrintWriter writer = response.getWriter();
		Connection connection = DatabaseConnection.getDatabaseConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM  user_tbl WHERE user_email=? AND user_name=?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
		   ResultSet resultSet =  preparedStatement.executeQuery();
		   if(resultSet.next()) {
			   writer.println(resultSet.getInt(1));
			   writer.println(resultSet.getString(2));
			   writer.println(resultSet.getString(3));
			   writer.print(resultSet.getLong(4));
		   }
		   else {
			   writer.print("no record found");
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

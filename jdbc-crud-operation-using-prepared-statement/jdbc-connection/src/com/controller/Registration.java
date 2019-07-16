package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;
import com.dao.Dao;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + "/registration.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		String actionCode = request.getParameter("actionCode");
		if (actionCode.equals("addUser")) {
			String name = request.getParameter("userName");
			String email = request.getParameter("userEmail");
			String password = request.getParameter("userPassword");
			Connection connection = DatabaseConnection.getDatabaseConnection();
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO user_tbl(user_name,user_email,user_password)VALUES(?,?,?)");
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, password);
				int temp = preparedStatement.executeUpdate();
				if (temp > 0) {
					DatabaseConnection.closeConnection();
					response.sendRedirect(request.getContextPath() + "/view.jsp");
				} else {
					DatabaseConnection.closeConnection();
					writer.println("insert error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (actionCode.equals("deleteUser")) {
			int deleteId = Integer.parseInt(request.getParameter("deleteId"));
			int temp = dao.deleteUser(deleteId);
			if (temp > 0) {
				response.sendRedirect(request.getContextPath() + "/view.jsp");
			} else {
				writer.println("delete error");
			}
		} else if (actionCode.equals("editUser")) {
			int userId = Integer.parseInt(request.getParameter("editId"));
			String name = request.getParameter("userName");
			String email = request.getParameter("userEmail");
			String password = request.getParameter("userPassword");

			int temp = dao.userUpdate(userId, name, email, password);
			if (temp > 0) {
				response.sendRedirect(request.getContextPath() + "/view.jsp");
			} else {
				writer.println("delete error");
			}
		}
	}
}

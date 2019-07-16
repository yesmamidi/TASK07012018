<%@page import="com.connection.DatabaseConnection"%>
<%@page import="com.dao.Dao"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title>view</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#No</th>
					<th>Name</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>Action</th>
				</tr>
			</thead>
			  <%
			    Dao dao = new Dao();
			    ResultSet resultSet = dao.getUserRows();  
			    
			    while(resultSet.next()){ 
			   %>
			     <tr>
			       <td><%= resultSet.getInt(1) %></td>
			       <td><%= resultSet.getString(2) %></td>
			       <td><%= resultSet.getString(3) %></td>
			       <td><%= resultSet.getString(4) %></td>
			       <td>
			         <form action="edit.jsp" method="post">
			             <input type="hidden" name="editID" value="<%= resultSet.getInt(1) %>">
			            <button type="submit" class="btn btn-success">
			              <span class="fa fa-pencil"></span>
			            </button>
			         </form>
			          <form action="./Registration" method="post">
			            <input type="hidden" name="actionCode" value="deleteUser">
			          <input type="hidden" name="deleteId" value="<%= resultSet.getInt(1) %>">
			            <button type="submit" class="btn btn-danger">
			              <span class="fa fa-trash"></span>
			            </button>
			         </form>
			       </td>
			     </tr>
			   <% }
			     DatabaseConnection.closeConnection();
			    %>
			<tbody>
			</tbody>
		</table>
	</div>

</body>
</html>

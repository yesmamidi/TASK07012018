<%@page import="com.connection.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
</head>
<body>
	<%
	ResultSet resultSet = null;
 try{  
	 
	 int editID = Integer.parseInt(request.getParameter("editID"));
	  Dao dao = new Dao();
	   resultSet = dao.getSingleRow(editID);
	   if(resultSet.next()){
 %> 
 	<form action="./Registration" method="post">
		<input type="hidden" name="actionCode" value="editUser">
		<input type="hidden" name="editId" value="<%= resultSet.getInt(1) %>">
		 <input
			type="text" name="userName" placeholder="enter name"
			value="<%= resultSet.getString(2) %>"> <input type="email"
			name="userEmail" placeholder="enter Email"
			value="<%= resultSet.getString(3) %>"> <input type="password"
			name="userPassword" placeholder="enter password"
			value="<%= resultSet.getString(4) %>"> <input type="submit"
			value="edit">
   
	</form>
 <%
 	 DatabaseConnection.closeConnection();	   
	   }
	   else{
		   response.sendRedirect("view.jsp");
	   }
  }catch(NumberFormatException e){
	  response.sendRedirect("view.jsp");
  }
 catch(NullPointerException n){
	 
 }
 
 %>

	
</body>
</body>
</html>
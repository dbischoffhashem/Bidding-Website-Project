<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h1>Sign Up</h1>
</div>
<br>
To make an account all you have to do is enter in the following:
<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Passwords don't match. Try again.</p>
<% } %>
<form action="signupservlet" method="post">
	<table>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="fname"></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lname"></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td>Confirm Password:</td>
			<td><input type="password" name="confirm"></td>
		</tr>
		<tr>
			<td>Do you wish to be a seller?</td>
			<td>
				<label>
           		<input type="radio" name="choice" value="yes">
           		Yes
       		</label>
       		<br>
       		<label>
           		<input type="radio" name="choice" value="no">
           		No
       		</label>
       	</td>
		</tr>
		<tr>
			<td> </td>
			<td><br><input type="submit" value="Create Account"></td>
		</tr>
	</table>
</form>
</body>
</html>


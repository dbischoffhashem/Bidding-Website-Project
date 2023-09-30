<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div align="center">
<h1>Representative Login</h1>
</div>
<br>
<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Wrong username or password. Try again.</p>
<% } %>
<form action="repservlet" method="post">
	<table>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="txtName"></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="txtPwd"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Log In"></td>
		</tr>
	</table>
</form>
</body>
</html>


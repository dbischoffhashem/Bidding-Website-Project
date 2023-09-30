<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div align="center">
<h1>Customer Representative Home</h1>
</div>
<br>
Reset a customer's password:
   <form action="resetpassword.jsp" method="get">
       <input type="submit" value="reset">
   </form>
<br>
Delete a bid:
   <form action="deletebid.jsp" method="get">
       <input type="submit" value="delete bid">
   </form>
   <br>
Delete customer's account
	<form action="deleteaccount.jsp" method="get">
       <input type="submit" value="delete bid">
   </form>
   <br>
Edit a customer's bid:
	<form action="EditBid.jsp" method="get">
       <input type="submit" value="edit bid">
   </form>
   <br>
</body>
</html>

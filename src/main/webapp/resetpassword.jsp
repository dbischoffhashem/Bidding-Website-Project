<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
</head>
<body>
   <h1>Reset User Password</h1>
   <% if (request.getParameter("error") != null) { %>
       <p style="color: red;">An error occurred during password reset. Please try again.</p>
   <% } %>
   <form action="resetpasswordservlet" method="post">
       <label for="userId">User ID:</label>
       <input type="text" name="userId" required><br>
      
       <label for="newPassword">New Password:</label>
       <input type="password" name="newPassword" required><br>
      
       <input type="submit" value="Reset Password">
   </form>
</body>
</html>

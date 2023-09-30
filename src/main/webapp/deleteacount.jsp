<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete User Account</title>
</head>
<body>
   <h1>Delete User Account</h1>
   <% if (request.getParameter("error") != null) { %>
       <p style="color: red;">An error occurred during account deletion. Please try again.</p>
   <% } %>
   <form action="DeleteAccountServlet" method="post">
       <label for="userId">User ID:</label>
       <input type="text" name="userId" required><br>
      
       <input type="submit" value="Delete Account">
   </form>
</body>
</html>

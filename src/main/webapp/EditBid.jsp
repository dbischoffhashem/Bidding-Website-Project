<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User Bid</title>
</head>
<body>
   <h1>Edit User Bid</h1>
   <% if (request.getParameter("error") != null) { %>
       <p style="color: red;">An error occurred during bid editing. Please try again.</p>
   <% } %>
   <form action="EditUserBidServlet" method="post">
       <label for="bidId">Bid ID:</label>
       <input type="text" name="bidId" required><br>
      
       <label for="newBidAmount">New Bid Amount:</label>
       <input type="text" name="newBidAmount" required><br>
      
       <input type="submit" value="Edit Bid">
   </form>
</body>
</html>
>
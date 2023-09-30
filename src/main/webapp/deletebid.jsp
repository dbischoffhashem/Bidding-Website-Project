<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Bid</title>
</head>
<body>
   <h1>Delete Bid</h1>
   <% if (request.getParameter("error") != null) { %>
       <p style="color: red;">An error occurred during bid deletion. Please try again.</p>
   <% } %>
   <form action="DeleteBidServlet" method="post">
       <label for="bidId">Bid ID:</label>
       <input type="text" name="bidId" required><br>
      
       <input type="submit" value="Delete Bid">
   </form>
</body>
</html>

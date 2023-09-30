<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>Sales Summary Report</title>
</head>
<body>
   <h1>Sales Summary Report</h1>
  
   <c:forEach var="entry" items="${reportData}">
       <p>${entry}</p>
   </c:forEach>
</body>
</html>

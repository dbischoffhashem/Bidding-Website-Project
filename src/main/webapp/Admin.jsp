<!DOCTYPE html>
<html>
<head>
   <title>Admin Page</title>
</head>
<body>
   <h1>Sales Summary Report</h1>
  
   <form action="SummaryServlet" method="post">
       <label>Select Report Type:</label>
       <select name="reportType">
           <option value="totalEarnings">Total Earnings</option>
           <option value="earningsPerItem">Earnings Per Item</option>
           <option value="earningsPerItemType">Earnings Per Item Type</option>
           <option value="bestSellingItems">Best Selling Items</option>
           <option value="bestSellingUsers">Best Selling Users</option>
       </select>
       <input type="submit" value="Generate Report">
   </form>
</body>
</html>

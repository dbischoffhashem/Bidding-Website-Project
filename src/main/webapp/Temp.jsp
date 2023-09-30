<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Item</title>
    
    <style>
    	.specifications ul {
            list-style-type: none;
            padding: 0;
        }
        
        .specifications li {
            margin-bottom: 10px; /* Add some spacing between items */
            padding-bottom: 5px; /* Add padding at the bottom of each item */
        }
        
        .specifications label {
            display: inline-block;
            width: 150px; /* Adjust the width of the label as needed */
    </style>
       <script>
        function populateSubcategories(category) {
            var subDropdown = document.getElementById('subcategory');
            subDropdown.innerHTML = ''; // Clear existing options

            // Add a default "Select a subcategory" option
            //addSubcategoryOption('all');

            if (category === 'electronics') {
                addSubcategoryOption('phone');
                addSubcategoryOption('computer');
                addSubcategoryOption('camera');
            } else if (category === 'home') {
                addSubcategoryOption('decor');
                addSubcategoryOption('furniture');
                addSubcategoryOption('dish');
            } else if (category === 'clothing') {
                addSubcategoryOption('shirt');
                addSubcategoryOption('pants');
                addSubcategoryOption('jacket');
            } else if (category === 'accessories') {
                addSubcategoryOption('bag');
                addSubcategoryOption('jewelry');
                addSubcategoryOption('hat');
            } else if (category === 'toys') {
                addSubcategoryOption('stuffed animal');
                addSubcategoryOption('game');
                addSubcategoryOption('outdoor toy');
            } else if (category === 'vehicles') {
                addSubcategoryOption('car');
                addSubcategoryOption('truck');
                addSubcategoryOption('motorcycle');
            }
        }

        function addSubcategoryOption(subcategory) {
            var option = document.createElement('option');
            option.value = subcategory;
            option.textContent = subcategory;
            document.getElementById('subcategory').appendChild(option);
        }
    </script>
</head>
<body>

<h1>List an Item</h1>  
   <form method="get" action="SpecificationsForListing">
        <select name="category" size="1" onchange="populateSubcategories(this.value)">
            <option value="">Select a category</option>
            <option value="clothing">Clothing</option>
            <option value="electronics">Electronics</option>
            <option value="home">Home</option>
            <option value="accessories">Accessories</option>
            <option value="toys">Toys</option>
            <option value="vehicles">Vehicles</option>
        </select>
        <select name="subcategory" id="subcategory" size="1">
            <option value="">Select a subcategory</option>
        </select>
        <input type="submit" value="Next">
    </form>
    
  <div id="specifications" class="specifications">
        <h2>Required Specifications:</h2>
        <ul>
            <%
                List<String> inames = (List<String>) request.getAttribute("inames");
                if (inames != null) {
                    for (String iname : inames) {
            %>
            <li>
                <!-- Display specification next to the textbox -->
                <label><%= iname %>:</label>
                <input type="text">
                <br>
            </li>
            <%
                    }
                } else {
            %>
            <li>No items to display</li>
            <%
                }
            %>
        </ul>
    </div>
    
</body>
</html>

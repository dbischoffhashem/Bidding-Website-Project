<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Item</title>
    
    <style>
    	#WelcomeMessage {
        	display: none;        
    	}
        /* Modified styles for text colors */
        body {
            background-color: #fff;
            font-family: Arial, sans-serif;
        }
        
        .container {
            margin: 20px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        
        .specifications ul {
            list-style-type: none;
            padding: 0;
        }
        
        .specifications li {
            margin-bottom: 10px;
            padding-bottom: 5px;
        }
        
        .specifications label {
            display: inline-block;
            width: 150px;
            color: #444; /* Very dark grey for label */
            font-weight: bold;
        }
        
        .specifications input[type="text"] {
            width: 97%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        
        .specifications span {
            color: #888; /* Light grey for description */
        }

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
<body >

	<h1 id="WelcomeMessage">Welcome, <span id="username"></span>!</h1>	

<div class="container">
    <h1>List an Item</h1>
    
    <form method="get" action="SpecificationsForListing">
        <input type="hidden" name="username" id="hiddenUsername" value="">
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
    
   
        
         <form method="post" action="AddListingToDatabase" class="submit-button show-button">
            <input type="hidden" name="username" id="hiddenUsername2" value="">
            <!-- <input type="hidden" name="subcategory" id="hiddenSubcategory" value="">-->
         <div id="specifications" class="specifications">
        <h2>Required Specifications:</h2>
        
        	<label><span style="color: #333; font-weight: bold;">Subcategory:</span></label>
         	<input type="text" name="subcategory">
         	<span>(provide the subcategory you selected from the dropdown)</span>
         	<br>
         	<br>
        	<label><span style="color: #333; font-weight: bold;">Item Name:</span></label>
         	<input type="text" name="iname">
         	<span>(provide a brief name or description for your item)</span>
         	<br>
        	<br>
         	<label><span style="color: #333; font-weight: bold;">Increment By:</span></label>
        	<input type="text" name="increment_by">
        	<span>(provide the increment value)</span>
        	<br>
        	<br>
        	<label><span style="color: #333; font-weight: bold;">End Date & Time:</span></label>
        	<input type="datetime-local" name="end_date_time">
        	<span>(provide the end date and time)</span>
    
         	
         
        <ul>
            <%
                List<List<String>> resultData = (List<List<String>>) request.getAttribute("resultData");
                if (resultData != null && !resultData.isEmpty()) {
                    for (List<String> row : resultData) {
                        String specName = row.get(0);
                        String description = row.get(1);
            %>
            <li>
                <label><span style="color: #333; font-weight: bold;"><%= specName %>:</span></label>
                <input type="text" name="<%= specName %>">
                <span><%= description %></span>
            </li>
            <%
                    }
                } else {
            %>
            
            <li style="color: red;">Select a category and subcategory from the dropdown and click "Next"</li>
            <%
                }
            %>
        </ul>
            <input type="submit" value="List Item">
        </form>
    </div>

<script>
	const usernameElement = document.getElementById('username');
		const urlParams = new URLSearchParams(window.location.search);
		const loggedInUsername = urlParams.get('username');
	// Check if the user is logged in. If not, redirect to the login page

	if (!loggedInUsername) {

	window.location.href = 'Login.jsp';

	} else {

		usernameElement.textContent = loggedInUsername;
	}
		
	// Function to handle logout
	 function logout() {
		const xhr = new XMLHttpRequest();
		xhr.open('GET', 'LogoutServlet', true);
		xhr.onreadystatechange = function() {
			
			if (xhr.readyState === XMLHttpRequest.DONE) {
				window.location.href = 'Login.jsp'; // Redirect to the login page
				}
			};
			
			xhr.send();
			}
	
	</script>
	
	<script>
	
	
	//GET USERNAME TO KEEP IN URL EVEN WHEN DATA IS QUERIED AND TO PUT IN DATABASE
	// Get the <span> element where the username is displayed
	const usernameSpan = document.getElementById('username');
	
	// Get the hidden input element
	const hiddenUsernameInput = document.getElementById('hiddenUsername');
	
	const hiddenUsernameInput2 = document.getElementById('hiddenUsername2');

	// Get the username text from the <span> element
	const displayedUsername = usernameSpan.textContent.trim();

	// Set the value of the hidden input field to the displayed username
	hiddenUsernameInput.value = displayedUsername;
	hiddenUsernameInput2.value = displayedUsername;

	</script>

</body>
</html>

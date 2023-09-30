<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Place Bid</title>
    <link rel="stylesheet" href="./Style.css">
    
    <style>
#WelcomeMessage {
        display: none;        
    }
</style>
</head>
<body>

	<h1 id="WelcomeMessage">Welcome, <span id="username"></span>!</h1>

    <div class="top-bar"></div>
    <img class="BuyMeLogo" src="BuyMeLogo3.png" alt="BuyMe Logo">
    <br>
    <br>
    <br>
    <br>
        <div id="item-details">
        <h2>Item Details:</h2>
        
        <%
        List<List<String>> resultData = (List<List<String>>) request.getAttribute("resultData");
        if (resultData != null && !resultData.isEmpty()) {
            List<String> itemDetails = resultData.get(0); // Get the first row of data
            String itemName = itemDetails.get(0);
            String itemId = itemDetails.get(1);
            String startingPrice = itemDetails.get(2);
            String highestBid = itemDetails.get(3);
            String subcategory = itemDetails.get(4);
        %>
        <p><strong>Name: </strong> <%= itemName %></p>
        <p><strong>Item ID: </strong> <%= itemId %></p>
        <p><strong>Starting Price: $</strong> <%= startingPrice %></p>
        <p><strong>Highest Bid: $</strong> <%= highestBid %></p>
        <p><strong>Subcategory: </strong> <%= subcategory %></p>
        <% 
        } else {
        %>
        <p>No item details available</p>
        <%
        }
        %>
    </div>


    <div id="bid-form">
        <h2>Place Bid</h2>        
        <form method="post" action="PlacebidServlet">
            <label for="bidAmount">Bid Amount:</label>
            <input type="text" name="bidAmount" required>
            <br>
            <br>
            <label for="itemId">Item ID:</label>            
            <input type="number" name="itemId" required>           
            <input type="hidden" name="username" id="hiddenUsername" value="" required>
            <br>
            <br>
            <button type="submit">Place Bid</button>
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

	//GET USERNAME TO KEEP IN URL EVEN WHEN DATA IS QUERIED

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
	
	
	<script>
    // Get the item ID from the URL query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const itemId = urlParams.get('item_id');
    
    // Set the value of the hidden input field for the item ID
    const hiddenItemIdInput = document.getElementById('hiddenItemId');
    hiddenItemIdInput.value = itemId;
</script>

</body>
</html>

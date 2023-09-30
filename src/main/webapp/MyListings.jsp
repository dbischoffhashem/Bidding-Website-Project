<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Listings</title>
<link rel = "stylesheet" href = "./Style.css">

<style>
#WelcomeMessage {
        display: none;        
    }
</style>
	<div class="top-bar"></div>
	<div class="dropdown">		
	</div>
	
</head>
<body>

	<h1 id="WelcomeMessage">Welcome, <span id="username"></span>!</h1>
	
<br>
<br>
<br>
<br>
<br>

<form method="get" action="ShowMyListings">
<input type="hidden" name="username" id="hiddenUsername" value="">
<br>
<input type="submit" value="Show My Listings" onclick="return submitForm();">
</form>
<div id="item-list" class="item-list">
<h2>My Listings:</h2>
<ul>
<%
List<String> inames = (List<String>) request.getAttribute("inames");
if (inames != null) {
for (String iname : inames) {
%>
<li><%= iname %></li>
<%
}
} else {
%>
<li>Click the "Show My Listings" button to see your listings</li>
<%
}
%>
</ul>
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

	// Get the username text from the <span> element
	const displayedUsername = usernameSpan.textContent.trim();

	// Set the value of the hidden input field to the displayed username
	hiddenUsernameInput.value = displayedUsername;

	</script>
	
	
	
	<script>

function submitForm() {
//const selectedCategory = document.querySelector('select[name="category"]').value;
//const selectedSubcategory = document.querySelector('select[name="subcategory"]').value;

// Check if the username is valid
if (loggedInUsername) {
// Redirect to GetItemsServlet with username, category, and subcategory parameters
window.location.href = `GetItemsServlet?username=${loggedInUsername}&category=${selectedCategory}&subcategory=${selectedSubcategory}`;
} else {
// Redirect to the login page
window.location.href = 'Login.jsp';
}
}
</script>

</body>
</html>


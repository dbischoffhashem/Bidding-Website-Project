<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel = "stylesheet" href = "./Style.css">

<style>
#WelcomeMessage {
        display: none;        
    }
</style>
	<div class="top-bar"></div>
	<div class="dropdown">
			
		</div>
	</div>
	
</head>
<body>

	<h1 id="WelcomeMessage">Welcome, <span id="username"></span>!</h1>
<br>
<br>
<br>
<br>
<br>
<form method="get" action="SetAlerts">
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
<br>
<input type="submit" value="Set Alerts" onclick="return submitForm();">
</form>
<div id="item-list" class="item-list">
<h2>Item Names:</h2>
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
<li>No items to display</li>
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
function populateSubcategories(category) {
var subDropdown = document.getElementById('subcategory');
subDropdown.innerHTML = ''; // Clear existing options
// Add a default "Select a subcategory" option
addSubcategoryOption('all');
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
function submitForm() {
const selectedCategory = document.querySelector('select[name="category"]').value;
const selectedSubcategory = document.querySelector('select[name="subcategory"]').value;
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


<script>
//KEEP USERNAME IN URL WHEN NAVIGATING TO CREATELISTING.JSP
  var idValue = document.getElementById("username").textContent;
  var usernameLink = document.getElementById("usernameLink");
  var usernameLinkForMyListings = document.getElementById("usernameLinkForMyListings");

  usernameLink.href = "CreateListing.jsp?username=" + idValue;
  usernameLinkForMyListings.href = "MyListings.jsp?username=" + idValue;
</script>

</body>
</html>

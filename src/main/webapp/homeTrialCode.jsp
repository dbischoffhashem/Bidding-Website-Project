<!-- Load font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- The navigation menu -->
<div class="navbar">
  <div class="subnav">
    <button class="subnavbtn">Clothing <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="#company">Shirts</a>
      <a href="#team">Pants</a>
      <a href="#careers">Jackets</a>
    </div>
  </div>
  <div class="subnav">
    <button class="subnavbtn">Accessories <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="#bring">Jewelry</a>
      <a href="#deliver">Bags</a>
      <a href="#package">Hats</a>
    </div>
  </div>
  <div class="subnav">
    <button class="subnavbtn">Electronics <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="#link1">Phones</a>
      <a href="#link2">Computers</a>
      <a href="#link3">Cameras</a>
    </div>
  </div>
</div>











<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

.navbar {
  overflow: hidden;
  background-color: #333; 
}

.navbar a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.subnav {
  float: left;
  overflow: hidden;
}

.subnav .subnavbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .subnav:hover .subnavbtn {
  background-color: #619cd3;
}

.subnav-content {
  display: none;
  position: absolute;
  left: 0;
  background-color: #619cd3;
  width: 100%;
  z-index: 1;
}

.subnav-content a {
  float: left;
  color: white;
  text-decoration: none;
}

.subnav-content a:hover {
  background-color: #eee;
  color: black;
}

.subnav:hover .subnav-content {
  display: block;
}
</style>
</head>
</html>
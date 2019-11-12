<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	overflow-y: auto;
    background-color: #595959;
    color: white;
}

.split {
	height: 100%;
	width: 60%;
	position: relative;
	top: 0;
	overflow-x: hidden;
}

.left {
	left: 0;
}

.right {
	right: 0;
    position: static;
}

.savedgame {
  border: 2px solid #ccc;
  background-color: #eee;
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  color: black;
}

.vline {
	position: absolute;
	top: 0%;
	left: 60%;
	border-right: 4px solid grey;
	height: 100%;
}

.savedgame span {
  font-size: 20px;
  margin-right: 15px;
}

hr {
	border-width: 4px;
    border-style: solid;
    border-color: grey;
}

</style>
<title>Battleship! - Saved Games</title>
</head>
<body>

<div class="split left">
<h2>Saved Games</h2>

<div class="savedgame">
  <p><span>Chris Fox.</span> CEO at Mighty Schools.</p>
  <p>John Doe saved us from a web disaster.</p>
</div>

</div>

<div class="vline"></div>

<div class="split right">

	<h2>Search Games</h2>
    <h3>Username</h3>
    <input type="text">
    <button>Find</button>
    
	<hr>
    
    <h2 style="color:white">Create Room</h2>
    <label>Private
    	<input type="radio">
    </label>
    <br>
    <label>Public
    	<input type="radio">
    </label>
    <br>
    <input type="submit" name="Create">

</div>



</body>
</html>
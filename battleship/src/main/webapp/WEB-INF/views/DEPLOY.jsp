<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	font-family: Verdana;
	color: white;
    background-color: #595959;
    font-size: 25px;
}

.split {
	height: 100%;
	width: 50%;
	position: absolute;
	z-index: 1;
	top: 0;
	overflow-x: hidden;
}

.left {
	left: 0;
}

.right {
	right: 0;
}

.centered {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
}

.vline {
	position: absolute;
	top: 10%;
	left: 50%;
	border-right: 9px solid grey;
	height: 80%;
}

img {
	width: 200px;
}

</style>

<title>Battleship! - DEPLOY</title>
</head>
<body>
<a href="/dock">Dock</a>

<div class="split left">
  <div class="centered">
    <h2>Search</h2>
    <h2>for</h2>
    <h2>Battle</h2>
    <form method="POST">
	    <input type="text" value="Username" name="username"/>
	    <input type="submit" value="Scour"/>
    </form>
  </div>
</div>

<div class="vline"></div>

<div class="split right">
  <div class="centered">
    <h2>Create</h2>
    <div>
    	<img src="./shipWheel.png" id="createBattle">
    </div>
    <h2>Battle</h2>
  </div>
</div>
<script src="/scripts/DEPLOY.js"></script>
</body>
</html>
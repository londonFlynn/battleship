<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	boolean loggedIn = (boolean)request.getAttribute("loggedIn");
%>
<!DOCTYPE html>
<html>
<head>
<style>
body {background-image: url("./battleship.jpg");
	background-size: cover;}
h1 {font-size: 150px;
	 padding: 15px;
	 margin: 15px;
	 text-align: center;}
.header {background-color: #000;
	display: inline-block;
	width: 100%;
	height: 100px;}
.gameplaySection {background-color: #222;
	color: #fff;
	width: 400px;
	height: 230px;}
#login {float: right;
	background-color: #fff;
	margin: 25px;
	border: 10px solid white;}
#homelink {float: left;
	font-size: 38px;
	margin: 25px;}
#dockPreview{position: absolute;
	bottom: 0;
	right: 0;
	margin: 35px;}
#multiplayerPreview{position: absolute;
	bottom: 0;
	left: 0;
	margin: 35px;}
#gametitle {height: 500px}
</style>
<meta charset="ISO-8859-1">
<title>Battleship!</title>
</head>
<body>
	<header class='header'><!--display: block-->
      <a id='homelink' href='/'>Battleship</a>
      <a id='login'
     <%if(!loggedIn) { %>
      href='/login'> Login <%} 
      else { %>
      href='/logout'> Log out <%} %></a>
    </header>
    <div id='gametitle'>
    	<h1>Battleship</h1>
    </div>
    <footer id='preview'>
      <div id='multiplayerPreview' class='gameplaySection'>
          <h2 style='text-align: center'>Two Player Gameplay</h2>
          <ul>
              <li><p>Connect with random players</p></li>
              <li><p>Play against friends</p></li>
              <li><p>Open your own games</p></li>
          </ul>
      </div>
      <div id='dockPreview' class='gameplaySection'>
          <h2 style='text-align: center'>Visit the Dock</h2>
          <ul>
              <li><p>See your battle stats</p></li>
              <li><p>Pick your favorite ship</p></li>
              <li><p>Decide your next destination</p></li>
          </ul>
      </div>
    </footer>
</body>
</html>
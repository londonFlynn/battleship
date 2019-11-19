<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	boolean loggedIn = (boolean)request.getAttribute("loggedIn");
%>
<!DOCTYPE html>
<html>
<head>
<link href='css/Home.css' rel='stylesheet' />
<meta charset="ISO-8859-1">
<title>Battleship!</title>
</head>
<body>
	<header class='header'><!--display: block-->
      <a id='homelink' href='/'>Battleship</a>
      <a id='login' href='/login'>
     <%if(!loggedIn) { %>
      Login <%} 
      else { %>
      Log out <%} %></a>
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
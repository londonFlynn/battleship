<%@page import="pro0.battleship.models.Game"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 	List<Game> games = (List<Game>)request.getAttribute("games");
	%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	overflow-y: auto;
    background-color: #2d2d2d;
    color: white;
}

.savedgame {
  border: 2px solid #ababab;
  background-color: #a12b72;
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  color: black;
}

.savedgame span {
  font-size: 40px;
  margin-right: 15px;
}

</style>
<title>Battleship! - Saved Games</title>
</head>
<body>
<h1>Saved Games</h1>

<div class="savedgame">
  <p><span>You vs uName</span></p>
  <p>Images of board status</p>
</div>

</body>
</html>
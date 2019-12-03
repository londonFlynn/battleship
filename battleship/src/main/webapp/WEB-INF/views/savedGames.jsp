<%@page import="pro0.battleship.models.Game"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String) request.getAttribute("username");
	List<Game> games = (List<Game>)request.getAttribute("games");
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

#dock {
	float: right;
	background-color: #ff751a;
	border: 10px solid #ff751a;
}

</style>
<title>Battleship! - Saved Games</title>
</head>
<body>
<a id='dock' href='/dock'>Dock</a>
<h1>Saved Games</h1>

	<% if (games != null) { 
		for (Game game : games) {
			//If opponent is null do not print
			if(game.getOtherUser() != null) {
	%>
<div class="savedgame">
  <p>
  	<span>
  		<a href=<%="/savedgames/" + game.getId() %>>You vs <%=game.getOtherUser(username).getUsername() %></a>
  	</span>
  </p>
  <p></p>
</div>
  <%		}
		}
	} %>

</body>
</html>
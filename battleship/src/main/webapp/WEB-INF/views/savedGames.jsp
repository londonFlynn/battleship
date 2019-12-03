<%@page import="pro0.battleship.models.Game"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String) request.getAttribute("username");
	List<Game> allGames = (List<Game>) request.getAttribute("allGames");
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
<a id='dock' href='/battleship/dock'>Dock</a>
<h1>Saved Games</h1>

	<% if (allGames != null) { 
		for (Game game : allGames) {
			//If opponent is null do not print
			if(game.getOtherUser(username) != null) {
	%>
<div class="savedgame">
  <p>
  	<span>
  		<a href=<%="/battleship/savedgames/" + game.getId() %>>You vs <%=game.getOtherUser(username).getUsername() %></a>
  	</span>
  </p>
  <p></p>
</div>
  <%		}
		}
	} %>

</body>
</html>
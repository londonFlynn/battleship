<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String)request.getAttribute("username");
	int won = (Integer)request.getAttribute("gamesWon");
	int lost = (Integer)request.getAttribute("gamesLost");
	String imgUrl = (String)request.getAttribute("imgUrl");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>

</style>
<title>Battleship! - Dock </title>
</head>
<body>

<div class="profileinfo">
	<img src="<%=imgUrl%>">
	<p><%=username%></p>
    <p>Stats</p>
    <p>Rank</p>
    <p>Average: <%=(won)/(won+lost) %></p>
    <p>Wins: <%=won%></p>
    <p>Loses: <%=lost%></p>
</div>

<div class="signpost left">
	<a href="/savedgames">Saved Games</a>
    <a href="/availablegames">Available Games</a>
</div>

<div class="signpost right">
	<a href="/home">Home</a>
    <a href="/logout">Logout</a>
</div>


</body>
</html>
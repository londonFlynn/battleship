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
body{
	background: url("./pier.jpg") no-repeat center center fixed;
	background-size: cover;
}

.profileinfo img {
	width: 180px;
}

.profileinfo {
	float: right;
	background-color: #37EBB1;
	text-align: center;
	border: 10px solid #61b8a9;
}

.signpost img {
	height: 50%;
	position: absolute;
	left: 0px;
	z-index: -1;
}

.right .top {
	position: absolute;
	top: 6%;
	left: 13%;
	z-index: 999;
	font-size: 1.2vw;
}

.right .bottom {
	position: absolute;
	top: 24%;
	left: 13%;
	z-index: 999;
	font-size: 1.2vw;
}

.left .top {
	position: absolute;
	top: 15%;
	left: 6%;
	z-index: 999;
	font-size: 1.2vw;
}

.left .bottom {
	position: absolute;
	top: 32%;
	left: 6%;
	z-index: 999;
	font-size: 1.2vw;
}
</style>
<title>Battleship! - Dock </title>
</head>
<body>

<div class="profileinfo">
	<img src="<%=imgUrl%>">
	<p><%=username%></p>
    <p style="font-size: 25px;">Stats</p>
    <p>Rank</p>
    <p>Average: <%=(won)/(won+lost) * 100 %>%</p>
    <p>Wins: <%=won%></p>
    <p>Loses: <%=lost%></p>
</div>
<div class="signpost">
	<img src="./signpost4.png">
	
	<div class="left">
		<p class="top"><a href="/savedgames">Saved Games</a></p>
	    <p class="bottom"><a href="/availablegames">Available Games</a></p>
	</div>
	
	<div class="right">
		<p class="top"><a href="/home">Home</a></p>
	    <p class="bottom"><a href="/logout">Logout</a></p>
	</div>
</div>

</body>
</html>
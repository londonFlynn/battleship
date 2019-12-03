<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String)request.getAttribute("username");
	int won = (int) request.getAttribute("gamesWon");
	int lost = (int) request.getAttribute("gamesLost");
	int total = won + lost;
	String imgUrl = (String)request.getAttribute("imgUrl");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body{
	background: url("/battleship/images/pier.jpg") no-repeat center center fixed;
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
	left: 26%;
	z-index: 999;
	font-size: 2.5vw;
}

.right .bottom {
	position: absolute;
	top: 23.8%;
	left: 25.8%;
	z-index: 999;
	font-size: 2.5vw;
}

.left .top {
	position: absolute;
	top: 14.5%;
	left: 9%;
	z-index: 999;
	font-size: 2.5vw;
}

.left .bottom {
	position: absolute;
	top: 32.3%;
	left: 11%;
	z-index: 999;
	font-size: 2.5vw;
}

@media only screen and (color) and (min-width: 1300px) {
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
		left: 4.8%;
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
}

a {
	color: #8b6914;
	text-decoration: none;
	text-shadow: 1px 1px 1px black;
}

a:hover {
  color: #452b0a;
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
    <p>Average: <% if(total > 0) out.print(won/total); else out.print("N/A"); %></p>
    <p>Wins: <%=won%></p>
    <p>Loses: <%=lost%></p>
</div>
<div class="signpost">
	<img src="/battleship/images/signpost4.png">
	
	<div class="left">
		<p class="top"><a href="/battleship/savedgames">Saved Games</a></p>
	    <p class="bottom"><a href="/battleship/DEPLOY">DEPLOY</a></p>
	</div>
	
	<div class="right">
		<p class="top"><a href="/battleship/">Home</a></p>
	    <p class="bottom"><a href="/battleship/logout">Logout</a></p>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String) request.getAttribute("username");
	int gameId = (int) request.getAttribute("gameId");
%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	height: 100%;
	background-image: url(/battleship/images/loading.gif);
	background-size: cover;
	background-repeat: no-repeat;
}

h1 {
	color: white;
	text-align: center;
}

</style>
<meta charset="ISO-8859-1">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<title>Battleship! - DEPLOYING</title>
</head>
<body>
<div id="username" style="display: none"><%=username%></div>
<div id="gameId" style="display: none"><%=gameId%></div>
<div>
	<h1>YOU ARE AT THE MERCY OF THE HIGH SEAS!!!</h1>
</div>

<script src="/battleship/js/DEPLOYING.js"></script>
</body>
</html>
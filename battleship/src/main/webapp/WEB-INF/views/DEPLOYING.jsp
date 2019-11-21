<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String username = (String) request.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<style>

</style>
<meta charset="ISO-8859-1">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<title>Battleship! - DEPLOYING</title>
</head>
<body>
<div id="username" style="display: none"><%=username%></div>


<script src="/scripts/DEPLOYING.js"></script>
</body>
</html>
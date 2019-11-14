<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%char let = 'a'; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>

.tile {
	width: 25px;
	height: 25px;
	background-color: #000;
	color: #fff;
	padding: 1px;
	margin: 5px;
}
.ships {
	height: 35px;
	width: 35px;
	background-color: #fff;
	border-radius: 50%;
	border: 5px solid #1B3E40;
	display: inline-block;
}
.shipsAvaliable {
	width: 300px;
	height: 50px;
	background-color: #18C3CC;
	border-radius: 5%;
}
#currentPlayerTurn {
	width: 400px;
	height: 75px;
	background-color: #1B3E40;
	border: 5px solid #fff;
	border-radius: 5%;
}
</style>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/scripts/battle.js"></script>
<title>Battle!</title>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
    <table id='opponentBoard'>
	<tbody><tr>
		<th class='tile'></th>
		<td class='tile'>A</td>
		<td class='tile'>B</td>
		<td class='tile'>C</td>
		<td class='tile'>D</td>
		<td class='tile'>E</td>
        <td class='tile'>F</td>
		<td class='tile'>G</td>
		<td class='tile'>H</td>
		<td class='tile'>I</td>
		<td class='tile'>J</td>
	</tr>
	<% for(int i=1;i<11;i++) { %>
	<tr class='tile'>
		<th class='tile'><%=i%></th>
			<%for(int j=0;j<10;j++){ %>
			<td id='<%=""+let+i%>' class='tile'></td>
			<%let++;} %>
	</tr>
	<% let='a';} %>
</tbody>
</table>
<table id='playerBoard'>
	<tbody><tr>
		<th class='tile'></th>
		<td class='tile'>A</td>
		<td class='tile'>B</td>
		<td class='tile'>C</td>
		<td class='tile'>D</td>
		<td class='tile'>E</td>
        <td class='tile'>F</td>
		<td class='tile'>G</td>
		<td class='tile'>H</td>
		<td class='tile'>I</td>
		<td class='tile'>J</td>
	</tr>
	<% for(int i=1;i<11;i++) { %>
	<tr class='tile'>
		<th class='tile'><%=i%></th>
			<%for(int j=0;j<10;j++){ %>
			<td id='<%=""+let+i%>' class='tile'></td>
			<%let++;} %>
	</tr>
	<% let='a';} %>
</tbody>
</table>
<div id='currentPlayerTurn'>
	<h1>Turn: username</h1>
</div>
<div id='opponentShips' class='shipsAvaliable'>
	<%for(int i=0;i<4;i++) {%>
		<div class='.ships'></div>
	<%} %>
</div>
<div id='playerShips' class='shipsAvaliable'>
	<%for(int i=0;i<4;i++) {%>
		<div class='.ships'></div>
	<%} %>
</div>
</body>
</html>















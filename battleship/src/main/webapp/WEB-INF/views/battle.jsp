<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%char let = 'a'; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
.otiles {
	width: 55px;
	height: 55px;
	background-image: url(./sync.gif);
	color: #fff;
	padding: 1px;
	margin: 5px;
	opacity: 0.90;
}
.otiles:hover{
	opacity: 0.5;
}
.tile {
	width: 45px;
	height: 45px;
	background-image: url(./still.jpg);
	color: #fff;
	padding: 1px;
	margin: 5px;
	opacity: 0.90;
}
.titleTile {
	width: 45px;
	height: 45px;
	padding: 1px;
	margin: 5px;
}
.ships {
	height: 55px;
	width: 55px;
	background-color: #abc;
	border-radius: 50%;
	border: 5px solid #1B3E40;
	display: inline-block;
	margin: 5px;
}
.shipsAvaliable {
	width: 400px;
	height: 85px;
	background-color: #18C3CC;
	margin: 25px 0px;
}
#opponentBoard{
	margin: 15px;
}
#playerBoard{
	margin: 15px;
}
#currentPlayerTurn {
	width: 400px;
	height: 75px;
	background-color: #18C3CC;
	border: 5px solid #fff;
}
</style>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/scripts/battle.js"></script>
<script type="application/javascript">

</script>
<title>Battle!</title>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
    <table id='opponentBoard'  class='board'>
	<tbody><tr>
		<th class='titleTile'></th>
		<td class='titleTile'>A</td>
		<td class='titleTile'>B</td>
		<td class='titleTile'>C</td>
		<td class='titleTile'>D</td>
		<td class='titleTile'>E</td>
        <td class='titleTile'>F</td>
		<td class='titleTile'>G</td>
		<td class='titleTile'>H</td>
		<td class='titleTile'>I</td>
		<td class='titleTile'>J</td>
	</tr>
	<% for(int i=1;i<11;i++) { %>
	<tr>
		<th><%=i%></th>
			<%for(int j=0;j<10;j++){ %>
			<td id='<%=""+let+i%>' class='otiles'></td>
			<%let++;} %>
	</tr>
	<% let='a';} %>
	</tbody>
	</table>
	<table id='playerBoard' class='board'>
		<tbody><tr>
			<th class='titleTile'></th>
			<td class='titleTile'>A</td>
			<td class='titleTile'>B</td>
			<td class='titleTile'>C</td>
			<td class='titleTile'>D</td>
			<td class='titleTile'>E</td>
	        <td class='titleTile'>F</td>
			<td class='titleTile'>G</td>
			<td class='titleTile'>H</td>
			<td class='titleTile'>I</td>
			<td class='titleTile'>J</td>
		</tr>
		<% for(int i=1;i<11;i++) { %>
		<tr>
			<th><%=i%></th>
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
		<div id='opponentAircraft' class='ships'></div>
		<div id='opponentBattleship' class='ships'></div>
		<div id='opponentCruser' class='ships'></div>
		<div id='opponentDestroyer' class='ships'></div>
		<div id='opponentSubmarine' class='ships'></div>
	</div>
	<div id='playerShips' class='shipsAvaliable'>
		<div id='playerAircraft' class='ships'></div>
		<div id='playerBattleship' class='ships'></div>
		<div id='playerCruser' class='ships'></div>
		<div id='playerDestroyer' class='ships'></div>
		<div id='playerSubmarine' class='ships'></div>
	</div>
</body>
</html>















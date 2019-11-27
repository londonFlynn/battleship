<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%char let = 'a'; %>

<% int gameID = (int) request.getAttribute("gameID"); %>
<% String username = (String) request.getAttribute("username"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href='/css/battle.css' rel='stylesheet' type="text/css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<title>Battle!</title>
</head>
<body>
	<div id='page'>
		<div id='game' style="display: inline-block">
			<div id="battleId" style="display: none"><%=gameID%></div>
			<div id="username" style="display: none"><%=username%></div>
			<noscript>
				<h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
			    enabled. Please enable
			    Javascript and reload this page!</h2>
			</noscript>
			<h2>Opponent's Board</h2>
			<table id="opponentBoard" class="board">
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
							<td id='<%=""+let+""+i%>' class='otiles'></td>
							<%let++;}%>
				</tr>
				<% let='a';} %>
				</tbody>
			</table>
			<h2>Player's Board</h2>
			<table id='playerBoard' class='board'>
				<tbody>
					<tr>
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
		</div>
		<div id='shipPlacement'>
			<h2>Place Ships</h2>
			<div>
				<div id='start'>start</div>
				<div id='startPlacingShips'>
					<h3 class='placement' id='placingAircraft'>Aircraft 5</h3>
					<h3 class='placement' id='placingBattleship'>Battleship 4</h3>
					<h3 class='placement' id='placingCruiser'>Cruiser 3</h3>
					<h3 class='placement' id='placingDestroyer'>Destroyer 2</h3>
					<h3 class='placement' id='placingSubmarine'>Submarine 3</h3>				
				</div>
				<h2>Orientation</h2>
				<div>
					<h3 class='orient' id='horizontal'>Horizontal</h3>
					<h3 class='orient' id='vertical'>Vertical</h3>
				</div>
			</div>
		</div>
		<div id='gameInfo' style="display: inline-block">
			<div>
				<h1 id='currentPlayerTurn'>Turn: <%=username %></h1>
			</div>
			<div id='opponentShips' class='shipsAvaliable'>
				<p>Opponent's Ships</p>
				<div id='oaircraft'class='ships'><span class="tooltiptext">Aircraft</span></div>
				<div id='obattleship'class='ships'><span class="tooltiptext">Battleship</span></div>
				<div id='ocruiser'class='ships'><span class="tooltiptext">Cruiser</span></div>
				<div id='odestroyer'class='ships'><span class="tooltiptext">Destroyer</span></div>
				<div id='osubmarine'class='ships'><span class="tooltiptext">Submarine</span></div>
			</div>
			<div id='playerShips' class='shipsAvaliable'>
				<p>Player's Ships</p>
				<div id='paircraft' class='ships'><span class="tooltiptext">Aircraft</span></div>
				<div id='pbattleship' class='ships'><span class="tooltiptext">Battleship</span></div>
				<div id='pcruiser' class='ships'><span class="tooltiptext">Cruiser</span></div>
				<div id='pdestroyer' class='ships'><span class="tooltiptext">Destroyer</span></div>
				<div id='psubmarine' class='ships'><span class="tooltiptext">Submarine</span></div>
			</div>
		</div>
		<div id='winner'>
			<h1>You won the battle!</h1>
			<h2>Player <%=username %> is the winner</h2>
		</div>
		<div id='loser'>
			<h1>You lost the battle...</h1>
			<h2>Player <%=username %> is the winner</h2>
		</div>
	</div>
	<script src='/js/battle.js'></script>
</body>
</html>















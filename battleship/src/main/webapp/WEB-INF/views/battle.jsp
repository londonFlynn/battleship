<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>

#tile {
	width: 15px;
	height: 15px;
}
.ships {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
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
    <table>
	<tbody><tr>
		<th></th>
		<th>1</th>
		<th>2</th>
		<th>3</th>
		<th>4</th>
		<th>5</th>
        <th>6</th>
		<th>7</th>
		<th>8</th>
		<th>9</th>
		<th>10</th>
	</tr>
		<tr>
			<th><%=1+1%></th>
	<% char let = 'a';
		for(int i=1;i<11;i++) { %>
			<td id='<%=""+let+i%>' class='tile'></td>
		</tr>
	<% let++;
		} %>
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















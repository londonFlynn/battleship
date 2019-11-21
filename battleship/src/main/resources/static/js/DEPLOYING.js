
window.onload() = function () {
	username = document.getElementById("username").innerText;
	gameId = document.getElementById("gameId").innerText;
};

function connect(username) {
	 var socket = new SockJS('/gs-battleship-websocket'); //Makes it use sockets
	 stompClient = Stomp.over(socket); //Stomp to find queues for the sockets
	 stompClient.connect({}, function (frame) {
		 setConnected(true);
		 stompClient.subscribe('/tojs/DEPLOY/' + username, function (oppoUsername) {
			 if(oppoUsername) {
				 window.location.href = "/battle/" + gameId;
			 }
		 });
	 });
}
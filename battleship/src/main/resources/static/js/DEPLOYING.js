
window.onload = function () {
	username = document.getElementById("username").innerText;
	gameId = document.getElementById("gameId").innerText;
	connect(username);
};

function connect(username) {
	 var socket = new SockJS('/gs-battleship-websocket'); //Makes it use sockets
	 stompClient = Stomp.over(socket); //Stomp to find queues for the sockets
	 stompClient.connect({}, function (frame) {
		 console.log("connected: " + frame);
		 stompClient.subscribe('/tojs/DEPLOY/' + username, function (myUsername) {
			 if(JSON.parse(myUsername.body)) {
				 console.log("Received something.");
				 window.location.href = "/battle/" + gameId;
			 }
		 });
	 });
}
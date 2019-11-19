var playerShips {
	aircraft = getElementById('playerAircraft'),
	battleship = getElementById('playerBattleship'),
	cruiser = getElementById('playerCruser'),
	destroyer = getElementById('playerDestroyer'),
	submarine = getElementById('playerSubmarine')
}
var opponentShips {
	aircraft = getElementById('opponentAircraft'),
	battleship = getElementById('opponentBattleship'),
	cruiser = getElementById('opponentCruser'),
	destroyer = getElementById('opponentDestroyer'),
	submarine = getElementById('opponentSubmarine')
}

var stompClient = null;

window.onload = function() {
    this.connect();
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-battleship-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/tojs/game', function (game) {
            showGame(game.body);
        });
        test();
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendGame(game) {
    console.log("sending game " + game);
    stompClient.send("/fromjs/game",{},JSON.stringify(game));
}

function showGame(game) {
    console.log("Recived Game "+game);
}

function test() {
    sendGame(Game);
}

var Game = {
    id: 1,
    boards: null,
    user: null,
    currentTurn: null,
    otherUser: null
}
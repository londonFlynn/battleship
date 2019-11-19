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
var gameId;

function setGameId(id) {
    gameId = id;
    this.connect(id);
}

window.onload = function() {
    this.connect();
};

function setConnected(connected) {
    if (!connected ) {
        //Notify user that they lost their connection
    }
}

function connect(id) {
    var socket = new SockJS('/gs-battleship-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/tojs/game/'+id, function (game) {
            showGame(JSON.parse(game.body));
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
    game.boards.forEach( function(board) {
        board.rows.forEach (function(row) {
            row.cells.forEach(function(cell){
                console.log(cell.targeted);
            });
        });
    });
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
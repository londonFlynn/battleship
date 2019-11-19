var playerShips = {
	aircraft = getElementById('playerAircraft'),
	battleship = getElementById('playerBattleship'),
	cruiser = getElementById('playerCruser'),
	destroyer = getElementById('playerDestroyer'),
	submarine = getElementById('playerSubmarine')
}
var opponentShips = {
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

function setConnected(connected) {
    if (!connected ) {
        //Notify user that they lost their connection
    }
}

function sendShipPlacementRequest(xPos, yPos, direction, shipType) {
    stompClient.send("/fromjs/placeShip/"+gameId,{}, {"xPos":xPos, "yPos":yPos, "direction":direction, "shipType": shipType});
}

function sendTargetCellRequest(xPos, yPos) {
    tompClient.send("/fromjs/targetCell/"+gameId,{}, {"xPos":xPos, "yPos":yPos});
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
        setupGameFromServer();
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
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

function setupGameFromServer() {
    stompClient.send("/fromjs/gameState/"+gameId,{});
}

var Game = {
    id: 1,
    boards: null,
    user: null,
    currentTurn: null,
    otherUser: null
}
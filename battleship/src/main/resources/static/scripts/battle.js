var currentPlayer = document.getElementById('currentPlayerTurn');
var oaircraft = document.getElementById('oaircraft');
var obattleship = document.getElementById('obattleship');
var ocruiser = document.getElementById('ocruiser');
var odestroyer = document.getElementById('odestroyer');
var osubmarine = document.getElementById('osubmarine');
var paircraft = document.getElementById('paircraft');
var pbattleship = document.getElementById('pbattleship');
var pcruiser = document.getElementById('pcruiser');
var pdestroyer = document.getElementById('pdestroyer');
var psubmarine = document.getElementById('psubmarine');
var opponentBoard = document.getElementById('opponentBoard');
console.log(opponentBoard);

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
    stompClient.send("/fromjs/targetCell/"+gameId,{}, {"xPos":xPos, "yPos":yPos});
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

function determineClick() {
	console.log('determine click');
	console.log(event);
}

opponentBoard.addEventListener("click", event => {
	var index = event.target.id.substring(0, 1) + "," + event.target.id.substring(1);
	index.split(",");
	var xPos = index[0].charCodeAt(0) - 49;
	
	var yPos = event.target.id.substring(1);
	var y = parseInt(yPos, 10)
	y = y-1;
	
	console.log("X: " + String.fromCharCode(xPos) +"; Y: "+ y);
	
	sendTargetCellRequest(String.fromCharCode(xPos), y);
});











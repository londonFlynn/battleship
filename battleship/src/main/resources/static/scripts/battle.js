var currentPlayer = document.getElementById('currentPlayerTurn');
var opponentShipsLife = [
		oaircraft = document.getElementById('oaircraft'),
		obattleship = document.getElementById('obattleship'),
		ocruiser = document.getElementById('ocruiser'),
		odestroyer = document.getElementById('odestroyer'),
		osubmarine = document.getElementById('osubmarine')
]
var userShipsLife = [
		paircraft = document.getElementById('paircraft'),
		pbattleship = document.getElementById('pbattleship'),
		pcruiser = document.getElementById('pcruiser'),
		pdestroyer = document.getElementById('pdestroyer'),
		psubmarine = document.getElementById('psubmarine')
]
var wonGame = document.getElementById('winner');
var lostGame = document.getElementById('loser');
var game = document.getElementById('game');
var opponentBoard = document.getElementById('opponentBoard');
var playerBoard = document.getElementById('playerBoard');

console.log(opponentBoard);
wonGame.style.display = "none";
lostGame.style.display = "none";

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


function showUsersBoard(board) {
    //TODO populate the board

    board.rows.forEach (function(row) {
        row.cells.forEach(function(cell){
            console.log(cell.targeted);
            console.log(cell.hit);
        });
    });
}

function showOpponentBoard(board) {
    //TODO polulate the board

    board.rows.forEach (function(row) {
        row.cells.forEach(function(cell){
            console.log(cell.targeted);
            console.log(cell.hit);
        });
    });
}

function setConnected(connected) {
    if (!connected ) {
        //TODO Notify user that they lost their connection
    }
}
function notifyInvalidAttack() {
    //TODO Notify user that they cannot attack that space
}
function userGotHit(position) {
	position.xPos = position.xPos + "";
	var initialAscii = position.xPos.charCodeAt(0) + 49;
	var x = String.fromCharCode(initialAscii);
	var y = position.yPos + 1;
	
	var targetId = x + "" + y;

	for (var i = 0, row; row = playerBoard.rows[i]; i++) {
	   for (var j = 0, col; col = row.cells[j]; j++) {
		   if(col.id == targetId) {
			   col.style.backgroundImage="url('/images/hit.gif'";
		   }
	   }  
	}
}
function userWasMissed(position) {
	position.xPos = position.xPos + "";
	var initialAscii = position.xPos.charCodeAt(0) + 49;
	var x = String.fromCharCode(initialAscii);
	var y = position.yPos + 1;
	
	var targetId = x + "" + y;

	for (var i = 0, row; row = playerBoard.rows[i]; i++) {
	   for (var j = 0, col; col = row.cells[j]; j++) {
		   if(col.id == targetId) {
			   col.style.backgroundImage="url('/images/miss.gif'";
		   }
	   }  
	}
}
function userMissed(position) {
	position.xPos = position.xPos + "";
	var initialAscii = position.xPos.charCodeAt(0) + 49;
	var x = String.fromCharCode(initialAscii);
	var y = position.yPos + 1;
	
	var targetId = x + "" + y;

	for (var i = 0, row; row = opponentBoard.rows[i]; i++) {
	   for (var j = 0, col; col = row.cells[j]; j++) {
		   if(col.id == targetId) {
			   col.style.backgroundImage="url('/images/miss.gif'";
		   }
	   }  
	}
}
function userHit(position) {
	position.xPos = position.xPos + "";
	var initialAscii = position.xPos.charCodeAt(0) + 49;
	var x = String.fromCharCode(initialAscii);
	var y = position.yPos + 1;
	
	var targetId = x + "" + y;

	for (var i = 0, row; row = opponentBoard.rows[i]; i++) {
	   for (var j = 0, col; col = row.cells[j]; j++) {
		   if(col.id == targetId) {
			   col.style.backgroundImage="url('/images/hit.gif'";
		   }
	   }  
	}	
}
function usersShipWasSunk(shipType) {
	var count = 0;
	for (const property in ShipType) {
		if(shipType == property){
			userShipsLife[count].style.backgroundColor = "#E84948";
		}
		count++;
	}
}
function userSunkAShip(shipType) {
	var count = 0;
	for (const property in ShipType) {
		if(shipType == property){
			opponentShipsLife[count].style.backgroundColor = "#E84948";
		}
		count++;
	}
}

function userLost() {
    loser.style.display = "block";
    game.style.display = "none";
}
function userWon() {
	winner.style.display = "block";
    game.style.display = "none";
}
function itsYourTurn() {
    //TODO unlock board
}
function itsTheOpponentsTurn() {
    //TODO lock board
}
function gameHasStarted() {
    //Notify User
}

function displayShipInPositions(positions) {
    positions.forEach(function (position) {
        //show a ship in that position
    });
}

function sendShipPlacementRequest(position, direction, shipType) {
    var shipPlacementRequest = {
        "position": position,
        "direction": direction,
        "shipType": shipType
    }
    var url = "/shipPlacement";
    sendRequest(shipPlacementRequest, url, "POST", reciveShipPlacementResponse);   
}

function sendTargetCellRequest(position) {
    console.log(position);
    stompClient.send("/fromjs/targetCell/"+gameId,{}, JSON.stringify({"position":position}));
}

class BoardPosition {
    constructor(xPos, yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}

const Direction = {
    NORTH: 'NORTH',
    SOUTH: 'SOUTH',
    EAST: 'EAST',
    WEST: 'WEST'
}

var ShipType = {
    AIRCRAFT_CARRIER: 'AIRCRAFT_CARRIER',
    BATTLESHIP: 'BATTLESHIP',
    SUBMARINE: 'SUBMARINE',
    CRUISER: 'CRUISER',
    DESTROYER: 'DESTROYER'
}

//You shouldnt need to touch anything below this line

var stompClient = null;
var gameId;
var username;

window.onload = function() {
    username = document.getElementById("username").innerText;
	setGameId(document.getElementById("battleId").innerText);
};

var setGameId = function setGameId(id) {
    gameId = id;
    this.connect(id);
};

async function sendRequest(object, requestPath, requestType, result) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open(requestType, requestPath, true);
    xmlHttp.setRequestHeader('Content-type', 'application/json');
    xmlHttp.onreadystatechange = function() {
        if(this.readyState === XMLHttpRequest.DONE) {
            result(JSON.parse(this.responseText));
        }
    };
    if (object) {
        xmlHttp.send(JSON.stringify(object));
    } else {
        xmlHttp.send();
    }
}

function connect(id) {
    var socket = new SockJS('/gs-battleship-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/tojs/attackResult/'+id, function (attackResult) {
            reciveAttackResult(attackResult);
        });
        stompClient.subscribe('/tojs/sunkShip/'+id, function (shipSunkNotification) {
            reciveShipSunkNotificaiton(shipSunkNotification);
        });
        stompClient.subscribe('/tojs/gameOver/'+id, function (gameOverNotification) {
            reciveGameOverNotification(gameOverNotification);
        });
        stompClient.subscribe('/tojs/turnChange/'+id, function (turnChangeNotification) {
            reciveTurnChangeNotification(turnChangeNotification);
        });
        stompClient.subscribe('/tojs/gameStarted/'+id, function (gameStartedNotification) {
            reciveGameStartedNotification(gameStartedNotification);
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

function reciveShipPlacementResponse(shipPlacementResponse) {
    if (shipPlacementResponse.success) {
        displayShipInPositions(shipPlacementResponse.positions);
    }
}

function reciveAttackResult(attackResult) {
    if (attackResult.invalid) {
        if (attackResult.playerUsername == username) {
            notifyInvalidAttack();
        }
    } else {
        if (attackResult.playerUsername == username) {
            if (attackResult.hit) {
                userGotHit(attackResult.position);
            } else {
                userWasMissed(attackResult.position);
            }
        } else {
            if (attackResult.hit) {
                userHit(attackResult.position);
            } else {
                userMissed(attackResult.position);
            }
        }
    }
}
function reciveShipSunkNotificaiton(shipSunkNotification) {
    if (shipSunkNotification.playerUsername == username) {
        usersShipWasSunk(shipSunkNotification.shipType);
    } else {
        userSunkAShip(shipSunkNotification.shipType);
    }
}
function reciveGameOverNotification(gameOverNotification) {
    if (gameOverNotification.winner == username) {
        userWon();
    } else {
        userLost();
    }
    // disconnect();
}
function reciveTurnChangeNotification(turnChangeNotification) {
    if (turnChangeNotification.playerUsername == username) {
        itsYourTurn();
    } else {
        itsTheOpponentsTurn();
    }
}
function reciveGameStartedNotification(gameStartedNotification) {
    console.log(gameStartedNotification);
    if (gameStartedNotification.started) {
        gameHasStarted();
        if (gameStartedNotification.turn.playerUsername == username) {
            itsYourTurn();
        } else {
            itsTheOpponentsTurn();
        }
    }
}

function setupGameFromServer() {
    setupYourBoardFromServer();
    // setupOpponentBoardFromServer();
    setupShipsFromServer();
    setupDestroyedShipsFromServer();
    setupGameHasStartedFromServer();
}

async function setupYourBoardFromServer() {
    var board = {rows: new Array()};
    for(var i = 0; i < 10; i++) {
        await setupBoardRowFromServer(board, i, false);
    }
    showUsersBoard(board);
}

function setupShipsFromServer() {
    sendRequest(null, "/gamestate/ships/"+gameId, "GET", function(shipPlacementResponses) {
        console.log(shipPlacementResponses);
        shipPlacementResponses.forEach(function(shipPlacementResponse) {
            displayShipInPositions(shipPlacementResponse.positions);
        });
    });
}

function setupDestroyedShipsFromServer() {
    sendRequest(null, "/gamestate/destroyed/"+gameId, "GET", function(shipSunkNotifications) {
        console.log(shipSunkNotifications);
        shipSunkNotifications.forEach(reciveShipSunkNotificaiton);
    });
}


async function setupBoardRowFromServer(board, row, opponent) {
    await sendRequest(null, "/gamestate/row/"+gameId+"/"+row+"/"+opponent, "GET", function(boardRow) {
        console.log(boardRow);
        board.rows.push(boardRow);
    });
}

function setupGameHasStartedFromServer() {
    sendRequest(null, "/gamestate/started/"+gameId, "GET", reciveGameStartedNotification);
}

function setupOpponentBoardFromServer() {
    var board = {rows: new Array()};
    for(var i = 0; i < 10; i++) {
        setupBoardRowFromServer(board, i, true);
    }
    showOpponentBoard(board);
}
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
var opponentBoard = document.getElementById('opponentBoard');
console.log(opponentBoard);

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
    //TODO display the hit
}
function userWasMissed(position) {
    //TODO display the miss
}
function userMissed(position) {
    //TODO display the miss
}
function userHit(position) {
    //TODO display the hit
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
    //TODO display the saddness
}
function userWon() {
    //TODO display the success
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
    stompClient.send("/fromjs/targetCell/"+gameId,{}, {"position":position});
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

function sendRequest(object, requestPath, requestType, result) {
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
    setupOpponentBoardFromServer();
    setupShipsFromServer();
    setupDestroyedShipsFromServer();
    setupGameHasStartedFromServer();
}

function setupYourBoardFromServer() {
    sendRequest(null, "/gamestate/myBoard/"+gameId, "GET", function(board) {

    });
    // setupBoardRowFromServer():
}

function setupShipsFromServer() {
    sendRequest(null, "/gamestate/ships/"+gameId, "GET", function(shipPlacementResponses) {

    });
}

function setupDestroyedShipsFromServer() {
    sendRequest(null, "/gamestate/destroyed/"+gameId, "GET", function(shipSunkNotifications) {

    });
}


function setupBoardRowFromServer(row, opponent) {
    sendRequest(null, "/gamestate/row/"+gameId+"/"+row+"/"+opponent, "GET", function(boardRow) {

    });
}

function setupGameHasStartedFromServer() {
    sendRequest(null, "/gamestate/started/"+gameId, "GET", reciveGameStartedNotification);
}

function setupOpponentBoardFromServer() {
    sendRequest(null, "/gamestate/opponentBoard/"+gameId, "GET", function(board) {

    });
    // setupBoardRowFromServer():
}
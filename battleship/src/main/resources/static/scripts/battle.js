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

console.log(opponentBoard);
wonGame.style.display = "none";
lostGame.style.display = "none";

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
	//get coordinate and relate it to user board
	//change background image to /hit.gif
}
function userWasMissed(position) {
    //TODO display the miss
	//get coordinate and relate it to user board
	//change background image to /miss.gif
}
function userMissed(position) {
    //TODO display the miss
	//get coordinate and relate it to opponent board
	//change background image to /miss.gif
}
function userHit(position) {
    //TODO display the hit
	opponentBoard
	position.xPos
	//get coordinate and relate it to opponent board
	//change background image to /hit.gif
	
	
	class BoardPosition {
	    constructor(xPos, yPos) {
	        this.xPos = xPos;
	        this.yPos = yPos;
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

function sendShipPlacementRequest(position, direction, shipType) {
    
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

function setupGameFromServer() {
    
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











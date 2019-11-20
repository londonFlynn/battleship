// var playerShips = {
// 	aircraft = getElementById('playerAircraft'),
// 	battleship = getElementById('playerBattleship'),
// 	cruiser = getElementById('playerCruser'),
// 	destroyer = getElementById('playerDestroyer'),
// 	submarine = getElementById('playerSubmarine')
// }
// var opponentShips = {
// 	aircraft = getElementById('opponentAircraft'),
// 	battleship = getElementById('opponentBattleship'),
// 	cruiser = getElementById('opponentCruser'),
// 	destroyer = getElementById('opponentDestroyer'),
// 	submarine = getElementById('opponentSubmarine')
// }


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
    //TODO display the saddness
}
function userSunkAShip(shipType) {
    //TODO display the success
}

function userLost() {
    //TODO display the saddness
}
function userWon() {
    //TODO display the success
}

function sendShipPlacementRequest(position, direction, shipType) {
    stompClient.send("/fromjs/placeShip/"+gameId,{}, {"position": position, "direction":direction, "shipType": shipType});
}

function sendTargetCellRequest(position) {
    tompClient.send("/fromjs/targetCell/"+gameId,{}, {"position":position});
}

class BoardPosition {
    constructor(xPos, yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}

const ShipType = {
    NORTH: 'NORTH',
    SOUTH: 'SOUTH',
    EAST: 'EAST',
    WEST: 'WEST'
}

const Direction = {
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

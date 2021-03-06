var currentPlayer = document.getElementById('currentPlayerTurn');
var opponentShipsLife = [
	oaircraft = document.getElementById('oaircraft'),
	obattleship = document.getElementById('obattleship'),
	osubmarine = document.getElementById('osubmarine'),
	ocruiser = document.getElementById('ocruiser'),
	odestroyer = document.getElementById('odestroyer')
]
var userShipsLife = [
	paircraft = document.getElementById('paircraft'),
	pbattleship = document.getElementById('pbattleship'),
	psubmarine = document.getElementById('psubmarine'),
	pcruiser = document.getElementById('pcruiser'),
	pdestroyer = document.getElementById('pdestroyer')
]
var placingShips = [
	placingAircraft = document.getElementById('placingAircraft'),
	placingBattleship = document.getElementById('placingBattleship'),
	placingCruiser = document.getElementById('placingCruiser'),
	placingDestroyer = document.getElementById('placingDestroyer'),
	placingSubmarine = document.getElementById('placingSubmarine')
]
var wonGame = document.getElementById('winner');
var lostGame = document.getElementById('loser');
var game = document.getElementById('game');
var opponentBoard = document.getElementById('opponentBoard');
var playerBoard = document.getElementById('playerBoard');
var start = document.getElementById('startGame');
var placer = document.getElementById('shipPlacement');
var gameInfo = document.getElementById('gameInfo');
var placementAmount = 5;
var rotated = false;
var currentShipType;

opponentBoard.style.display = "none";
wonGame.style.display = "none";
lostGame.style.display = "none";
gameInfo.style.display = "none";
//element.addEventListener("mousedown", handleMouseDown, true); after last ship placed

document.getElementById('start').addEventListener("click", event => {
	addEventListenersToPlacementNames()
	playerMouseEvents();
	placingShips.forEach(element => {
		element.addEventListener("mouseover", event => element.style.opacity = .5);
		element.addEventListener("mouseout", event => element.style.opacity = 1);
		element.style.cursor = "pointer";
		document.getElementById('start').style.display = "none";
	});
	currentShipType = ShipType.AIRCRAFT_CARRIER;
	placingAircraft.style.color = 'red';
});

function resetPlacementColor() {
	placingShips.forEach(element => {
		element.style.color = 'black';
	});
}

function addEventListenersToPlacementNames() {
	horizontalButton = document.getElementById('horizontal');
	verticalButton = document.getElementById('vertical');
	horizontalButton.style.color = 'red';
	placingAircraft.addEventListener("click", event => { resetPlacementColor(); placementAmount = 5; currentShipType = ShipType.AIRCRAFT_CARRIER; placingAircraft.style.color = 'red' });
	placingBattleship.addEventListener("click", event => { resetPlacementColor(); placementAmount = 4; currentShipType = ShipType.BATTLESHIP; placingBattleship.style.color = 'red' });
	placingCruiser.addEventListener("click", event => { resetPlacementColor(); placementAmount = 3; currentShipType = ShipType.CRUISER; placingCruiser.style.color = 'red' });
	placingDestroyer.addEventListener("click", event => { resetPlacementColor(); placementAmount = 2; currentShipType = ShipType.DESTROYER; placingDestroyer.style.color = 'red' });
	placingSubmarine.addEventListener("click", event => { resetPlacementColor(); placementAmount = 3; currentShipType = ShipType.SUBMARINE; placingSubmarine.style.color = 'red' });
	horizontalButton.addEventListener("click", event => { rotated = false; horizontalButton.style.color = 'red'; verticalButton.style.color = 'black'; });
	verticalButton.addEventListener("click", event => { rotated = true; verticalButton.style.color = 'red'; horizontalButton.style.color = 'black'; });
	horizontalButton.style.cursor = "pointer";
	verticalButton.style.cursor = "pointer";
}

function playerMouseEvents() {
	playerBoard.addEventListener("mouseover", event => {
		if (gameHasStarted) {
			var position = turnIntoBoardPosition(event);
			position.xPos = position.xPos + "";
			var initialAscii = position.xPos.charCodeAt(0) + 49;
			var x = String.fromCharCode(initialAscii);
			var y = position.yPos + 1;

			var targetId = x + "" + y;

			//iterate thru entire table
			for (let i = 0, row; row = playerBoard.rows[i]; i++) {
				for (let j = 0, col; col = row.cells[j]; j++) {
					//check if current cell(col) was the one that is hovered over
					if (col.id == targetId) {
						//iteration for size of ship
						if (col.style.backgroundImage != 'url("/images/placeShip.png")') {
							col.style.backgroundImage = 'url("/images/target.gif")';
						}
						var nextCell = null;
						for (let k = 1; k < placementAmount; k++) {
							nextCell = null;
							if (rotated && (i + placementAmount - 1) < 11) {
								nextCell = playerBoard.rows[i + k].cells[j];
							} else if (!rotated && (j + placementAmount - 1) < 11) {
								nextCell = row.cells[j + k];
							}
							if (nextCell) {
								if (nextCell.style.backgroundImage != 'url("/images/placeShip.png")') {
									nextCell.style.backgroundImage = 'url("/images/target.gif")'
								}
							}
						}
					}
				}
			}
		}
	});
	playerBoard.addEventListener("mouseout", event => {
		clearBoardTargeting();
	});
	playerBoard.addEventListener("click", event => {
		sendShipPlacementRequest(turnIntoBoardPosition(event), rotated ? Direction.SOUTH : Direction.EAST, currentShipType);
		clearBoardTargeting();
	});
}
function clearBoardTargeting() {
	for (let i = 0, row; row = playerBoard.rows[i]; i++) {
		for (let j = 0, col; col = row.cells[j]; j++) {
			if (col.style.backgroundImage == 'url("/images/target.gif")') {
				col.style.backgroundImage = 'url("/images/still.jpg")';
			}
		}
	}
}

function turnIntoBoardPosition(event) {
	var index = event.target.id.substring(0, 1) + "," + event.target.id.substring(1);
	index.split(",");
	var xPos = index[0].charCodeAt(0) - 49;
	var x = (parseInt(xPos, 10)) - 48;


	var yPos = event.target.id.substring(1);
	var y = parseInt(yPos, 10)
	y = y - 1;
	return new BoardPosition(x, y);
}

//start.addEventListener("click", event => {
//	
//	opponentBoard.style.display = "block";
//});

opponentBoard.addEventListener("click", event => {
	var index = event.target.id.substring(0, 1) + "," + event.target.id.substring(1);
	index.split(",");
	var xPos = index[0].charCodeAt(0) - 49;
	var x = (parseInt(xPos, 10)) - 48;


	var yPos = event.target.id.substring(1);
	var y = parseInt(yPos, 10)
	y = y - 1;

	console.log("X: " + x + "; Y: " + y);
	sendTargetCellRequest(new BoardPosition(x, y));
});

function setConnected(connected) {
	if (!connected) {
		//TODO Notify user that they lost their connection
	}
}
function notifyInvalidAttack() {

}
function userGotHit(position) {
	position.xPos = position.xPos + "";
	var initialAscii = position.xPos.charCodeAt(0) + 49;
	var x = String.fromCharCode(initialAscii);
	var y = position.yPos + 1;

	var targetId = x + "" + y;

	for (var i = 0, row; row = playerBoard.rows[i]; i++) {
		for (var j = 0, col; col = row.cells[j]; j++) {
			if (col.id == targetId) {
				col.style.backgroundImage = "url('/images/hit.gif'";
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
			if (col.id == targetId) {
				col.style.backgroundImage = "url('/images/miss.gif'";
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
			if (col.id == targetId) {
				col.style.backgroundImage = "url('/images/miss.gif'";
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
			if (col.id == targetId) {
				col.style.backgroundImage = "url('/images/hit.gif'";
			}
		}
	}
}
function usersShipWasSunk(shipType) {
	var count = 0;
	for (const property in ShipType) {
		if (shipType == property) {
			userShipsLife[count].style.backgroundColor = "#E84948";
		}
		count++;
	}
}
function userSunkAShip(shipType) {
	var count = 0;
	for (const property in ShipType) {
		if (shipType == property) {
			opponentShipsLife[count].style.backgroundColor = "#E84948";
		}
		count++;
	}
}

function userLost() {
	loser.style.display = "block";
	game.style.display = "none";
	gameInfo.style.display = "none";
}
function userWon() {
	winner.style.display = "block";
	game.style.display = "none";
	gameInfo.style.display = "none";
}
function itsYourTurn() {
	opponentBoard.style.display = "block";
	gameInfo.style.display = "block";
	placer.style.display = "none";
	document.getElementById("currentPlayerTurn").innerHTML = "Your Turn!";

	for (let i = 0, row; row = opponentBoard.rows[i]; i++) {
		for (let j = 0, col; col = row.cells[j]; j++) {
			if (col.classList.contains('otiles2')) {
				col.classList.remove('otiles2');
				col.classList.add('otiles');
			}
		}
	}

}
function itsTheOpponentsTurn() {
	//TODO lock board (but still let them see it)
	opponentBoard.style.display = "block";
	gameInfo.style.display = "block";
	placer.style.display = "none";
	document.getElementById("currentPlayerTurn").innerHTML = "Not Your Turn!";

	for (let i = 0, row; row = opponentBoard.rows[i]; i++) {
		for (let j = 0, col; col = row.cells[j]; j++) {
			if (col.classList.contains('otiles')) {
				col.classList.remove('otiles');
				col.classList.add('otiles2');
			}
		}
	}
}
var gameHasStarted = false;
function shipPlacementFailure(shipType) {
	switch (shipType) {
		case ShipType.AIRCRAFT_CARRIER:
			placingShips[0].click();
			break;
		case ShipType.BATTLESHIP:
			placingShips[1].click();
			break;
		case ShipType.CRUISER:
			placingShips[2].click();
			break;
		case ShipType.DESTROYER:
			placingShips[3].click();
			break;
		case ShipType.SUBMARINE:
			placingShips[4].click();
			break;
		default:
			console.log('Error while trying to display Place Ships button after failed ship placement.')
	}
	//	
	//	playerMouseEvents();


}

function hideShipPlacementForShip(shipType) {
	switch (shipType) {
		case ShipType.AIRCRAFT_CARRIER:
			placingShips[0].style.display = 'none';
			break;
		case ShipType.BATTLESHIP:
			placingShips[1].style.display = 'none';
			break;
		case ShipType.CRUISER:
			placingShips[2].style.display = 'none';
			break;
		case ShipType.DESTROYER:
			placingShips[3].style.display = 'none';
			break;
		case ShipType.SUBMARINE:
			placingShips[4].style.display = 'none';
			break;
		default:
			console.log('internal error')
	}
}

function displayShipInPositions(positions) {
	positions.forEach(function (position) {
		position.xPos = position.xPos + "";
		var initialAscii = position.xPos.charCodeAt(0) + 49;
		var x = String.fromCharCode(initialAscii);
		var y = position.yPos + 1;

		var targetId = x + "" + y;

		for (var i = 0, row; row = playerBoard.rows[i]; i++) {
			for (var j = 0, col; col = row.cells[j]; j++) {
				if (col.id == targetId) {
					col.style.backgroundImage = "url('/images/placeShip.png'";
				}
			}
		}
	});
}

function sendShipPlacementRequest(position, direction, shipType) {
	var shipPlacementRequest = {
		"position": position,
		"direction": direction,
		"shipType": shipType
	}
	var url = "/shipPlacement/" + gameId;
	sendRequest(shipPlacementRequest, url, "POST", reciveShipPlacementResponse);
}

function sendTargetCellRequest(position) {
	stompClient.send("/fromjs/targetCell/" + gameId, {}, JSON.stringify({ "position": position }));
}

class BoardPosition {
	constructor(xPos, yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
}

var Direction = {
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

window.onload = function () {
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
	xmlHttp.onreadystatechange = function () {
		if (this.readyState === XMLHttpRequest.DONE) {
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
		stompClient.subscribe('/tojs/attackResult/' + id, function (attackResult) {
			reciveAttackResult(JSON.parse(attackResult.body));
		});
		stompClient.subscribe('/tojs/sunkShip/' + id, function (shipSunkNotification) {
			reciveShipSunkNotificaiton(JSON.parse(shipSunkNotification.body));
		});
		stompClient.subscribe('/tojs/gameOver/' + id, function (gameOverNotification) {
			reciveGameOverNotification(JSON.parse(gameOverNotification.body));
		});
		stompClient.subscribe('/tojs/turnChange/' + id, function (turnChangeNotification) {
			reciveTurnChangeNotification(JSON.parse(turnChangeNotification.body));
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
	console.log(shipPlacementResponse);
	if (shipPlacementResponse.success) {
		displayShipInPositions(shipPlacementResponse.positions);
		hideShipPlacementForShip(shipPlacementResponse.shipType);
	} else if (!gameHasStarted) {
		shipPlacementFailure(shipPlacementResponse.shipType);
	}
}

function reciveAttackResult(attackResult) {
	console.log(attackResult);
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
				console.log("Miss");
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
	if (gameOverNotification) {
		console.log("game over");
		if (gameOverNotification.winner == username) {
			userWon();
		} else {
			userLost();
		}
	}
	// disconnect();
}
function reciveTurnChangeNotification(turnChangeNotification) {
	gameHasStarted = true;
	if (turnChangeNotification.playerUsername == username) {
		itsYourTurn();
	} else {
		itsTheOpponentsTurn();
	}
}

function setupGameFromServer() {
	// setupYourBoardFromServer();
	// setupOpponentBoardFromServer();
	setupCellsFromServer();
	setupShipsFromServer();
	setupDestroyedShipsFromServer();
	setupGameHasStartedFromServer();
	setupGameHasEndedFromServer();
}

async function setupCellsFromServer() {
	sendRequest(null, "/gamestate/attacks/" + gameId, "GET", function (attackResults) {
		attackResults.forEach(reciveAttackResult);
	});
}

async function setupYourBoardFromServer() {
	var board = { rows: new Array() };
	for (var i = 0; i < 10; i++) {
		await setupBoardRowFromServer(board, i, false);
	}
	showUsersBoard(board);
}

function setupShipsFromServer() {
	sendRequest(null, "/gamestate/ships/" + gameId, "GET", function (shipPlacementResponses) {
		shipPlacementResponses.forEach(reciveShipPlacementResponse);
	});
}

function setupDestroyedShipsFromServer() {
	sendRequest(null, "/gamestate/destroyed/" + gameId, "GET", function (shipSunkNotifications) {
		shipSunkNotifications.forEach(reciveShipSunkNotificaiton);
	});
}


async function setupBoardRowFromServer(board, row, opponent) {
	await sendRequest(null, "/gamestate/row/" + gameId + "/" + row + "/" + opponent, "GET", function (boardRow) {
		board.rows.push(boardRow);
	});
}

function reciveGameStartedNotification(gameStartedNotification) {
	if (gameStartedNotification.started) {
		reciveTurnChangeNotification(gameStartedNotification.turn);
	}
}

function setupGameHasStartedFromServer() {
	sendRequest(null, "/gamestate/started/" + gameId, "GET", reciveGameStartedNotification);
}
function setupGameHasEndedFromServer() {
	sendRequest(null, "/gamestate/over/" + gameId, "GET", reciveGameOverNotification);
}

function setupOpponentBoardFromServer() {
	var board = { rows: new Array() };
	for (var i = 0; i < 10; i++) {
		setupBoardRowFromServer(board, i, true);
	}
	showOpponentBoard(board);
}
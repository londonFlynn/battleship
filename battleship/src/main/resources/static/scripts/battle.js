var stompClient = null;

window.onload = connect;

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
            showGame(JSON.parse(game.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendGame() {
    stompClient.send("/fromjs/game", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGame(game) {
    console.log(game);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    // $( "#connect" ).click(function() { connect(); });
    // $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendGame(); });
});


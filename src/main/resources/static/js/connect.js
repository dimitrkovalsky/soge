var connected = false;
var stompClient;
var socket;
function onError(error) {
    console.log("Error ", error);
}
function setConnected() {
    connected = true;
}

function setDisconnected() {
    connected = false;
}
function performConnect() {
    socket = new SockJS('/notifications');
    stompClient = Stomp.over(socket);
    stompClient.connect("test", "test", function (frame) {
        setConnected();
        stompClient.subscribe('/topic/broadcast', onBroadcast);
        stompClient.subscribe('/user/' + window.sogeToken + '/notify', onBroadcast);
    });
}
function connect() {
    if (connected) {
        performDisconnect();
    } else {
        performConnect();
    }
}
function onBroadcast(frame) {
    //var msg = JSON.parse(frame.body);
    // addLog(frame.body);
    console.log(frame.body);
}

function performDisconnect() {
    if (!stompClient && connected)
        return;
    stompClient.disconnect(function () {
        setDisconnected();
        console.log("Disconnected");
    });
}

// performConnect();


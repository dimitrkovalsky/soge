var connected = false;
var stompClient;
var socket;
function onError(error) {
    console.log("Error ", error);
}
function setConnected() {
    connected = true;
    var btn = $('#connect_btn');
    btn.removeClass('btn-default').addClass("btn-danger");
    btn.text("Disconnect");
}

function setDisconnected() {
    connected = false;
    var btn = $('#connect_btn');
    btn.removeClass("btn-danger").addClass('btn-default');
    btn.text("Connect");
}
function performConnect() {
    socket = new SockJS('/updates');
    stompClient = Stomp.over(socket);
    stompClient.connect("test", "test", function (frame) {
        debugger;
        setConnected();
        stompClient.subscribe('/topic/live', onUpdate);
        stompClient.subscribe('/user/' + frame.headers['user-name'] + '/notify', console.debug);

    });
}
function connect() {
    if (connected) {
        performDisconnect();
    } else {
        performConnect();
    }
}
function onUpdate(frame) {
    //var msg = JSON.parse(frame.body);
    addLog(frame.body);
}

function performDisconnect() {
    if (!stompClient && connected)
        return;
    stompClient.disconnect(function () {
        setDisconnected();
        console.log("Disconnected");
    });
}
function sendRequest() {
    var request = $('#requestData').val();
    stompClient.send("/user/requests", {priority: 9}, request);
}

function addLog(msg) {
    console.log("Message : " + msg);
    var logList = $('#logList');
    logList.append('<li>' + msg + '</li>');
    logList.scrollTop = logList.scrollHeight;
}

var defaultRequest = {
    messageType: 1,
    data: null
};

function setDefaultRequest() {
    var msg = JSON.stringify(defaultRequest);
    $('#requestData').val(msg);
}
setDefaultRequest();


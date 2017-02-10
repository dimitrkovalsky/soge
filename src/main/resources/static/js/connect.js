window.connected = false;
var stompClient;
function onError(error) {
    console.log("Error ", error);
};

function connect() {
    var socket = new SockJS('/updates');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        connected = true;
        var btn = $('#connect_btn');
        btn.removeClass('btn-default').addClass("btn-danger");
        btn.text("Disconnect");
        stompClient.subscribe('/topic/live', onUpdate);
    });
};

function onUpdate(frame) {
    //var msg = JSON.parse(frame.body);
    addLog(frame.body);
};

function sendRequest() {
  var request = $('#requestData').val();
  stompClient.send(request);
}

function addLog(msg) {
   console.log("Message : " + msg);
    var logList = $('#logList');
    logList.append('<li>'+msg+'</li>');
    logList.scrollTop  = logList.scrollHeight;
};

var defaultRequest = {
   messageType: 1,
   data:null
};

function setDefaultRequest() {
    var msg = JSON.stringify(defaultRequest);
    $('#requestData').val(msg);
}
setDefaultRequest();


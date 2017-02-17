var authenticated = false;
var sogeToken;
var userId;
var requestHandlers;

function setAuthenticated() {
    authenticated = true;
    var btn = $('#auth_btn');
    btn.removeClass('btn-default').addClass("btn-danger");
    btn.text("logout");
}

function setLogout() {
    authenticated = false;
    var btn = $('#auth_btn');
    btn.removeClass("btn-danger").addClass('btn-default');
    btn.text("login");
}

function auth() {
    closeError();
    if(!authenticated)
        performAuth($('#login').val(), $('#password').val());
    else
        performLogout();
}

function performLogout() {
    data = JSON.stringify({token: sogeToken});
    $.ajax({
      type: "POST",
      url: "/auth/logout",
      contentType:"application/json; charset=utf-8",
      data: data,
      error: onResponseError,
      success: function() {
         sogeToken = null;
         userId = null;
         setLogout();
         $('#token').empty();
      }
    });
}

function performAuth(login, password) {
    closeError();
    var request = JSON.stringify({ login: login, password: password });

    function onAuthComplete(data) {
        sogeToken = data.token;
        userId = data.userId;
        $('#token').append("<div>token : <strong class='token''>" + sogeToken +"</strong></div>");
        setAuthenticated();
    }

    $.ajax({
      type: "POST",
      url: "/auth/login",
      contentType:"application/json; charset=utf-8",
      data: request,
      success: onAuthComplete,
      error: onResponseError
    });
}

function showError(error) {
    $('#errorSpace').css("display", "");
    $('#errorSpace').empty().append(error);
}

function closeError() {
    $('#errorSpace').empty();
    $('#errorSpace').css("display", "none");
}

function sendRequest(){
    closeError();
    var data = $('#requestData').val();
    if(!sogeToken){
        showError("Token can not be empty");
        return;
    }
    $.ajax({
      type: "POST",
      url: "/api",
      headers: {
       'Soge-Token' : sogeToken
      },
      contentType:"application/json; charset=utf-8",
      data: data,
      success: onRequestComplete,
      error: onResponseError
    });
}

function onResponseError(XMLHttpRequest, textStatus, errorThrown) {
   showError("Status code : " + XMLHttpRequest.status);
   addErrorLog(errorThrown);
}

function onRequestComplete(data) {
    var msg = JSON.stringify(data);
    if(data.responseCode == 201){
        addErrorLog(msg);
    }  else {
        addLog(msg);
    }
}

function addErrorLog(msg) {
    console.error(msg);
    $('#logList').append('<li style="color: red; font-size: 14px;">' + msg + '</li>');
    scrollDown();
}

function addLog(msg) {
    console.log("Message : " + msg);
    $("#logList").append('<li style="font-size: 14px;">' + msg + '</li>');
    scrollDown();
}

function scrollDown() {
    var scrollLog = document.getElementById('logList');
    scrollLog.scrollTop = scrollLog.scrollHeight;
}

function loadRequest(messageType) {
   console.log("Loading " + messageType);
   var handler  = getHandler(messageType);
   var request = JSON.stringify({messageType: handler.messageType, data : handler.input});
   $('#requestData').val(request);
}

function getHandler(messageType) {
    for(var i in requestHandlers) {
     if(requestHandlers[i].messageType == messageType){
         return requestHandlers[i];
     }
    }
}



function loadHandlers() {
    $.get( "/admin/handlers",null, function( data ) {
        requestHandlers = data;
        for(var i in data) {
            $('#requestList').append(createHandlerElement(data[i].messageType, data[i].handlerClass, data[i].handlerFull));
        }
    });
}

function createHandlerElement(messageType, handlerClass, handlerFull) {
     var element = '<div href="#" class="list-group-item">' +
       '<strong class="list-group-item-heading" style="cursor: pointer;" '+ 'onclick="loadRequest(' + messageType  +')" ' +
       'title="'+ handlerFull +'">'+
       handlerClass +'</strong>' +
        '<small class="list-group-item-text">'+ 'messageType:' + messageType +'</small>'+
      '</div>';

      return element;
}

loadHandlers();

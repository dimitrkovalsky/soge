window.authenticated = false;
window.sogeToken = "";
window.userId = "";
window.requestHandlers;

function setAuthenticated() {
    window.authenticated = true;
    var btn = $('#auth_btn');
    btn.removeClass('btn-default').addClass("btn-danger");
    btn.text("logout");
    performConnect();
}

function setLogout() {
    window.authenticated = false;
    var btn = $('#auth_btn');
    btn.removeClass("btn-danger").addClass('btn-default');
    btn.text("login");
}

//function auth() {
//    closeNotifications();
//    if (!window.authenticated)
//        performAuth($('#login').val(), $('#password').val());
//    else
//        performLogout();
//}

function auth() {
    performAuth($('#login').val(), $('#password').val());
}

function performLogout() {
    data = JSON.stringify({token: window.sogeToken});
    $.ajax({
        type: "POST",
        url: "/auth/logout",
        contentType: "application/json; charset=utf-8",
        data: data,
        error: onResponseError,
        success: function () {
            window.sogeToken = null;
            window.userId = null;
            setLogout();
            $('#token').empty();
        }
    });
}

function performAuth(login, password) {
    closeNotifications();
    var request = JSON.stringify({login: login, password: password});

    function onAuthComplete(data) {
        window.sogeToken = data.token;
        window.userId = data.userId;
        $('#token').append("<div>token : <strong class='token''>" + window.sogeToken + "</strong></div>");
        setAuthenticated();
    }

    $.ajax({
        type: "POST",
        url: "/j_spring_security_check",
        contentType: "application/json; charset=utf-8",
        data: request,
        success: onAuthComplete,
        error: onResponseError
    });
}

function showError(error) {
    $('#errorSpace').css("display", "");
    $('#errorSpace').empty().append(error);
}

function showInfo(info) {
    $('#infoSpace').css("display", "");
    $('#infoSpace').empty().append(info);
}

function closeNotifications() {
    $('#errorSpace').empty();
    $('#errorSpace').css("display", "none");
    $('#infoSpace').empty();
    $('#infoSpace').css("display", "none");
}

function sendRequest() {
	console.log('click');
    closeNotifications();
    var data = $('#requestData').val();
    sendPost("/api", data, onRequestComplete);
}

function onResponseError(XMLHttpRequest, textStatus, errorThrown) {
    showError("Status code : " + XMLHttpRequest.status);
    addErrorLog(errorThrown);
}

function onRequestComplete(data) {
    var msg = JSON.stringify(data);
    if (data.responseCode == 201) {
        addErrorLog(msg);
    } else {
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
    var handler = getHandler(messageType);
    var request = JSON.stringify({messageType: handler.messageType, data: handler.input});
    $('#requestData').val(request);
}

function getHandler(messageType) {
    for (var i in requestHandlers) {
        if (requestHandlers[i].messageType == messageType) {
            return requestHandlers[i];
        }
    }
}

function loadHandlers() {
    $.get("/admin/handlers", null, function (data) {
        window.requestHandlers = data;
        for (var i in data) {
            $('#requestList').append(createHandlerElement(data[i].messageType, data[i].handlerClass, data[i].handlerFull));
        }
    });
}

function createHandlerElement(messageType, handlerClass, handlerFull) {
    var element = '<div href="#" class="list-group-item">' +
        '<strong class="list-group-item-heading" style="cursor: pointer;" ' + 'onclick="loadRequest(' + messageType + ')" ' +
        'title="' + handlerFull + '">' +
        handlerClass + '</strong>' +
        '<small class="list-group-item-text">' + 'messageType:' + messageType + '</small>' +
        '</div>';

    return element;
}

function generateModel() {
    var modelName = $("#modelNameInput").val();
    closeNotifications();
    if (!modelName) {
        showError("Model name cannot be empty.");
    } else {
        sendGenerationRequest("model", modelName);
    }
}

function generateRepository() {
    var modelName = $("#repositoryNameInput").val();
    closeNotifications();
    if (!modelName) {
        showError("Repository name cannot be empty.");
    } else {
        sendGenerationRequest("repository", modelName);
    }
}

function generateAction() {
    var modelName = $("#actionNameInput").val();
    var messageType = $("#messageTypeInput").val();
    closeNotifications();
    if (!modelName || !messageType) {
        showError("Action name or messageType cannot be empty.");
    } else {
        var data = JSON.stringify({className: modelName, messageType: messageType});
        sendPost("/generator/generate/action", data, function () {
            showInfo("Successfully generated " + modelName + " action handler");
        })
    }
}

function sendGenerationRequest(path, className) {
    var data = JSON.stringify({className: className});
    sendPost("/generator/generate/" + path, data, function () {
        showInfo("Successfully generated " + className);
    })
}

function sendPost(url, data, onRequestComplete) {
    if (!window.sogeToken) {
        showError("Token can not be empty");
        return;
    }
    $.ajax({
        type: "POST",
        url: url,
        headers: {
            'Soge-Token': window.sogeToken
        },
        contentType: "application/json; charset=utf-8",
        data: data,
        success: onRequestComplete,
        error: onResponseError
    });
}

loadHandlers();

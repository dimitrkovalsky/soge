    $.material.init()

    window.connected = false;

    function onError(error) {
        console.log("Error ", error);
    };

    function connect() {
        var socket = new SockJS('/updates');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            connected = true;
            stompClient.subscribe('/topic/live', onUpdate);
        });
    };

   function onUpdate(frame) {
        var msg = JSON.parse(frame.body);
        addLog(msg);
//        switch (msg.messageType) {
//            case 1 {
//                addLog(msg);
//                break;
//            }
//
//            default :
//                console.log("Unrecognized message type : " + msg);
//        }
    };

   function addLog(msg) {
      console.log("Message : " + msg);
   };

   connect();
package com.liberty.soge.websockets;

import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.MessageProcessor;
import com.liberty.soge.notification.NotificationBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * User: Dimitr Date: 22.05.2016 Time: 21:36
 */
//@Controller
@Slf4j
public class WebSocketFrontController implements NotificationBus {

    public static final String TOPIC_NAME = "/topic/live";

    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private MessageProcessor processor;

    @SendTo(TOPIC_NAME)
    @MessageMapping("/updates")
    public int onUpdate(String message) {
        log.info("Message : " + message);
        return 0;
    }

    @MessageMapping("/requests")
    public void onRequest(String message) {
        log.info("Message on Request: " + message);
    }

    private GenericResponse processMessage(String json) {
        Action action = processor.process(json);
        return action.execute();
    }

    @Override
    public void sendAll(GenericResponse msg) {
        if (template != null) {
            template.convertAndSend(TOPIC_NAME, msg);
        } else {
            log.error("Client doesn't connected");
        }
    }

    @Override
    public void sendUser(String user, GenericResponse msg) {
        if (template != null) {
            template.convertAndSendToUser(user, TOPIC_NAME, msg);
        } else {
            log.error("Client doesn't connected");
        }
    }


}

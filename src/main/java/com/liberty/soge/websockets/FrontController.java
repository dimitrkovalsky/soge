package com.liberty.soge.websockets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * User: Dimitr Date: 22.05.2016 Time: 21:36
 */
@Controller
@Slf4j
public class FrontController {

    public static final String TOPIC_NAME = "/topic/live";

    @Autowired
    private SimpMessagingTemplate template;


    @SendTo(TOPIC_NAME)
    @MessageMapping("/updates")
    public int onUpdate(String message) {
        log.info("Message : " + message);
        return 0;
    }

    private void sendAll(GenericMessage msg) {
        if (template != null) {
            template.convertAndSend(TOPIC_NAME, msg);
        } else {
            log.error("Client doesn't connected");
        }
    }

    private void sendUser(String user, GenericMessage msg) {
        if (template != null) {
            template.convertAndSendToUser(user, TOPIC_NAME, msg);
        } else {
            log.error("Client doesn't connected");
        }
    }

    public void live(String data) {
        log.info("Live connected : " + data);
    }
    
}

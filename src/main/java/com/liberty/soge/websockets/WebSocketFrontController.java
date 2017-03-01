package com.liberty.soge.websockets;

import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.model.UserSession;
import com.liberty.soge.notification.NotificationBus;
import com.liberty.soge.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * User: Dimitr Date: 22.05.2016 Time: 21:36
 */
@Controller
@Slf4j
public class WebSocketFrontController implements NotificationBus {

    public static final String TOPIC_NAME = "/topic/broadcast";
    public static final String USER_NOTIFICATIONS = "/user/%s/notify";

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void sendAll(GenericResponse msg) {
        if (template != null) {
            template.convertAndSend(TOPIC_NAME, msg);
        } else {
            log.error("Client doesn't connected");
        }
    }

    @Override
    public void sendUser(String userId, GenericResponse msg) {
        if (template != null) {
            Optional<UserSession<String>> session = authenticationService.getUserSessionByUserId(userId);
            if (session.isPresent())
                template.convertAndSend(String.format(USER_NOTIFICATIONS, session.get().getToken()), msg);
            else
                log.info(String.format("User %s doesn't connected: ", userId));
        } else {
            log.error("Client doesn't connected");
        }
    }


}

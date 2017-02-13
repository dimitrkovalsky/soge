package com.liberty.soge.websockets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Component
@Slf4j
public class UserListener {

    private long activeUsers = 0;

    @EventListener
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        activeUsers++;
        log.info("Client connected : " + sessionConnectedEvent.getUser() + ". Active Users: " + activeUsers);
    }

    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        activeUsers--;
        log.info("Client disconnected : " + sessionDisconnectEvent.getUser() + ". Active Users: " + activeUsers);
    }
}

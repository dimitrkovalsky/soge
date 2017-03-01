package com.liberty.soge.gameword;


import com.liberty.soge.annotation.EventHandler;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.notification.NotificationBus;
import com.liberty.soge.types.ResponseCode;
import com.liberty.soge.types.SogeMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Slf4j
@Component
public class GenericEventHandler {

    @Autowired
    private NotificationBus bus;

    @EventHandler(GameEvent.class)
    public void handleEvent(GameEvent event) {
        log.info("Trying to handle event : " + event);
    }


    @EventHandler(DevelopmentCompletedEvent.class)
    public void handleEvent(DevelopmentCompletedEvent event) {
        log.info("Trying to handle specific event : " + event);
        bus.sendUser(event.getUserId(), new GenericResponse(SogeMessageType.SYSTEM_NOTIFICATION, SogeMessageType.SYSTEM_NOTIFICATION,
                ResponseCode.STATUS_OK, event.toString()));
    }


}

package com.liberty.soge.notification;

import com.liberty.soge.action.errors.ErrorFactory;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.JsonHelper;
import com.liberty.soge.types.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.liberty.soge.types.SogeMessageType.SYSTEM_ERROR_NOTIFICATION;
import static com.liberty.soge.types.SogeMessageType.SYSTEM_NOTIFICATION;

/**
 * @author Dmytro_Kovalskyi.
 * @since 10.02.2017.
 */
@Service
@Slf4j
public class WebSocketNotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationBus notificationBus;

    @Override
    public void notifyUser(String username, Notification notification) {
        GenericResponse response = buildSuccessResponse(notification);
        notificationBus.sendUser(username, response);
    }

    @Override
    public void notifyErrorUser(String username, Notification notification) {
        String msg = JsonHelper.toJsonString(notification);
        GenericResponse response = ErrorFactory.createResponse(SYSTEM_ERROR_NOTIFICATION, msg);
        notificationBus.sendUser(username, response);
    }

    @Override
    public void notifyAll(Notification notification) {
        GenericResponse msg = buildSuccessResponse(notification);
        log.info("Notification: " + msg);
        notificationBus.sendAll(msg);
    }

    private GenericResponse buildSuccessResponse(Notification notification) {
        return new GenericResponse(SYSTEM_NOTIFICATION, SYSTEM_NOTIFICATION, ResponseCode.STATUS_OK, notification);
    }

}

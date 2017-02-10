package com.liberty.soge.notification;

import com.liberty.soge.common.GenericResponse;

/**
 * @author Dmytro_Kovalskyi.
 * @since 10.02.2017.
 */
public interface NotificationBus {

    void sendAll(GenericResponse msg);

    void sendUser(String user, GenericResponse msg);
}

package com.liberty.soge.notification;

/**
 * @author Dmytro_Kovalskyi.
 * @since 10.02.2017.
 */
public interface NotificationService {
    void notifyUser(String username, Notification notification);

    void notifyErrorUser(String username, Notification notification);

    void notifyAll(Notification notification);
}

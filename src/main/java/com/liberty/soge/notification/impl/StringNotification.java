package com.liberty.soge.notification.impl;

import com.liberty.soge.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 10.02.2017.
 */
@Data
@AllArgsConstructor
public class StringNotification extends Notification {
    private String message;
}

package com.liberty.soge.model;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Data
public class UserSession<T> {
    private String token;
    private T userId;
}

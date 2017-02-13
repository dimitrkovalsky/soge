package com.liberty.soge.security;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Data
public class UserCredentials {
    private String login;
    private String password;
}

package com.liberty.soge.service;

import com.liberty.soge.model.UserSession;
import com.liberty.soge.security.TokenAuthentication;
import com.liberty.soge.security.UserBase;

import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
public interface AuthenticationService<T> {
    Optional<TokenAuthentication<T>> login(String login, String password);

    void logout(String token);

    Optional<UserBase<T>> getUser(String token);

    UserSession getCurrentUser();
}

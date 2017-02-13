package com.liberty.soge.security;

import java.security.Principal;
import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
public interface AuthenticationService {
    Optional<Principal> login(String login, String password);
}

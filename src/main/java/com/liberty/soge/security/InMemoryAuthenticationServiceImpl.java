package com.liberty.soge.security;

import com.sun.security.auth.UserPrincipal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Service
public class InMemoryAuthenticationServiceImpl implements AuthenticationService {
    private Map<String, String> users = new HashMap<String, String>() {{
        put("test", "test");
    }};

    @Override
    public Optional<Principal> login(String login, String password) {
        String toCheckPassword = users.get(login);
        if (toCheckPassword == null)
            return Optional.empty();
        if (toCheckPassword.equals(password))
            return Optional.of(new UserPrincipal(login));
        return Optional.empty();
    }
}

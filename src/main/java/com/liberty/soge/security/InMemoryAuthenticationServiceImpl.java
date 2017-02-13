package com.liberty.soge.security;

import org.springframework.stereotype.Service;

import java.util.*;

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
    public Optional<TokenAuthentication> login(String login, String password) {
        String toCheckPassword = users.get(login);
        if (toCheckPassword == null)
            return Optional.empty();
        if (toCheckPassword.equals(password)) {
            UserAccount userAccount = new UserAccount("test-id", login, password);
            TokenAuthentication authentication = new TokenAuthentication(UUID.randomUUID().toString(),
                    Collections.emptyList(), true, userAccount);
            return Optional.of(authentication);
        }
        return Optional.empty();
    }
}

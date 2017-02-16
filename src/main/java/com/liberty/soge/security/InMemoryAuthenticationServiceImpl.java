package com.liberty.soge.security;

import com.liberty.soge.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Service
@Slf4j
public class InMemoryAuthenticationServiceImpl implements AuthenticationService<String> {
    private Map<String, InMemoryUser> users = new HashMap<String, InMemoryUser>() {{
        put("test", new InMemoryUser("test", "test", "test-id"));
    }};
    private Map<String, InMemoryUser> activeUsers = new HashMap<>();


    @Override
    public Optional<TokenAuthentication<String>> login(String login, String password) {
        // TODO: if logged invalidate session
        InMemoryUser user = users.get(login);
        if (user == null)
            return Optional.empty();
        if (user.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            TokenAuthentication<String> authentication = new TokenAuthentication<>(user.getUserId(), token);
            activeUsers.put(token, user);
            return Optional.of(authentication);
        }
        return Optional.empty();
    }

    @Override
    public void logout(String token) {
        InMemoryUser removed = activeUsers.remove(token);
        if (removed != null) {
            log.info("Successfully logout for : " + removed.getLogin());
        } else {
            log.info("Can not find active session for : " + token);
        }
    }

    @Override
    public Optional<UserBase<String>> getUser(String token) {
        InMemoryUser inMemoryUser = activeUsers.get(token);
        if (inMemoryUser == null)
            return Optional.empty();
        return Optional.of(new UserAccount(inMemoryUser.userId, inMemoryUser.getLogin()));
    }

    @Data
    @AllArgsConstructor
    private class InMemoryUser {
        private String login;
        private String password;
        private String userId;
    }
}

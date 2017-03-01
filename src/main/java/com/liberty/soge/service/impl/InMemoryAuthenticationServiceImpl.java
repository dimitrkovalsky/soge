package com.liberty.soge.service.impl;

import com.liberty.soge.gameword.DevelopmentCompletedEvent;
import com.liberty.soge.gameword.EventGenerator;
import com.liberty.soge.model.UserAccount;
import com.liberty.soge.model.UserSession;
import com.liberty.soge.security.TokenAuthentication;
import com.liberty.soge.security.UserBase;
import com.liberty.soge.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private EventGenerator eventGenerator;

    @Autowired
    private HttpSession httpSession;

    private Map<String, InMemoryUser> users = new HashMap<String, InMemoryUser>() {{
        put("test", new InMemoryUser("test", "test", "test-id"));
    }};
    private Map<String, InMemoryUser> activeUsers = new HashMap<>();


    @Override
    public Optional<TokenAuthentication<String>> login(String login, String password) {

        eventGenerator.generateEvent(new DevelopmentCompletedEvent("test-id", 42L),
                System.currentTimeMillis() + 5000);
        eventGenerator.generateEvent(new DevelopmentCompletedEvent("test-id", 43L),
                System.currentTimeMillis() + 5000);
        eventGenerator.generateEvent(new DevelopmentCompletedEvent("test-id2", 45L),
                System.currentTimeMillis() + 7000);
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
        return Optional.of(new UserAccount(inMemoryUser.userId, "ext-id-" + inMemoryUser.userId,
                inMemoryUser.getLogin()));
    }

    // TODO: implement
    @Override
    public UserSession<String> getCurrentUser() {
        UserSession<String> userSession = new UserSession<>();
        return userSession;
    }

    @Override
    public Optional<UserSession<String>> getUserSessionByUserId(String userId) {
        Optional<UserSession<String>> user = Optional.empty();
        for (Map.Entry<String, InMemoryUser> userEntry : activeUsers.entrySet()) {
            if (userEntry.getValue().getUserId().equals(userId)) {
                user = Optional.of(new UserSession<String>(userEntry.getKey(), userId));
                break;
            }

        }

        return user;
    }

    @Data
    @AllArgsConstructor
    private class InMemoryUser {
        private String login;
        private String password;
        private String userId;
    }
}

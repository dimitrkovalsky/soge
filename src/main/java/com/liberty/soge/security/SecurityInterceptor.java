package com.liberty.soge.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Component
@Slf4j
public class SecurityInterceptor extends ChannelInterceptorAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String login = accessor.getLogin();
            String password = accessor.getPasscode();
            Optional<Principal> principal = authenticationService.login(login, password);
            if (principal.isPresent()) {
                accessor.setUser(principal.get());
                log.info("Logged as " + principal.get().getName());
            } else
                return null;
        }
       // message.getHeaders().
        return message;
    }
}

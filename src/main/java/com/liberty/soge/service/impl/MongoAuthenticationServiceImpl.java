package com.liberty.soge.service.impl;

import com.liberty.soge.model.UserSession;
import com.liberty.soge.repository.UserRepository;
import com.liberty.soge.security.TokenAuthentication;
import com.liberty.soge.security.UserBase;
import com.liberty.soge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
// TODO: override according to vkontakte login
public class MongoAuthenticationServiceImpl implements AuthenticationService<String> {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Optional<TokenAuthentication<String>> login(String login, String password) {
        
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public Optional<UserBase<String>> getUser(String token) {
        return null;
    }

    @Override
    public UserSession<String> getCurrentUser() {
        return null;
    }
}

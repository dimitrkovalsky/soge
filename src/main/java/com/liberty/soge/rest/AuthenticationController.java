package com.liberty.soge.rest;

import com.liberty.soge.service.AuthenticationService;
import com.liberty.soge.security.TokenAuthentication;
import com.liberty.soge.security.UserCredentials;
import com.liberty.soge.security.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService<String> authenticationService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public UserToken login(@RequestBody UserCredentials credentials) {
        // TODO: validate for null
        Optional<TokenAuthentication<String>> login =
                authenticationService.login(credentials.getLogin(), credentials.getPassword());
        if (login.isPresent()) {
            TokenAuthentication<String> authentication = login.get();
            return new UserToken(authentication.getUserId(), authentication.getToken());
        }
        throw new BadCredentialsException("Bad credentials for : " + credentials.getLogin());
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void login(@RequestBody UserToken token) {
        // TODO: validate for null
        authenticationService.logout(token.getToken());
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public UserToken register(@RequestBody UserCredentials credentials) {
        return null;
    }
}

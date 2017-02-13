package com.liberty.soge.rest;

import com.liberty.soge.security.AuthenticationService;
import com.liberty.soge.security.UserCredentials;
import com.liberty.soge.security.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public UserToken login(@RequestBody UserCredentials credentials) {
        return null;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public UserToken register(@RequestBody UserCredentials credentials) {
        return null;
    }
}

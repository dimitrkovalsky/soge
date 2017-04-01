package com.liberty.soge.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthorizeRestController {
    
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public void login() {
        log.info("called");
    }

}

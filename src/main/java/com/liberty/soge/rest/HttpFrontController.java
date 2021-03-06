package com.liberty.soge.rest;

import com.liberty.soge.action.Action;
import com.liberty.soge.common.EventHandlerService;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.MessageProcessor;
import com.liberty.soge.gameword.DevelopmentCompletedEvent;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api")
@Slf4j
public class HttpFrontController {

    @Autowired
    private MessageProcessor processor;
    
    @Autowired
    private EventHandlerService eventHandler;

    @RequestMapping(method = RequestMethod.POST)
    public GenericResponse process(@RequestBody String message) {
        
        eventHandler.handle(new DevelopmentCompletedEvent("some user id", 42L));
        
        Action action = processor.process(message);
        return action.execute();
    }
}

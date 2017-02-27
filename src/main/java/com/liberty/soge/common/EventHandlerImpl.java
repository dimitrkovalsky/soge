package com.liberty.soge.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberty.soge.gameword.GameEvent;
import com.liberty.soge.register.events.EventHandlersProvider;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Service
public class EventHandlerImpl implements EventHandler {
    @Autowired
    private EventHandlersProvider eventHandlersProvider;
    
    @Override
    public void handle(GameEvent event) {
        
    }

}

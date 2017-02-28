package com.liberty.soge.common;

import java.lang.reflect.Method;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberty.soge.gameword.GameEvent;
import com.liberty.soge.register.events.EventHandlersProvider;
import com.liberty.soge.util.Pair;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Service
public class EventHandlerServiceImpl implements EventHandlerService {
    
    @Autowired
    private EventHandlersProvider eventHandlersProvider;
    
    private Set<Pair<Class<?>, Method>> eventHandlerMethods;
    
    @Override
    public void handle(GameEvent event) {
        Method m;
        m.invoke(event, args)
    }
    
    @PostConstruct
    private void init() {
        
    }
    
}

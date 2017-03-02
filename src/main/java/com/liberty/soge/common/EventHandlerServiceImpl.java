package com.liberty.soge.common;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    
    @Autowired
    private ApplicationContext context;
    
    private Map<Class<? extends GameEvent>, Pair<Class<?>, Method>> eventHandlerMethods;
    
    @Override
    public void handle(GameEvent event) {
        Pair<Class<?>, Method> beanMethodPair = eventHandlerMethods.get(event.getClass());
        Object bean = context.getBean(beanMethodPair.getKey());
        Method method = beanMethodPair.getValue();
        try {
            method.invoke(bean, event);
        } catch (Throwable e) {
            log.error("can't invoke event handler", e);
        }
        
    }
    
    @PostConstruct
    private void init() {
        eventHandlerMethods = eventHandlersProvider.getEventHandlerTypesMaping();
    }
    
}

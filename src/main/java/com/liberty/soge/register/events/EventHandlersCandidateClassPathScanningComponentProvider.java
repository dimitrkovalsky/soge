package com.liberty.soge.register.events;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import com.liberty.soge.action.Action;
import com.liberty.soge.gameword.GameEvent;
import com.liberty.soge.register.actions.ActionTypesProvider;

public class EventHandlersCandidateClassPathScanningComponentProvider extends ClassPathScanningCandidateComponentProvider implements EventHandlersProvider {
    
    private String[] packages;
    
    private Set<BeanDefinition> beanDefinitions;
    
    private Set<Class<?>> eventHandlers;
    
    private Set<Class<? extends GameEvent>> handlers = new HashSet<Class<? extends GameEvent>>();
    
    public EventHandlersCandidateClassPathScanningComponentProvider() {
        super(true);
    }

    @Override
    public Set<Class<?>> getEventHandlers() {
        return eventHandlers;
    }

}

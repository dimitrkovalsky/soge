package com.liberty.soge.register.events;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import com.liberty.soge.annotation.EventHandler;
import com.liberty.soge.annotation.EventHandlersType;
import com.liberty.soge.gameword.GameEvent;
import com.liberty.soge.util.Pair;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class EventHandlersCandidateClassPathScanningComponentProvider extends ClassPathScanningCandidateComponentProvider implements EventHandlersProvider {
    
    private String[] packages;
    
    private Set<BeanDefinition> beanDefinitions;
    
    private Set<Class<?>> eventHandlers;
    
    private Set<Class<?>> handlers = new HashSet<Class<?>>();
    
    public EventHandlersCandidateClassPathScanningComponentProvider() {
        super(true);
    }
    
    @PostConstruct
    public void init() throws ClassNotFoundException {
        beanDefinitions = new HashSet<BeanDefinition>();

        for (String p : packages) {
            beanDefinitions.addAll(findCandidateComponents(p));
        }
        
        eventHandlers = new HashSet<Class<?>>();
        
        for (BeanDefinition bd : beanDefinitions) {
            eventHandlers.add(Class.forName(bd.getBeanClassName()));
        }

        log.info("detected action types" + beanDefinitions);

    }
    
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        log.info("candidate = " + beanDefinition);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(beanDefinition.getBeanClassName());
            if(validate(clazz)) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            log.error("error during initialization", e);
        }
        return false;
    }
    
    @Override
    public Set<Class<?>> getEventHandlers() {
        return eventHandlers;
    }
    
    private boolean validate(Class<?> clazz) {
        if(clazz.getAnnotation(EventHandlersType.class) == null) {
            return false;
        }
        
        for (Field f : clazz.getDeclaredFields()) {
            EventHandler[] bindedHandlers = f.getAnnotationsByType(EventHandler.class);
            for (EventHandler h : bindedHandlers) {
                Class<? extends GameEvent> handlerClass = h.value();
                if (!handlers.add(handlerClass)) {
                    throw new RuntimeException("duplicate event handler detected handler = " 
                + handlerClass 
                + " in handlers type = " + clazz);
                }
            }
        }
        return true;
    }
    
}

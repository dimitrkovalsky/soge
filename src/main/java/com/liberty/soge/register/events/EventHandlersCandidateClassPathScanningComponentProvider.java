package com.liberty.soge.register.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    
    private Set<Class<?>> eventHandlers = new HashSet<Class<?>>();
    
    private Set<Class<?>> eventHandlerTypes = new HashSet<Class<?>>();
    
    private Map<Class<? extends GameEvent>, Pair<Class<?>, Method>> eventHandlerTypesMaping = new HashMap<>();
    
    public EventHandlersCandidateClassPathScanningComponentProvider() {
        super(true);
    }
    
    @PostConstruct
    public void init() throws ClassNotFoundException {
        beanDefinitions = new HashSet<BeanDefinition>();
        
        for (String p : packages) {
            findCandidateComponents(p);
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
    public Map<Class<? extends GameEvent>, Pair<Class<?>, Method>> getEventHandlerTypesMaping() {
        return eventHandlerTypesMaping;
    }
    
    private boolean validate(Class<?> clazz) {
        if(clazz.getAnnotation(EventHandlersType.class) == null) {
            return false;
        }
        
        if(!eventHandlerTypes.add(clazz)) {
            throw new RuntimeException("duplicate event handler type = " + clazz.getName());
        }
        
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(EventHandler.class)) {
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                Class<? extends GameEvent> handlerClass = eventHandler.value();
                if (!eventHandlers.add(handlerClass)) {
                    throw new RuntimeException("duplicate event handler detected handler = " 
                + handlerClass 
                + " in handlers type = " + clazz);
                }
                
                eventHandlerTypesMaping.put(handlerClass, new Pair<Class<?>, Method>(clazz, method));
            }
        }
        return true;
    }
    
}

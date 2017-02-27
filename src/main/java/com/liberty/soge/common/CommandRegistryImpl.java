package com.liberty.soge.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.BindActionToId;
import com.liberty.soge.register.actions.ActionTypesProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommandRegistryImpl implements CommandRegistry {
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private ActionTypesProvider actionsTypeProvider;

    private Map<Integer, Class<? extends Action>> commands = null;

    @Override
    public Action getCommand(Integer requestType) {
        if (requestType == null)
            return null;
        Class<? extends Action> clazz = commands.get(requestType);
        if (clazz == null)
            return null;
        try {
            return createActionBean(clazz);
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    private Action createActionBean(Class<? extends Action> clazz) {
        BeanDefinition bd = new RootBeanDefinition(clazz);
        ((BeanDefinitionRegistry)context).registerBeanDefinition(clazz.getSimpleName(), bd);
        return context.getBean(clazz);
    }

    @Override
    public Map<Integer, Class<? extends Action>> getAllCommands() {
        return commands;
    }

    @PostConstruct
    private void init() {
        commands = new HashMap<>();

        for (Class<?> c : actionsTypeProvider.getActionTypes()) {
            try {
                for (Field field : c.getDeclaredFields()) {
                    if (field.isAnnotationPresent(BindActionToId.class)) {
                        field.setAccessible(true);
                        Integer code = field.getInt(c);
                        Class<? extends Action> handlerClass = field.getAnnotation(BindActionToId.class).value();
                        if (commands.containsKey(code)) {
                            throw new IllegalStateException("can't add " 
                                    + handlerClass 
                                    + " handler for action code. " 
                                    + code 
                                    + "It's already exists for " 
                                    + commands.get(code) 
                                    + " handler");
                        } else {
                            commands.put(field.getInt(c), handlerClass);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("initialization error", e);
            }
        }
    }

}

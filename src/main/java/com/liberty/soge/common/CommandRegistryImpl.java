package com.liberty.soge.common;

import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.Handler;
import com.liberty.soge.register.ActionsTypeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CommandRegistryImpl implements CommandRegistry {

    @Autowired
    private ActionsTypeProvider actionsTypeProvider;

    private Map<Integer, Class<? extends Action>> commands = null;

    @Override
    public Action getCommand(Integer requestType) {
        if (requestType == null)
            return null;
        Class<? extends Action> clazz = commands.get(requestType);
        if (clazz == null)
            return null;
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
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
                    if (field.isAnnotationPresent(Handler.class)) {
                        field.setAccessible(true);
                        Integer code = field.getInt(c);
                        Class<? extends Action> handlerClass = field.getAnnotation(Handler.class).value();
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

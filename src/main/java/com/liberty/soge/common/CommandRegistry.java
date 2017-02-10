package com.liberty.soge.common;


import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.Handler;
import com.liberty.soge.types.SogeMessageType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandRegistry {
    private static Logger logger = Logger.getLogger(CommandRegistry.class.getName());
    private static Map<Integer, Class<? extends Action>> commands = null;

    static {
        commands = new HashMap<>();
        // TODO: need scan all classes and interfaces marked with ActionTypes annotation
        Class<SogeMessageType> clazz = SogeMessageType.class;
        try {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Handler.class))
                    commands.put(field.getInt(clazz), field.getAnnotation(Handler.class).value());
            }
        } catch (Exception e) {
            logger.severe("[CommandRegistry] static initialization error " + e.getMessage());
        }
    }

    public static Action getCommand(Integer requestType) {
        if (requestType == null)
            return null;
        Class<? extends Action> clazz = commands.get(requestType);
        if (clazz == null)
            return null;
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            logger.severe("[CommandRegistry] getCommand method " + e.getMessage());
            return null;
        }
    }

}

package com.liberty.soge.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.Input;
import com.liberty.soge.annotation.Validatable;
import com.liberty.soge.errors.ValidationException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class BodyValidator {
    public boolean validateBody(Action action) {
        try {
            if (action.getClass().isAnnotationPresent(Validatable.class)) {
                extractInput(action);
                // todo: validate
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    private void extractInput(Action action) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = action.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Input.class)) {
                field.setAccessible(true);
                Class<?> validationClass = field.getType();
                field.set(action, getObject(validationClass, action.getRequest().getData()));
            }
        }
    }

    private Object getObject(Class<?> validationClass, Object input) throws IllegalAccessException, InstantiationException {
        ObjectMapper mapper = JsonHelper.getObjectMapper();
        Object object = mapper.convertValue(input, validationClass);
        for (Field field : validationClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                if (field.get(object) == null)
                    throw new ValidationException(field.getName() + " is null");

            }
        }
        return object;
    }
}

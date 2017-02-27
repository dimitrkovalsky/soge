package com.liberty.soge.common;

import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.Input;
import com.liberty.soge.annotation.Validatable;
import com.liberty.soge.errors.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BodyDeserializerImpl implements InputDeserializer {
    
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    
    @Override
    public Action deserializeInput(Action action) {
        try {
            Class<?> clazz = action.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Input.class)) {
                    field.setAccessible(true);
                    Class<?> validationClass = field.getType();
                    field.set(action, getObject(validationClass, action.getRequest().getData()));
                }
            }
        } catch (Throwable t) {
            log.error("can't deserialize body input", t);
            return null;
        }
        return action;
    }

    private Object getObject(Class<?> validationClass, Object input)
            throws IllegalAccessException, InstantiationException {
        ObjectMapper mapper = JsonHelper.getObjectMapper();
        Object object = mapper.convertValue(input, validationClass);
        if (validationClass.isAnnotationPresent(Validatable.class)) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
            if(constraintViolations.size() > 0) {
                throw new ValidationException(constraintViolations.iterator().next().getMessage());
            }
        }
        return object;
    }

}

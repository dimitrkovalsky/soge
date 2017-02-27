package com.liberty.soge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.liberty.soge.register.actions.ActionTypesScanBeanDefinitionRegister;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ActionTypesScanBeanDefinitionRegister.class)
public @interface ActionTypesScan {
    
    String[] packages();
    
}

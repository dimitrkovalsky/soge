package com.liberty.soge.annotation;

import com.liberty.soge.action.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
public @interface Handler {
    public Class<? extends Action> value();
}

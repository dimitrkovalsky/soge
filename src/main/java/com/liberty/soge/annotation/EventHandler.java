package com.liberty.soge.annotation;

import com.liberty.soge.gameword.GameEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface EventHandler {
    Class<? extends GameEvent> value();
}

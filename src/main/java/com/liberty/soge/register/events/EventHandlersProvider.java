package com.liberty.soge.register.events;

import java.lang.reflect.Method;
import java.util.Map;

import com.liberty.soge.gameword.GameEvent;
import com.liberty.soge.util.Pair;

public interface EventHandlersProvider {
    public Map<Class<? extends GameEvent>, Pair<Class<?>, Method>> getEventHandlerTypesMaping();
}

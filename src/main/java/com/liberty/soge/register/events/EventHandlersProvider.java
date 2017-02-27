package com.liberty.soge.register.events;

import java.util.Set;

public interface EventHandlersProvider {
    public Set<Class<?>> getEventHandlers();
}

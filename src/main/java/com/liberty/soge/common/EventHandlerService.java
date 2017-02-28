package com.liberty.soge.common;

import com.liberty.soge.gameword.GameEvent;

public interface EventHandlerService {
    void handle(GameEvent event);
}

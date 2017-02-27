package com.liberty.soge.common;

import com.liberty.soge.gameword.GameEvent;

public interface EventHandler {
    void handle(GameEvent event);
}

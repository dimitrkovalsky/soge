package com.liberty.soge.common;

import com.liberty.soge.action.Action;

import java.util.Map;

public interface CommandRegistry {

    Action getCommand(Integer requestType);

    Map<Integer, Class<? extends Action>> getAllCommands();
}

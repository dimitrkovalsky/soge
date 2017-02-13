package com.liberty.soge.common;

import com.liberty.soge.action.Action;

public interface CommandRegistry {

    Action getCommand(Integer requestType);

}

package com.liberty.soge.common;

import com.liberty.soge.action.Action;

public interface MessageProcessor {
    
    public Action process(String json);
    
}

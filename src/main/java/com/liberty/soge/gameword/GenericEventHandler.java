package com.liberty.soge.gameword;


import org.springframework.beans.factory.annotation.Autowired;

import com.liberty.soge.annotation.EventHandler;
import com.liberty.soge.annotation.EventHandlersType;
import com.liberty.soge.common.MessageProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Slf4j
@EventHandlersType
public class GenericEventHandler {
    
    @Autowired
    private MessageProcessor messageProcessor;
    
    @EventHandler(GameEvent.class)
    public void handleEvent(GameEvent event) {
        log.info("Trying to handle event : " + event);
    }


    @EventHandler(DevelopmentCompletedEvent.class)
    public void handleEvent(DevelopmentCompletedEvent event) {
        log.info("Trying to handle specific event : " + event);
    }


}

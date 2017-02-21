package com.liberty.soge.gameword;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
public interface EventGenerator {
    String SOGE_TRIGGER_GROUP = "soge-triggers";
    String SOGE_EVENTS_GROUP = "soge-events";
    String BEAN_DATA_KEY = "beanData";
    String CLASS_NAME_KEY = "className";

    void generateEvent(GameEvent event, long eventTime);
}

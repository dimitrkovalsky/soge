package com.liberty.soge.gameword;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
public interface EventGenerator {

    void generateEvent(GameEvent event, long eventTime);
}

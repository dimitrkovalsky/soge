package com.liberty.soge.gameword;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@AllArgsConstructor
@Getter
public abstract class GameEvent {
    protected String userId;

    public abstract String getEventId();
}

package com.liberty.soge.gameword;

import lombok.Getter;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Getter
public class DevelopmentStartedEvent extends GameEvent {
    private Long developmentId;

    public DevelopmentStartedEvent(String userId, Long developmentId) {
        super(userId);
        this.developmentId = developmentId;
    }

    public DevelopmentStartedEvent(String userId) {
        super(userId);
    }

    @Override
    public String getEventId() {
        return "dev_" + developmentId + "_usr_" + userId;
    }
}

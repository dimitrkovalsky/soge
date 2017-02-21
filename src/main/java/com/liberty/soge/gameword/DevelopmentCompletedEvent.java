package com.liberty.soge.gameword;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Data
@NoArgsConstructor
public class DevelopmentCompletedEvent extends GameEvent {
    private Long developmentId;

    public DevelopmentCompletedEvent(String userId, Long developmentId) {
        super(userId);
        this.developmentId = developmentId;
    }

    public DevelopmentCompletedEvent(String userId) {
        super(userId);
    }

    @Override
    public String getEventId() {
        return "dev_" + developmentId + "_usr_" + userId;
    }
}

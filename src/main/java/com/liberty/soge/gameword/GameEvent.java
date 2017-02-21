package com.liberty.soge.gameword;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Derived classes should contains default constructor.
 *
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class GameEvent {
    protected String userId;

    @JsonIgnore
    public abstract String getEventId();
}

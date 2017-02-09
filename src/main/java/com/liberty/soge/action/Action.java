package com.liberty.soge.action;

import com.liberty.soge.common.GenericMessage;
import com.liberty.soge.common.GenericResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dmytro_Kovalskyi.
 * @since 09.02.2017.
 */
@Setter
@Getter
public abstract class Action {
    protected GenericMessage request;

    public abstract GenericResponse execute();
}

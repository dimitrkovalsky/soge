package com.liberty.soge.common;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 22.06.2016.
 */
@Data
public class GenericMessage {

    private int messageType;
    private Object data;

}

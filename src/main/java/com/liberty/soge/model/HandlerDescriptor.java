package com.liberty.soge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 17.02.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerDescriptor {
    private Integer messageType;
    private String handlerClass;
    private String handlerFull;
    private String input;
}

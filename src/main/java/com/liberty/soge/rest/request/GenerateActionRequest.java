package com.liberty.soge.rest.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Dmytro_Kovalskyi.
 * @since 22.02.2017.
 */
@Data
public class GenerateActionRequest {
    @NotNull
    private String className;

    @NotNull
    private Integer messageType;
}

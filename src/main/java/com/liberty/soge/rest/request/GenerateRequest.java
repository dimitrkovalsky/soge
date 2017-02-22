package com.liberty.soge.rest.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Dmytro_Kovalskyi.
 * @since 22.02.2017.
 */
@Data
public class GenerateRequest {
    @NotNull
    private String className;
}

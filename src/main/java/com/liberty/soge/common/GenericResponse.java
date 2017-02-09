package com.liberty.soge.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 09.02.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private int messageType;
    private int responseType;
    private int responseCode;
    private Object response;

    public GenericResponse setResponseCode(int requestType) {
        this.responseCode = requestType;
        return this;
    }

    public GenericResponse setResponse(Object response) {
        this.response = response;
        return this;
    }
}

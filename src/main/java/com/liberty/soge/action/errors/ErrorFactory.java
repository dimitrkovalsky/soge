package com.liberty.soge.action.errors;


import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.types.ResponseCode;

public class ErrorFactory {
    public static GenericResponse createResponse(int errorCode, String errorString) {
        GenericResponse response = new GenericResponse();
        response.setResponseCode(ResponseCode.STATUS_FAIL);
        ErrorObject object = new ErrorObject(errorCode, errorString);
        response.setResponse(object);
        return response;
    }
}

package com.liberty.soge.common;


import com.liberty.soge.types.ResponseCode;

public class ResponseFactory {

    public static GenericResponse createResponse() {
        GenericResponse response = null;
        response = new GenericResponse();
        response.setResponseCode(ResponseCode.STATUS_OK);

        return response;
    }

    public static GenericResponse createResponse(GenericMessage request) {
        return createResponse(request, request.getMessageType());
    }

    public static GenericResponse createResponse(GenericMessage request, int responseType) {
        GenericResponse response = createResponse();
        response.setMessageType(request.getMessageType());
        response.setResponseType(responseType);
        return response;
    }
}
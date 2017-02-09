package com.liberty.soge.action.errors;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.types.ResponseErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFailedAction extends Action {

    @Override
    public GenericResponse execute() {
        log.error("Authentication failed ");

        return ErrorFactory.createResponse(ResponseErrorCode.AUTHENTICATION_FAILED, "Authentication failed");
    }
}

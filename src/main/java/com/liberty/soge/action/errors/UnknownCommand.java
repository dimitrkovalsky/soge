package com.liberty.soge.action.errors;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.types.ResponseErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnknownCommand extends Action {

    @Override
    public GenericResponse execute() {
        log.error("Unknown message : " + request.getMessageType());

        return ErrorFactory.createResponse(ResponseErrorCode.UNKNOWN_COMMAND, "Message type is not supported");
    }
}

package com.liberty.soge.action.errors;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.types.ResponseErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MalformedAction extends Action {
    @Override
    public GenericResponse execute() {
        log.error("Malformed action ");

        return ErrorFactory.createResponse(ResponseErrorCode.MALFORMED_COMMAND, "Malformed action");
    }
}

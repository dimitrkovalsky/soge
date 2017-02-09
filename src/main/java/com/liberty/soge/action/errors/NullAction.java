package com.liberty.soge.action.errors;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.types.ResponseErrorCode;

public class NullAction extends Action {

    @Override
    public GenericResponse execute() {

        return ErrorFactory.createResponse(ResponseErrorCode.NULL_COMMAND, "Null action");
    }
}

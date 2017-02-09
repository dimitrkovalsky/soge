package com.liberty.soge.action.test;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.ResponseFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstTestCommand extends Action {

    @Override
    public GenericResponse execute() {
        log.debug("First TEST");

        return ResponseFactory.createResponse(request)
                .setResponse("First test command response object");
    }
}

package com.liberty.soge.action.test;


import org.springframework.beans.factory.annotation.Autowired;

import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.Input;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.ResponseFactory;
import com.liberty.soge.register.actions.ActionTypesProvider;
import com.liberty.soge.types.TestInputType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThirdTestAction extends Action {
    
    @Autowired
    private ActionTypesProvider actionsTypeProvider;
    
	@Input
	private TestInputType message;

    @Override
    public GenericResponse execute() {
        log.info("Second Test");
        return ResponseFactory.createResponse(request).setResponse(new TestObject(message.getMessage()));
    }
}

package com.liberty.soge.action.test;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.ResponseFactory;

public class SecondTestAction extends Action {

    @Override
    public GenericResponse execute() {
        System.out.println("Second Test");

        return ResponseFactory.createResponse(request).setResponse(new TestObject());
    }
}

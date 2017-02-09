package com.liberty.soge.action.test;


import com.liberty.soge.action.Action;
import com.liberty.soge.common.GenericResponse;
import com.liberty.soge.common.ResponseFactory;
import lombok.Getter;

import java.util.Random;


public class RandomTestAction extends Action {
    @Override
    public GenericResponse execute() {
        System.out.println("RandomTestCommand invoked");
        int resp = Math.abs(new Random().nextInt()) % 3 + 1;
        if (resp == 1)
            return ResponseFactory.createResponse(request, resp).setResponse("String response");
        if (resp == 2)
            return ResponseFactory.createResponse(request, resp).setResponse(100500);

        return ResponseFactory.createResponse(request, resp).setResponse(new SimpleObject());
    }
}

@Getter
class SimpleObject {

    private int value;
    private String string = "Simple string";

    SimpleObject() {
        value = Math.abs(new Random().nextInt() % 1000);
    }

}


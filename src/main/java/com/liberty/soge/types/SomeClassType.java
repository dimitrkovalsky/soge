package com.liberty.soge.types;

import com.liberty.soge.action.test.SecondTestAction;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.Handler;

@ActionTypes
public class SomeClassType {
    
    @Handler(SecondTestAction.class)
    static int RT_TEST_2 = 10;
    
}
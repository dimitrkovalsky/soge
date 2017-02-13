package com.liberty.soge.types;


import com.liberty.soge.action.test.FirstTestCommand;
import com.liberty.soge.action.test.RandomTestAction;
import com.liberty.soge.action.test.SecondTestAction;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.Handler;

@ActionTypes
public interface SogeMessageType {
    
    @Handler(FirstTestCommand.class)
    int RT_TEST_1 = 1;

    @Handler(SecondTestAction.class)
    int RT_TEST_2 = 2;

    @Handler(RandomTestAction.class)
    int RT_RANDOM_TEST = 3;


    int SYSTEM_NOTIFICATION = 700;
    int SYSTEM_ERROR_NOTIFICATION = 701;

}
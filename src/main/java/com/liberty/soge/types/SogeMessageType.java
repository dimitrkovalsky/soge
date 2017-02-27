package com.liberty.soge.types;


import com.liberty.soge.action.test.FirstTestCommand;
import com.liberty.soge.action.test.RandomTestAction;
import com.liberty.soge.action.test.SecondTestAction;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.BindActionToId;

@ActionTypes
public interface SogeMessageType {
    
    @BindActionToId(FirstTestCommand.class)
    int RT_TEST_1 = 1;

    @BindActionToId(SecondTestAction.class)
    int RT_TEST_2 = 2;

    @BindActionToId(RandomTestAction.class)
    int RT_RANDOM_TEST = 3;


    int SYSTEM_NOTIFICATION = 700;
    int SYSTEM_ERROR_NOTIFICATION = 701;

}
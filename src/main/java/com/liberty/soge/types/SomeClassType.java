package com.liberty.soge.types;

import com.liberty.soge.action.ThirdTestAction;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.Handler;

@ActionTypes
public class SomeClassType {
  @Handler(ThirdTestAction.class)
  static int RT_TEST_2 = 5;
    
}

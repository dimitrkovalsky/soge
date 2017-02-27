package com.liberty.soge.types;

import com.liberty.soge.action.test.ThirdTestAction;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.BindActionToId;

@ActionTypes
public class SomeClassType {
  @BindActionToId(ThirdTestAction.class)
  static int RT_TEST_2 = 5;
    
}

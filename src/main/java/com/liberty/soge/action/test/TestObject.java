package com.liberty.soge.action.test;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 09.02.2017.
 */
@Data
public class TestObject {

    private int intValue;
    private float floatValue;
    private String stringValue;
    private String hiddenString;

    public TestObject(String message) {
        intValue = 100;
        floatValue = 3.14f;
        stringValue = message;
        hiddenString = "Hidden String";
    }
}


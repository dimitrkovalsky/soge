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

    public TestObject() {
        intValue = 100;
        floatValue = 3.14f;
        stringValue = "Test string value";
        hiddenString = "Hidden String";
    }
}


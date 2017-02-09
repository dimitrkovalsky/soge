package com.liberty.soge.action.errors;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {
    private int errorCode;
    private String message;
}

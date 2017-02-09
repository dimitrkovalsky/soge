package com.liberty.soge.types;


public interface ResponseErrorCode {
    int INVALID_REQUEST = 300;
    int UNKNOWN_COMMAND = 301;
    int VALIDATION_ERROR = 302;
    int NULL_COMMAND = 303;
    int MALFORMED_COMMAND = 304;
    int APPLICATION_EXCEPTION = 305;
    int AUTHENTICATION_FAILED = 310;
}

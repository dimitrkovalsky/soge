package com.liberty.soge.errors;

public class CodeGenerationException extends ApplicationException {

  public CodeGenerationException(String message) {
    super(ErrorCode.CODE_GENERATION_ERROR, message);
  }

  public CodeGenerationException(String message, Exception e) {
    super(ErrorCode.CODE_GENERATION_ERROR, message, e);
  }
}

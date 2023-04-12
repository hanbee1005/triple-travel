package com.triple.task.travel.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends CanNotProceedException {
    private ExceptionType exceptionType;
    private String message;

    public BusinessException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public BusinessException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = exceptionType.getMessage();
    }

    @Override
    public int getStatus() {
        return exceptionType.getStatus();
    }

    @Override
    public String getErrorCode() {
        return exceptionType.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

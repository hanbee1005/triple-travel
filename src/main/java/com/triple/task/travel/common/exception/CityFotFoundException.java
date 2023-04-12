package com.triple.task.travel.common.exception;

public class CityFotFoundException extends BusinessException {
    private static final ExceptionType EXCEPTION_TYPE = ExceptionType.NOT_FOUND_CITY;
    private static final String DEFAULT_MESSAGE = "해당 Id 의 도시가 존재하지 않습니다.";

    public CityFotFoundException() {
        super(EXCEPTION_TYPE, DEFAULT_MESSAGE);
    }
}

package com.triple.task.travel.common.exception;

public class TripNotFoundException extends BusinessException {
    private static final ExceptionType EXCEPTION_TYPE = ExceptionType.NOT_FOUND_TRIP;
    private static final String DEFAULT_MESSAGE = "해당 Id 의 여행이 존재하지 않습니다.";

    public TripNotFoundException() {
        super(EXCEPTION_TYPE, DEFAULT_MESSAGE);
    }
}

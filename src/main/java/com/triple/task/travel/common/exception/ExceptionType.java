package com.triple.task.travel.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionType {
    // standard
    BAD_REQUEST("301", HttpStatus.BAD_REQUEST.value(), "exception.standard.badRequest"),
    NOT_FOUND("304", HttpStatus.NOT_FOUND.value(), "exception.standard.notFound"),
    UNEXPECTED("305", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.standard.unexpected"),

    // business
    INVALID_REQUEST("401", HttpStatus.BAD_REQUEST.value(), "exception.common.validation.request"),
    NOT_FOUND_CITY("410", HttpStatus.BAD_REQUEST.value(), "exception.business.notFound.city"),
    DELETE_CITY_FAIL("411", HttpStatus.BAD_REQUEST.value(), "exception.business.fail.delete.city"),
    NOT_FOUND_TRIP("420", HttpStatus.BAD_REQUEST.value(), "exception.business.notFound.trip")
    ;

    private final String code;
    private final int status;
    private final String message;

    public String getCode() {
        return status + code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

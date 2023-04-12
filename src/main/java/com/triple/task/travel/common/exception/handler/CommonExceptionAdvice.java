package com.triple.task.travel.common.exception.handler;

import com.triple.task.travel.common.exception.BusinessException;
import com.triple.task.travel.common.model.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.triple.task.travel.common.exception.ExceptionType.*;
import static com.triple.task.travel.common.constant.ValidatorCodeConstants.*;

@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {
    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<CommonErrorResponse> handleException(BusinessException exception) {
        log.error("Business Exception occurred.");
        return ResponseEntity.status(exception.getStatus()).body(CommonErrorResponse.of(exception));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<CommonErrorResponse> handleException(final Exception e) {
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            String message = handleBindException(bindException);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(CommonErrorResponse.of(new BusinessException(INVALID_REQUEST, message)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonErrorResponse.of(new BusinessException(UNEXPECTED, e.getMessage())));
        }
    }

    private String handleBindException(final BindException exception) {
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrorList) {
            if (NOT_EMPTY.equals(error.getCode()) || NOT_BLANK.equals(error.getCode())) {
                return error.getField() + "은(는) " + error.getDefaultMessage();
            }
        }

        return Strings.EMPTY;
    }
}

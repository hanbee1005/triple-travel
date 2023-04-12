package com.triple.task.travel.common.model;

import com.triple.task.travel.common.exception.CanNotProceedException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonErrorResponse {
    @NonNull
    private String code;
    @NonNull
    private String message;

    public static CommonErrorResponse of(CanNotProceedException exception) {
        return new CommonErrorResponse(exception.getErrorCode(), exception.getMessage());
    }
}

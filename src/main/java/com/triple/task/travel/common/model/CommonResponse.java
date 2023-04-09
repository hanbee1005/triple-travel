package com.triple.task.travel.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResponse<T> {
    @NonNull
    final Integer code;

    @NonNull
    final String message;

    final T data;

    private CommonResponse(@NonNull Integer code, @NonNull String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> ok(final T data) {
        return new CommonResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static CommonResponse ok() {
        return ok(null);
    }
}

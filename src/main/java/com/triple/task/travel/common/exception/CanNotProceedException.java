package com.triple.task.travel.common.exception;

public abstract class CanNotProceedException extends RuntimeException {
    public CanNotProceedException() { super(); }
    public CanNotProceedException(String message) { super(message); }

    public abstract int getStatus();
    public abstract String getErrorCode();
    public abstract String getMessage();
}

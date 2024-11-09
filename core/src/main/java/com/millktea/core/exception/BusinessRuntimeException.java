package com.millktea.core.exception;

import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {

    private final RuntimeErrorCode errorCode;

    public BusinessRuntimeException(RuntimeErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
    }
}

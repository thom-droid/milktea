package com.millktea.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RuntimeErrorCode {

    BUSINESS_NOT_FOUND(1000, "Business not found"),
    BUSINESS_ALREADY_EXISTS(1001, "Business already exists"),
    BUSINESS_NOT_ACTIVE(1002, "Business not active"),
    ;

    final int code;
    final String message;

}

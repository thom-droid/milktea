package com.millktea.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RuntimeErrorCode {

    BUSINESS_NOT_FOUND(1000, "Business not found"),
    BUSINESS_ALREADY_EXISTS(1001, "Business already exists"),
    BUSINESS_NOT_ACTIVE(1002, "Business not active"),

    USER_NOT_FOUND(2000, "User not found"),
    USER_ALREADY_EXISTS(2001, "User already exists"),
    USER_NOT_ACTIVE(2002, "User not active"),

    FILE_NOT_FOUND(9000, "File not found"),
    FILE_UPLOAD_FAILED(9001, "File upload failed"),
    FILE_CREATING_DIRECTORY_FAILED(9002, "Error while creating directory")
    ;

    final int code;
    final String message;

}

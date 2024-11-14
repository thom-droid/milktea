package com.millktea.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RuntimeErrorCode {

    BUSINESS_NOT_FOUND(1000, "Business not found"),
    BUSINESS_ALREADY_EXISTS(1001, "Business already exists"),
    BUSINESS_NOT_ACTIVE(1002, "Business not active"),
    BUSINESS_HAS_NO_REPRESENTATIVE_USER(1003, "Business has no representative user"),
    BUSINESS_ALREADY_HAS_REPRESENTATIVE_USER(1004, "Business already has representative user"),

    USER_NOT_FOUND(2000, "User not found"),
    USER_ALREADY_EXISTS(2001, "User already exists"),
    USER_NOT_ACTIVE(2002, "User not active"),
    USER_NOT_REPRESENTATIVE(2003, "User is not representative"),

    FILE_NOT_FOUND(9000, "File not found"),
    FILE_UPLOAD_FAILED(9001, "File upload failed"),
    FILE_CREATING_DIRECTORY_FAILED(9002, "Error while creating directory")
    ;

    final int code;
    final String message;

}

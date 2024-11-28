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
    BUSINESS_DOES_NOT_CONTAIN_USER(1005, "Business does not contain user"),

    USER_NOT_FOUND(2000, "User not found"),
    USER_ALREADY_EXISTS(2001, "User already exists"),
    USER_NOT_ACTIVE(2002, "User not active"),
    USER_NOT_REPRESENTATIVE(2003, "User is not representative"),
    USER_HAS_NO_PRIVILEGES(2005, "User has no privileges"),
    USER_REPRESENTATIVE_MUST_NOT_BE_UPDATED(2006, "User representative must not be updated"),

    NO_PRIVILEGES_SELECTED(3000, "No privileges selected"),

    FILE_NOT_FOUND(9000, "File not found"),
    FILE_UPLOAD_FAILED(9001, "File upload failed"),
    FILE_CREATING_DIRECTORY_FAILED(9002, "Error while creating directory")
    ;

    final int code;
    final String message;

}

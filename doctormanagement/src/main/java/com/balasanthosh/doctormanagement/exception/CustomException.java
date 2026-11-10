package com.balasanthosh.doctormanagement.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private int status;
    private String errorCode;
    private Map<String, String> exceptions;

    public CustomException(int status, String errorCode, Map<String, String> exceptions) {
        this.status = status;
        this.errorCode = errorCode;
        this.exceptions = exceptions;
    }

}

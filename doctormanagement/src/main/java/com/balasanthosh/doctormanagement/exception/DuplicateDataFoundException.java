package com.balasanthosh.doctormanagement.exception;

import java.util.Map;

public class DuplicateDataFoundException extends CustomException {
    public DuplicateDataFoundException(Map<String, String> exceptions) {
        super(409, "conflict", exceptions);
    }

}

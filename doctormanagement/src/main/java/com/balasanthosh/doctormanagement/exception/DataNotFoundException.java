package com.balasanthosh.doctormanagement.exception;

import java.util.Map;

public class DataNotFoundException extends CustomException {
    public DataNotFoundException(Map<String, String> exceptions) {
        super(404, "not found", exceptions);
    }

}

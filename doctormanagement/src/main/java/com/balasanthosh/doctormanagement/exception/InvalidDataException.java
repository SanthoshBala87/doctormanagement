package com.balasanthosh.doctormanagement.exception;

import java.util.Map;

public class InvalidDataException extends CustomException {
    public InvalidDataException(Map<String, String> exceptions) {
        super(400, "bad request", exceptions);
    }

}

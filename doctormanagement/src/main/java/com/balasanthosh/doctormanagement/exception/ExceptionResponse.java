package com.balasanthosh.doctormanagement.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonPropertyOrder({"timestamp", "status", "errorCode", "exceptions", "requestDTOExceptions"})
public class ExceptionResponse {
    private Instant timestamp;
    private int status;
    private String errorCode;
    private Map<String, String> exceptions;
    private Map<String, List<String>> requestDTOExceptions;

}

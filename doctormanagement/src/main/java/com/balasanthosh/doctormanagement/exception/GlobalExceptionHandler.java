package com.balasanthosh.doctormanagement.exception;

/**
 * Here "exceptionMessage" is to know which error occured in operations. This is for me to understant, not to users!
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setStatus(500);
        exceptionResponse.setErrorCode("unexpected");
        exceptionResponse.setExceptions(Map.of(
                "exception", "Something went wrong !",
                "exceptionMessage", "" + exception.getClass().getSimpleName() + " -> " + exception.getMessage()

        ));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(DuplicateDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateDataFoundException(DuplicateDataFoundException duplicateDataFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setStatus(duplicateDataFoundException.getStatus());
        exceptionResponse.setErrorCode(duplicateDataFoundException.getErrorCode());
        Map<String, String> exceptions = duplicateDataFoundException.getExceptions();
        exceptions.put("exceptionMessage", "DuplicateDataFoundException  -> " + duplicateDataFoundException.getMessage());
        exceptionResponse.setExceptions(exceptions);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setStatus(dataNotFoundException.getStatus());
        exceptionResponse.setErrorCode(dataNotFoundException.getErrorCode());
        Map<String, String> exceptions = dataNotFoundException.getExceptions();
        exceptions.put("exceptionMessage", "DataNotFoundException -> " + dataNotFoundException.getMessage());
        exceptionResponse.setExceptions(exceptions);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDataException(InvalidDataException invalidDataException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setStatus(invalidDataException.getStatus());
        exceptionResponse.setErrorCode(invalidDataException.getErrorCode());
        Map<String, String> exceptions = invalidDataException.getExceptions();
        exceptions.put("exceptionMessage", "InvalidDataException -> " + invalidDataException.getMessage());
        exceptionResponse.setExceptions(exceptions);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setStatus(400);
        exceptionResponse.setErrorCode("bad request");
        Map<String, List<String>> exceptions = new LinkedHashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((fieldErrors) -> {
            exceptions.computeIfAbsent(fieldErrors.getField(), (key) -> new ArrayList<>()).add(fieldErrors.getDefaultMessage());
        });
        exceptions.put("exceptionMessage", List.of("MethodArgumentNotValidException -> " + methodArgumentNotValidException.getMessage()));
        exceptionResponse.setRequestDTOExceptions(exceptions);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}

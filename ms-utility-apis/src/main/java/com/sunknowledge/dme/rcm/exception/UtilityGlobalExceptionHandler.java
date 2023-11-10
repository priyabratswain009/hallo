package com.sunknowledge.dme.rcm.exception;

import com.sunknowledge.dme.rcm.application.handler.ErrorDTO;
import com.sunknowledge.dme.rcm.application.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerWebInputException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class UtilityGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNullPointerException(NullPointerException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder()
            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message("Unexpected error due to value is not exists!")
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        String errJsonStr = JSONValue.toJSONString(errors);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(errJsonStr)
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ServerWebInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ServerWebInputException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }
}

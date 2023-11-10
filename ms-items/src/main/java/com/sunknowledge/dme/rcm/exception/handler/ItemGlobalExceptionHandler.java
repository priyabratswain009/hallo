package com.sunknowledge.dme.rcm.exception.handler;

import com.sunknowledge.dme.rcm.application.handler.ErrorDTO;
import com.sunknowledge.dme.rcm.application.handler.GlobalExceptionHandler;
import com.sunknowledge.dme.rcm.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import javax.management.InvalidAttributeValueException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ItemGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ItemNotFoundException itemNotFoundException) {
        log.error(itemNotFoundException.getMessage(), itemNotFoundException);
        return ErrorDTO.builder()
            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(itemNotFoundException.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
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

    @ResponseBody
    @ExceptionHandler(value = {InvalidAttributeValueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(InvalidAttributeValueException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {InputMismatchException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(InputMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ResponseStatusException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message("Input should not be blank")
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

}

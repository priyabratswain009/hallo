package com.sunknowledge.dme.rcm.exception.handler;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.application.handler.ErrorDTO;
import com.sunknowledge.dme.rcm.application.handler.GlobalExceptionHandler;
import com.sunknowledge.dme.rcm.exception.PatientNotFoundException;
import com.sunknowledge.dme.rcm.exception.PatientDomainException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class PatientGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {PatientDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(PatientDomainException domainException) {
        log.error(domainException.getMessage(), domainException);
        return CustomErrorDTO.builder()
            .status(false)
            .message(domainException.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {PatientNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDTO handleException(PatientNotFoundException notFoundException) {
        log.error(notFoundException.getMessage(), notFoundException);
        return CustomErrorDTO.builder()
            .status(false)
            .message(notFoundException.getMessage())
            .data(null)
//            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ServerWebInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(ServerWebInputException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {WebExchangeBindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(WebExchangeBindException ex) {
        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        String errJsonStr = JSONValue.toJSONString(errors);
        return CustomErrorDTO.builder()
            .status(false)
            .message(errors)
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {InvalidAccessTokenException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(InvalidAccessTokenException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(ResponseStatusException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(HttpClientErrorException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpServerErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(HttpServerErrorException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ResourceAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(ResourceAccessException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDTO handleException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return CustomErrorDTO.builder()
            .status(false)
            .message(ex.getMessage())
            .data(null)
//            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }
}

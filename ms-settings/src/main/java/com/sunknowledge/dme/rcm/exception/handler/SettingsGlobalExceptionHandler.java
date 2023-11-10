package com.sunknowledge.dme.rcm.exception.handler;

import com.sunknowledge.dme.rcm.application.handler.ErrorDTO;
import com.sunknowledge.dme.rcm.application.handler.GlobalExceptionHandler;
import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.InvalidAttributeValueException;
import java.util.InputMismatchException;

@Slf4j
@ControllerAdvice
public class SettingsGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {BranchNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(BranchNotFoundException e) {
        log.error(e.getMessage(), e);
        return ErrorDTO.builder()
            .code(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(e.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {InvalidAttributeValueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(InvalidAttributeValueException e) {
        log.error(e.getMessage(), e);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(e.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {InputMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(InputMismatchException e) {
        log.error(e.getMessage(), e);
        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(e.getMessage())
            .build();
    }
}

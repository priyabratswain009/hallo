package com.sunknowledge.dme.rcm.exception;

import com.sunknowledge.dme.rcm.domain.exception.DomainException;

public class PatientDomainException extends DomainException {
    public PatientDomainException(String message) {
        super(message);
    }

    public PatientDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

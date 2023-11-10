package com.sunknowledge.dme.rcm.exception;

import com.sunknowledge.dme.rcm.domain.exception.DomainException;

public class SalesOrderDomainException extends DomainException {
    public SalesOrderDomainException(String message) {
        super(message);
    }

    public SalesOrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.sunknowledge.dme.rcm.exception;

import com.sunknowledge.dme.rcm.domain.exception.DomainException;

public class ItemNotFoundException extends DomainException {
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.sunknowledge.dme.rcm.exception;

import com.sunknowledge.dme.rcm.domain.exception.DomainException;

public class BranchNotFoundException extends DomainException {
    public BranchNotFoundException(String message) {
        super(message);
    }

    public BranchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsAuditLogMapperTest {

    private InvoicePostingDetailsAuditLogMapper invoicePostingDetailsAuditLogMapper;

    @BeforeEach
    public void setUp() {
        invoicePostingDetailsAuditLogMapper = new InvoicePostingDetailsAuditLogMapperImpl();
    }
}

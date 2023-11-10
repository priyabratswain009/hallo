package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceLineItemDetailsMapperTest {

    private InvoiceLineItemDetailsMapper invoiceLineItemDetailsMapper;

    @BeforeEach
    public void setUp() {
        invoiceLineItemDetailsMapper = new InvoiceLineItemDetailsMapperImpl();
    }
}

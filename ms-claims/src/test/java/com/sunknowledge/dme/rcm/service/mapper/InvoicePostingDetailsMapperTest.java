package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsMapperTest {

    private InvoicePostingDetailsMapper invoicePostingDetailsMapper;

    @BeforeEach
    public void setUp() {
        invoicePostingDetailsMapper = new InvoicePostingDetailsMapperImpl();
    }
}

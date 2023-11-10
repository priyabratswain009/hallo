package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceDetailsAuditLogMapperTest {

    private PriceDetailsAuditLogMapper priceDetailsAuditLogMapper;

    @BeforeEach
    public void setUp() {
        priceDetailsAuditLogMapper = new PriceDetailsAuditLogMapperImpl();
    }
}

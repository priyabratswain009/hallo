package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaxZoneAuditLogMapperTest {

    private TaxZoneAuditLogMapper taxZoneAuditLogMapper;

    @BeforeEach
    public void setUp() {
        taxZoneAuditLogMapper = new TaxZoneAuditLogMapperImpl();
    }
}

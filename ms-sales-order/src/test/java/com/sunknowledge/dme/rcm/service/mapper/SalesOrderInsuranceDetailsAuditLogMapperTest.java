package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalesOrderInsuranceDetailsAuditLogMapperTest {

    private SalesOrderInsuranceDetailsAuditLogMapper salesOrderInsuranceDetailsAuditLogMapper;

    @BeforeEach
    public void setUp() {
        salesOrderInsuranceDetailsAuditLogMapper = new SalesOrderInsuranceDetailsAuditLogMapperImpl();
    }
}

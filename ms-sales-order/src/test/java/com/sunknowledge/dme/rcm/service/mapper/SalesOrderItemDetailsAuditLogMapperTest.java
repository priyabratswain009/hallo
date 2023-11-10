package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalesOrderItemDetailsAuditLogMapperTest {

    private SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper;

    @BeforeEach
    public void setUp() {
        salesOrderItemDetailsAuditLogMapper = new SalesOrderItemDetailsAuditLogMapperImpl();
    }
}

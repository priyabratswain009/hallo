package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalesOrderClassificationAuditLogMapperTest {

    private SalesOrderClassificationAuditLogMapper salesOrderClassificationAuditLogMapper;

    @BeforeEach
    public void setUp() {
        salesOrderClassificationAuditLogMapper = new SalesOrderClassificationAuditLogMapperImpl();
    }
}

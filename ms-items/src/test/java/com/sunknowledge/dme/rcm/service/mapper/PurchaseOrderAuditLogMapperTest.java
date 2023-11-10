package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOrderAuditLogMapperTest {

    private PurchaseOrderAuditLogMapper purchaseOrderAuditLogMapper;

    @BeforeEach
    public void setUp() {
        purchaseOrderAuditLogMapper = new PurchaseOrderAuditLogMapperImpl();
    }
}

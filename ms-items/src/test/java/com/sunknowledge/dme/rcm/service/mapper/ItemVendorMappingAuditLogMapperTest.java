package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemVendorMappingAuditLogMapperTest {

    private ItemVendorMappingAuditLogMapper itemVendorMappingAuditLogMapper;

    @BeforeEach
    public void setUp() {
        itemVendorMappingAuditLogMapper = new ItemVendorMappingAuditLogMapperImpl();
    }
}

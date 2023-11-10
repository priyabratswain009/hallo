package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemSerialNumberAuditLogMapperTest {

    private ItemSerialNumberAuditLogMapper itemSerialNumberAuditLogMapper;

    @BeforeEach
    public void setUp() {
        itemSerialNumberAuditLogMapper = new ItemSerialNumberAuditLogMapperImpl();
    }
}

package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StopReasonMasterAuditLogMapperTest {

    private StopReasonMasterAuditLogMapper stopReasonMasterAuditLogMapper;

    @BeforeEach
    public void setUp() {
        stopReasonMasterAuditLogMapper = new StopReasonMasterAuditLogMapperImpl();
    }
}

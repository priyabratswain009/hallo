package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoteReasonMasterAuditLogMapperTest {

    private NoteReasonMasterAuditLogMapper noteReasonMasterAuditLogMapper;

    @BeforeEach
    public void setUp() {
        noteReasonMasterAuditLogMapper = new NoteReasonMasterAuditLogMapperImpl();
    }
}

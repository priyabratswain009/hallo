package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BranchItemLocationMapAuditLogMapperTest {

    private BranchItemLocationMapAuditLogMapper branchItemLocationMapAuditLogMapper;

    @BeforeEach
    public void setUp() {
        branchItemLocationMapAuditLogMapper = new BranchItemLocationMapAuditLogMapperImpl();
    }
}

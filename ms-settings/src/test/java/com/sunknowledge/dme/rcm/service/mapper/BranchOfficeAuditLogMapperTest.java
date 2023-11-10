package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BranchOfficeAuditLogMapperTest {

    private BranchOfficeAuditLogMapper branchOfficeAuditLogMapper;

    @BeforeEach
    public void setUp() {
        branchOfficeAuditLogMapper = new BranchOfficeAuditLogMapperImpl();
    }
}

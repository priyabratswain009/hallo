package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchGroupAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchGroupAuditLogDTO.class);
        BranchGroupAuditLogDTO branchGroupAuditLogDTO1 = new BranchGroupAuditLogDTO();
        branchGroupAuditLogDTO1.setId(1L);
        BranchGroupAuditLogDTO branchGroupAuditLogDTO2 = new BranchGroupAuditLogDTO();
        assertThat(branchGroupAuditLogDTO1).isNotEqualTo(branchGroupAuditLogDTO2);
        branchGroupAuditLogDTO2.setId(branchGroupAuditLogDTO1.getId());
        assertThat(branchGroupAuditLogDTO1).isEqualTo(branchGroupAuditLogDTO2);
        branchGroupAuditLogDTO2.setId(2L);
        assertThat(branchGroupAuditLogDTO1).isNotEqualTo(branchGroupAuditLogDTO2);
        branchGroupAuditLogDTO1.setId(null);
        assertThat(branchGroupAuditLogDTO1).isNotEqualTo(branchGroupAuditLogDTO2);
    }
}

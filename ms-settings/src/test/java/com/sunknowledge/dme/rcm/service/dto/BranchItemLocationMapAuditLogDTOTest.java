package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchItemLocationMapAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchItemLocationMapAuditLogDTO.class);
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO1 = new BranchItemLocationMapAuditLogDTO();
        branchItemLocationMapAuditLogDTO1.setId(1L);
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO2 = new BranchItemLocationMapAuditLogDTO();
        assertThat(branchItemLocationMapAuditLogDTO1).isNotEqualTo(branchItemLocationMapAuditLogDTO2);
        branchItemLocationMapAuditLogDTO2.setId(branchItemLocationMapAuditLogDTO1.getId());
        assertThat(branchItemLocationMapAuditLogDTO1).isEqualTo(branchItemLocationMapAuditLogDTO2);
        branchItemLocationMapAuditLogDTO2.setId(2L);
        assertThat(branchItemLocationMapAuditLogDTO1).isNotEqualTo(branchItemLocationMapAuditLogDTO2);
        branchItemLocationMapAuditLogDTO1.setId(null);
        assertThat(branchItemLocationMapAuditLogDTO1).isNotEqualTo(branchItemLocationMapAuditLogDTO2);
    }
}

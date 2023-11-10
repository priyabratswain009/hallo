package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchItemLocationMapAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchItemLocationMapAuditLog.class);
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog1 = new BranchItemLocationMapAuditLog();
        branchItemLocationMapAuditLog1.setId(1L);
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog2 = new BranchItemLocationMapAuditLog();
        branchItemLocationMapAuditLog2.setId(branchItemLocationMapAuditLog1.getId());
        assertThat(branchItemLocationMapAuditLog1).isEqualTo(branchItemLocationMapAuditLog2);
        branchItemLocationMapAuditLog2.setId(2L);
        assertThat(branchItemLocationMapAuditLog1).isNotEqualTo(branchItemLocationMapAuditLog2);
        branchItemLocationMapAuditLog1.setId(null);
        assertThat(branchItemLocationMapAuditLog1).isNotEqualTo(branchItemLocationMapAuditLog2);
    }
}

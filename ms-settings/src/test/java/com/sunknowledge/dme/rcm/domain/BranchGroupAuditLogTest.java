package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchGroupAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchGroupAuditLog.class);
        BranchGroupAuditLog branchGroupAuditLog1 = new BranchGroupAuditLog();
        branchGroupAuditLog1.setId(1L);
        BranchGroupAuditLog branchGroupAuditLog2 = new BranchGroupAuditLog();
        branchGroupAuditLog2.setId(branchGroupAuditLog1.getId());
        assertThat(branchGroupAuditLog1).isEqualTo(branchGroupAuditLog2);
        branchGroupAuditLog2.setId(2L);
        assertThat(branchGroupAuditLog1).isNotEqualTo(branchGroupAuditLog2);
        branchGroupAuditLog1.setId(null);
        assertThat(branchGroupAuditLog1).isNotEqualTo(branchGroupAuditLog2);
    }
}

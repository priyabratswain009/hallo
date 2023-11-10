package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchOfficeAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchOfficeAuditLog.class);
        BranchOfficeAuditLog branchOfficeAuditLog1 = new BranchOfficeAuditLog();
        branchOfficeAuditLog1.setId(1L);
        BranchOfficeAuditLog branchOfficeAuditLog2 = new BranchOfficeAuditLog();
        branchOfficeAuditLog2.setId(branchOfficeAuditLog1.getId());
        assertThat(branchOfficeAuditLog1).isEqualTo(branchOfficeAuditLog2);
        branchOfficeAuditLog2.setId(2L);
        assertThat(branchOfficeAuditLog1).isNotEqualTo(branchOfficeAuditLog2);
        branchOfficeAuditLog1.setId(null);
        assertThat(branchOfficeAuditLog1).isNotEqualTo(branchOfficeAuditLog2);
    }
}

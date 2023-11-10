package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchInsuranceMapAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchInsuranceMapAuditLog.class);
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog1 = new BranchInsuranceMapAuditLog();
        branchInsuranceMapAuditLog1.setId(1L);
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog2 = new BranchInsuranceMapAuditLog();
        branchInsuranceMapAuditLog2.setId(branchInsuranceMapAuditLog1.getId());
        assertThat(branchInsuranceMapAuditLog1).isEqualTo(branchInsuranceMapAuditLog2);
        branchInsuranceMapAuditLog2.setId(2L);
        assertThat(branchInsuranceMapAuditLog1).isNotEqualTo(branchInsuranceMapAuditLog2);
        branchInsuranceMapAuditLog1.setId(null);
        assertThat(branchInsuranceMapAuditLog1).isNotEqualTo(branchInsuranceMapAuditLog2);
    }
}

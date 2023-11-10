package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceGroupMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceGroupMasterAuditLog.class);
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog1 = new InsuranceGroupMasterAuditLog();
        insuranceGroupMasterAuditLog1.setId(1L);
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog2 = new InsuranceGroupMasterAuditLog();
        insuranceGroupMasterAuditLog2.setId(insuranceGroupMasterAuditLog1.getId());
        assertThat(insuranceGroupMasterAuditLog1).isEqualTo(insuranceGroupMasterAuditLog2);
        insuranceGroupMasterAuditLog2.setId(2L);
        assertThat(insuranceGroupMasterAuditLog1).isNotEqualTo(insuranceGroupMasterAuditLog2);
        insuranceGroupMasterAuditLog1.setId(null);
        assertThat(insuranceGroupMasterAuditLog1).isNotEqualTo(insuranceGroupMasterAuditLog2);
    }
}

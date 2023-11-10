package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceMasterAuditLog.class);
        InsuranceMasterAuditLog insuranceMasterAuditLog1 = new InsuranceMasterAuditLog();
        insuranceMasterAuditLog1.setId(1L);
        InsuranceMasterAuditLog insuranceMasterAuditLog2 = new InsuranceMasterAuditLog();
        insuranceMasterAuditLog2.setId(insuranceMasterAuditLog1.getId());
        assertThat(insuranceMasterAuditLog1).isEqualTo(insuranceMasterAuditLog2);
        insuranceMasterAuditLog2.setId(2L);
        assertThat(insuranceMasterAuditLog1).isNotEqualTo(insuranceMasterAuditLog2);
        insuranceMasterAuditLog1.setId(null);
        assertThat(insuranceMasterAuditLog1).isNotEqualTo(insuranceMasterAuditLog2);
    }
}

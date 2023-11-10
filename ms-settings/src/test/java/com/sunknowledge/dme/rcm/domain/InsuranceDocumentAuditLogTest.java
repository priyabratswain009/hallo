package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceDocumentAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceDocumentAuditLog.class);
        InsuranceDocumentAuditLog insuranceDocumentAuditLog1 = new InsuranceDocumentAuditLog();
        insuranceDocumentAuditLog1.setId(1L);
        InsuranceDocumentAuditLog insuranceDocumentAuditLog2 = new InsuranceDocumentAuditLog();
        insuranceDocumentAuditLog2.setId(insuranceDocumentAuditLog1.getId());
        assertThat(insuranceDocumentAuditLog1).isEqualTo(insuranceDocumentAuditLog2);
        insuranceDocumentAuditLog2.setId(2L);
        assertThat(insuranceDocumentAuditLog1).isNotEqualTo(insuranceDocumentAuditLog2);
        insuranceDocumentAuditLog1.setId(null);
        assertThat(insuranceDocumentAuditLog1).isNotEqualTo(insuranceDocumentAuditLog2);
    }
}

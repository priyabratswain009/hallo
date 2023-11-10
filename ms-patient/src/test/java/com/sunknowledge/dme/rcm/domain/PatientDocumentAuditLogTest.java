package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocumentAuditLog.class);
        PatientDocumentAuditLog patientDocumentAuditLog1 = new PatientDocumentAuditLog();
        patientDocumentAuditLog1.setId(1L);
        PatientDocumentAuditLog patientDocumentAuditLog2 = new PatientDocumentAuditLog();
        patientDocumentAuditLog2.setId(patientDocumentAuditLog1.getId());
        assertThat(patientDocumentAuditLog1).isEqualTo(patientDocumentAuditLog2);
        patientDocumentAuditLog2.setId(2L);
        assertThat(patientDocumentAuditLog1).isNotEqualTo(patientDocumentAuditLog2);
        patientDocumentAuditLog1.setId(null);
        assertThat(patientDocumentAuditLog1).isNotEqualTo(patientDocumentAuditLog2);
    }
}

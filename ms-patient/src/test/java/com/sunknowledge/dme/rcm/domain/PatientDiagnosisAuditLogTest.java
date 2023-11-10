package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDiagnosisAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDiagnosisAuditLog.class);
        PatientDiagnosisAuditLog patientDiagnosisAuditLog1 = new PatientDiagnosisAuditLog();
        patientDiagnosisAuditLog1.setId(1L);
        PatientDiagnosisAuditLog patientDiagnosisAuditLog2 = new PatientDiagnosisAuditLog();
        patientDiagnosisAuditLog2.setId(patientDiagnosisAuditLog1.getId());
        assertThat(patientDiagnosisAuditLog1).isEqualTo(patientDiagnosisAuditLog2);
        patientDiagnosisAuditLog2.setId(2L);
        assertThat(patientDiagnosisAuditLog1).isNotEqualTo(patientDiagnosisAuditLog2);
        patientDiagnosisAuditLog1.setId(null);
        assertThat(patientDiagnosisAuditLog1).isNotEqualTo(patientDiagnosisAuditLog2);
    }
}

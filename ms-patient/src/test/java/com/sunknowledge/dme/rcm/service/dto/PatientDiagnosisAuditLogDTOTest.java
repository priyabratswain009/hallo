package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDiagnosisAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDiagnosisAuditLogDTO.class);
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO1 = new PatientDiagnosisAuditLogDTO();
        patientDiagnosisAuditLogDTO1.setId(1L);
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO2 = new PatientDiagnosisAuditLogDTO();
        assertThat(patientDiagnosisAuditLogDTO1).isNotEqualTo(patientDiagnosisAuditLogDTO2);
        patientDiagnosisAuditLogDTO2.setId(patientDiagnosisAuditLogDTO1.getId());
        assertThat(patientDiagnosisAuditLogDTO1).isEqualTo(patientDiagnosisAuditLogDTO2);
        patientDiagnosisAuditLogDTO2.setId(2L);
        assertThat(patientDiagnosisAuditLogDTO1).isNotEqualTo(patientDiagnosisAuditLogDTO2);
        patientDiagnosisAuditLogDTO1.setId(null);
        assertThat(patientDiagnosisAuditLogDTO1).isNotEqualTo(patientDiagnosisAuditLogDTO2);
    }
}

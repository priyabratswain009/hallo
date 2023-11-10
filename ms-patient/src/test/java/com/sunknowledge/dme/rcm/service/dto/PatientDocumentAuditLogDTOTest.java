package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocumentAuditLogDTO.class);
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO1 = new PatientDocumentAuditLogDTO();
        patientDocumentAuditLogDTO1.setId(1L);
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO2 = new PatientDocumentAuditLogDTO();
        assertThat(patientDocumentAuditLogDTO1).isNotEqualTo(patientDocumentAuditLogDTO2);
        patientDocumentAuditLogDTO2.setId(patientDocumentAuditLogDTO1.getId());
        assertThat(patientDocumentAuditLogDTO1).isEqualTo(patientDocumentAuditLogDTO2);
        patientDocumentAuditLogDTO2.setId(2L);
        assertThat(patientDocumentAuditLogDTO1).isNotEqualTo(patientDocumentAuditLogDTO2);
        patientDocumentAuditLogDTO1.setId(null);
        assertThat(patientDocumentAuditLogDTO1).isNotEqualTo(patientDocumentAuditLogDTO2);
    }
}

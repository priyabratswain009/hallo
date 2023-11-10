package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientMasterAuditLogDTO.class);
        PatientMasterAuditLogDTO patientMasterAuditLogDTO1 = new PatientMasterAuditLogDTO();
        patientMasterAuditLogDTO1.setId(1L);
        PatientMasterAuditLogDTO patientMasterAuditLogDTO2 = new PatientMasterAuditLogDTO();
        assertThat(patientMasterAuditLogDTO1).isNotEqualTo(patientMasterAuditLogDTO2);
        patientMasterAuditLogDTO2.setId(patientMasterAuditLogDTO1.getId());
        assertThat(patientMasterAuditLogDTO1).isEqualTo(patientMasterAuditLogDTO2);
        patientMasterAuditLogDTO2.setId(2L);
        assertThat(patientMasterAuditLogDTO1).isNotEqualTo(patientMasterAuditLogDTO2);
        patientMasterAuditLogDTO1.setId(null);
        assertThat(patientMasterAuditLogDTO1).isNotEqualTo(patientMasterAuditLogDTO2);
    }
}

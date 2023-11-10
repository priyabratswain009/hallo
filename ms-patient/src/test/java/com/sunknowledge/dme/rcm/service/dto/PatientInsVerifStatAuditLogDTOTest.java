package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsVerifStatAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsVerifStatAuditLogDTO.class);
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO1 = new PatientInsVerifStatAuditLogDTO();
        patientInsVerifStatAuditLogDTO1.setId(1L);
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO2 = new PatientInsVerifStatAuditLogDTO();
        assertThat(patientInsVerifStatAuditLogDTO1).isNotEqualTo(patientInsVerifStatAuditLogDTO2);
        patientInsVerifStatAuditLogDTO2.setId(patientInsVerifStatAuditLogDTO1.getId());
        assertThat(patientInsVerifStatAuditLogDTO1).isEqualTo(patientInsVerifStatAuditLogDTO2);
        patientInsVerifStatAuditLogDTO2.setId(2L);
        assertThat(patientInsVerifStatAuditLogDTO1).isNotEqualTo(patientInsVerifStatAuditLogDTO2);
        patientInsVerifStatAuditLogDTO1.setId(null);
        assertThat(patientInsVerifStatAuditLogDTO1).isNotEqualTo(patientInsVerifStatAuditLogDTO2);
    }
}

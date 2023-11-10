package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsuranceAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsuranceAuditLogDTO.class);
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO1 = new PatientInsuranceAuditLogDTO();
        patientInsuranceAuditLogDTO1.setId(1L);
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO2 = new PatientInsuranceAuditLogDTO();
        assertThat(patientInsuranceAuditLogDTO1).isNotEqualTo(patientInsuranceAuditLogDTO2);
        patientInsuranceAuditLogDTO2.setId(patientInsuranceAuditLogDTO1.getId());
        assertThat(patientInsuranceAuditLogDTO1).isEqualTo(patientInsuranceAuditLogDTO2);
        patientInsuranceAuditLogDTO2.setId(2L);
        assertThat(patientInsuranceAuditLogDTO1).isNotEqualTo(patientInsuranceAuditLogDTO2);
        patientInsuranceAuditLogDTO1.setId(null);
        assertThat(patientInsuranceAuditLogDTO1).isNotEqualTo(patientInsuranceAuditLogDTO2);
    }
}

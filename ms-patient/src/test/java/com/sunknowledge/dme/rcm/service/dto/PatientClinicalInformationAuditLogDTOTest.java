package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientClinicalInformationAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientClinicalInformationAuditLogDTO.class);
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO1 = new PatientClinicalInformationAuditLogDTO();
        patientClinicalInformationAuditLogDTO1.setId(1L);
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO2 = new PatientClinicalInformationAuditLogDTO();
        assertThat(patientClinicalInformationAuditLogDTO1).isNotEqualTo(patientClinicalInformationAuditLogDTO2);
        patientClinicalInformationAuditLogDTO2.setId(patientClinicalInformationAuditLogDTO1.getId());
        assertThat(patientClinicalInformationAuditLogDTO1).isEqualTo(patientClinicalInformationAuditLogDTO2);
        patientClinicalInformationAuditLogDTO2.setId(2L);
        assertThat(patientClinicalInformationAuditLogDTO1).isNotEqualTo(patientClinicalInformationAuditLogDTO2);
        patientClinicalInformationAuditLogDTO1.setId(null);
        assertThat(patientClinicalInformationAuditLogDTO1).isNotEqualTo(patientClinicalInformationAuditLogDTO2);
    }
}

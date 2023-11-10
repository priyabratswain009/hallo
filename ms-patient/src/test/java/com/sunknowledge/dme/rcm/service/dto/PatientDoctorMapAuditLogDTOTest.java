package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDoctorMapAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDoctorMapAuditLogDTO.class);
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO1 = new PatientDoctorMapAuditLogDTO();
        patientDoctorMapAuditLogDTO1.setId(1L);
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO2 = new PatientDoctorMapAuditLogDTO();
        assertThat(patientDoctorMapAuditLogDTO1).isNotEqualTo(patientDoctorMapAuditLogDTO2);
        patientDoctorMapAuditLogDTO2.setId(patientDoctorMapAuditLogDTO1.getId());
        assertThat(patientDoctorMapAuditLogDTO1).isEqualTo(patientDoctorMapAuditLogDTO2);
        patientDoctorMapAuditLogDTO2.setId(2L);
        assertThat(patientDoctorMapAuditLogDTO1).isNotEqualTo(patientDoctorMapAuditLogDTO2);
        patientDoctorMapAuditLogDTO1.setId(null);
        assertThat(patientDoctorMapAuditLogDTO1).isNotEqualTo(patientDoctorMapAuditLogDTO2);
    }
}

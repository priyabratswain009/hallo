package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDoctorMapAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDoctorMapAuditLog.class);
        PatientDoctorMapAuditLog patientDoctorMapAuditLog1 = new PatientDoctorMapAuditLog();
        patientDoctorMapAuditLog1.setId(1L);
        PatientDoctorMapAuditLog patientDoctorMapAuditLog2 = new PatientDoctorMapAuditLog();
        patientDoctorMapAuditLog2.setId(patientDoctorMapAuditLog1.getId());
        assertThat(patientDoctorMapAuditLog1).isEqualTo(patientDoctorMapAuditLog2);
        patientDoctorMapAuditLog2.setId(2L);
        assertThat(patientDoctorMapAuditLog1).isNotEqualTo(patientDoctorMapAuditLog2);
        patientDoctorMapAuditLog1.setId(null);
        assertThat(patientDoctorMapAuditLog1).isNotEqualTo(patientDoctorMapAuditLog2);
    }
}

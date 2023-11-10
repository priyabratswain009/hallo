package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientClinicalInformationAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientClinicalInformationAuditLog.class);
        PatientClinicalInformationAuditLog patientClinicalInformationAuditLog1 = new PatientClinicalInformationAuditLog();
        patientClinicalInformationAuditLog1.setId(1L);
        PatientClinicalInformationAuditLog patientClinicalInformationAuditLog2 = new PatientClinicalInformationAuditLog();
        patientClinicalInformationAuditLog2.setId(patientClinicalInformationAuditLog1.getId());
        assertThat(patientClinicalInformationAuditLog1).isEqualTo(patientClinicalInformationAuditLog2);
        patientClinicalInformationAuditLog2.setId(2L);
        assertThat(patientClinicalInformationAuditLog1).isNotEqualTo(patientClinicalInformationAuditLog2);
        patientClinicalInformationAuditLog1.setId(null);
        assertThat(patientClinicalInformationAuditLog1).isNotEqualTo(patientClinicalInformationAuditLog2);
    }
}

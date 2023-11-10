package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDetails.class);
        PatientDetails patientDetails1 = new PatientDetails();
        patientDetails1.setPatientId(1L);
        PatientDetails patientDetails2 = new PatientDetails();
        patientDetails2.setPatientId(patientDetails1.getPatientId());
        assertThat(patientDetails1).isEqualTo(patientDetails2);
        patientDetails2.setPatientId(2L);
        assertThat(patientDetails1).isNotEqualTo(patientDetails2);
        patientDetails1.setPatientId(null);
        assertThat(patientDetails1).isNotEqualTo(patientDetails2);
    }
}

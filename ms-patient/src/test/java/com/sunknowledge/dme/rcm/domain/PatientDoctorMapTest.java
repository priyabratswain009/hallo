package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDoctorMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDoctorMap.class);
        PatientDoctorMap patientDoctorMap1 = new PatientDoctorMap();
        patientDoctorMap1.setPatientDoctorMapId(1L);
        PatientDoctorMap patientDoctorMap2 = new PatientDoctorMap();
        patientDoctorMap2.setPatientDoctorMapId(patientDoctorMap1.getPatientDoctorMapId());
        assertThat(patientDoctorMap1).isEqualTo(patientDoctorMap2);
        patientDoctorMap2.setPatientDoctorMapId(2L);
        assertThat(patientDoctorMap1).isNotEqualTo(patientDoctorMap2);
        patientDoctorMap1.setPatientDoctorMapId(null);
        assertThat(patientDoctorMap1).isNotEqualTo(patientDoctorMap2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientClinicalInformationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientClinicalInformation.class);
        PatientClinicalInformation patientClinicalInformation1 = new PatientClinicalInformation();
        patientClinicalInformation1.setPatientClinicalInformationId(1L);
        PatientClinicalInformation patientClinicalInformation2 = new PatientClinicalInformation();
        patientClinicalInformation2.setPatientClinicalInformationId(patientClinicalInformation1.getPatientClinicalInformationId());
        assertThat(patientClinicalInformation1).isEqualTo(patientClinicalInformation2);
        patientClinicalInformation2.setPatientClinicalInformationId(2L);
        assertThat(patientClinicalInformation1).isNotEqualTo(patientClinicalInformation2);
        patientClinicalInformation1.setPatientClinicalInformationId(null);
        assertThat(patientClinicalInformation1).isNotEqualTo(patientClinicalInformation2);
    }
}

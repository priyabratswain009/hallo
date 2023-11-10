package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsuranceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsurance.class);
        PatientInsurance patientInsurance1 = new PatientInsurance();
        patientInsurance1.setPatientInsuranceId(1L);
        PatientInsurance patientInsurance2 = new PatientInsurance();
        patientInsurance2.setPatientInsuranceId(patientInsurance1.getPatientInsuranceId());
        assertThat(patientInsurance1).isEqualTo(patientInsurance2);
        patientInsurance2.setPatientInsuranceId(2L);
        assertThat(patientInsurance1).isNotEqualTo(patientInsurance2);
        patientInsurance1.setPatientInsuranceId(null);
        assertThat(patientInsurance1).isNotEqualTo(patientInsurance2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDiagnosisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDiagnosis.class);
        PatientDiagnosis patientDiagnosis1 = new PatientDiagnosis();
        patientDiagnosis1.setPatientDiagnosisId(1L);
        PatientDiagnosis patientDiagnosis2 = new PatientDiagnosis();
        patientDiagnosis2.setPatientDiagnosisId(patientDiagnosis1.getPatientDiagnosisId());
        assertThat(patientDiagnosis1).isEqualTo(patientDiagnosis2);
        patientDiagnosis2.setPatientDiagnosisId(2L);
        assertThat(patientDiagnosis1).isNotEqualTo(patientDiagnosis2);
        patientDiagnosis1.setPatientDiagnosisId(null);
        assertThat(patientDiagnosis1).isNotEqualTo(patientDiagnosis2);
    }
}

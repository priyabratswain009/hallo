package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDiagnosisDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDiagnosisDTO.class);
        PatientDiagnosisDTO patientDiagnosisDTO1 = new PatientDiagnosisDTO();
        patientDiagnosisDTO1.setPatientDiagnosisId(1L);
        PatientDiagnosisDTO patientDiagnosisDTO2 = new PatientDiagnosisDTO();
        assertThat(patientDiagnosisDTO1).isNotEqualTo(patientDiagnosisDTO2);
        patientDiagnosisDTO2.setPatientDiagnosisId(patientDiagnosisDTO1.getPatientDiagnosisId());
        assertThat(patientDiagnosisDTO1).isEqualTo(patientDiagnosisDTO2);
        patientDiagnosisDTO2.setPatientDiagnosisId(2L);
        assertThat(patientDiagnosisDTO1).isNotEqualTo(patientDiagnosisDTO2);
        patientDiagnosisDTO1.setPatientDiagnosisId(null);
        assertThat(patientDiagnosisDTO1).isNotEqualTo(patientDiagnosisDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsuranceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsuranceDTO.class);
        PatientInsuranceDTO patientInsuranceDTO1 = new PatientInsuranceDTO();
        patientInsuranceDTO1.setPatientInsuranceId(1L);
        PatientInsuranceDTO patientInsuranceDTO2 = new PatientInsuranceDTO();
        assertThat(patientInsuranceDTO1).isNotEqualTo(patientInsuranceDTO2);
        patientInsuranceDTO2.setPatientInsuranceId(patientInsuranceDTO1.getPatientInsuranceId());
        assertThat(patientInsuranceDTO1).isEqualTo(patientInsuranceDTO2);
        patientInsuranceDTO2.setPatientInsuranceId(2L);
        assertThat(patientInsuranceDTO1).isNotEqualTo(patientInsuranceDTO2);
        patientInsuranceDTO1.setPatientInsuranceId(null);
        assertThat(patientInsuranceDTO1).isNotEqualTo(patientInsuranceDTO2);
    }
}

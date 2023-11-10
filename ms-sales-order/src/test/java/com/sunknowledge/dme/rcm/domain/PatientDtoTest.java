package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDtoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDto.class);
        PatientDto patientDto1 = new PatientDto();
        patientDto1.setPatientDtoId(1L);
        PatientDto patientDto2 = new PatientDto();
        patientDto2.setPatientDtoId(patientDto1.getPatientDtoId());
        assertThat(patientDto1).isEqualTo(patientDto2);
        patientDto2.setPatientDtoId(2L);
        assertThat(patientDto1).isNotEqualTo(patientDto2);
        patientDto1.setPatientDtoId(null);
        assertThat(patientDto1).isNotEqualTo(patientDto2);
    }
}

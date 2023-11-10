package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDtoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDtoDTO.class);
        PatientDtoDTO patientDtoDTO1 = new PatientDtoDTO();
        patientDtoDTO1.setPatientDtoId(1L);
        PatientDtoDTO patientDtoDTO2 = new PatientDtoDTO();
        assertThat(patientDtoDTO1).isNotEqualTo(patientDtoDTO2);
        patientDtoDTO2.setPatientDtoId(patientDtoDTO1.getPatientDtoId());
        assertThat(patientDtoDTO1).isEqualTo(patientDtoDTO2);
        patientDtoDTO2.setPatientDtoId(2L);
        assertThat(patientDtoDTO1).isNotEqualTo(patientDtoDTO2);
        patientDtoDTO1.setPatientDtoId(null);
        assertThat(patientDtoDTO1).isNotEqualTo(patientDtoDTO2);
    }
}

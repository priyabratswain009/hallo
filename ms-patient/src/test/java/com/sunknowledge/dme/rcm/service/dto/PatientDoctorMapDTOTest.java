package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDoctorMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDoctorMapDTO.class);
        PatientDoctorMapDTO patientDoctorMapDTO1 = new PatientDoctorMapDTO();
        patientDoctorMapDTO1.setPatientDoctorMapId(1L);
        PatientDoctorMapDTO patientDoctorMapDTO2 = new PatientDoctorMapDTO();
        assertThat(patientDoctorMapDTO1).isNotEqualTo(patientDoctorMapDTO2);
        patientDoctorMapDTO2.setPatientDoctorMapId(patientDoctorMapDTO1.getPatientDoctorMapId());
        assertThat(patientDoctorMapDTO1).isEqualTo(patientDoctorMapDTO2);
        patientDoctorMapDTO2.setPatientDoctorMapId(2L);
        assertThat(patientDoctorMapDTO1).isNotEqualTo(patientDoctorMapDTO2);
        patientDoctorMapDTO1.setPatientDoctorMapId(null);
        assertThat(patientDoctorMapDTO1).isNotEqualTo(patientDoctorMapDTO2);
    }
}

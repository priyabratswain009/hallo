package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientMasterDTO.class);
        PatientMasterDTO patientMasterDTO1 = new PatientMasterDTO();
        patientMasterDTO1.setPatientId(1L);
        PatientMasterDTO patientMasterDTO2 = new PatientMasterDTO();
        assertThat(patientMasterDTO1).isNotEqualTo(patientMasterDTO2);
        patientMasterDTO2.setPatientId(patientMasterDTO1.getPatientId());
        assertThat(patientMasterDTO1).isEqualTo(patientMasterDTO2);
        patientMasterDTO2.setPatientId(2L);
        assertThat(patientMasterDTO1).isNotEqualTo(patientMasterDTO2);
        patientMasterDTO1.setPatientId(null);
        assertThat(patientMasterDTO1).isNotEqualTo(patientMasterDTO2);
    }
}

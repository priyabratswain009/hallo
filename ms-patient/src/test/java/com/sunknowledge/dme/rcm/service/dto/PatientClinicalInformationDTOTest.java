package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientClinicalInformationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientClinicalInformationDTO.class);
        PatientClinicalInformationDTO patientClinicalInformationDTO1 = new PatientClinicalInformationDTO();
        patientClinicalInformationDTO1.setPatientClinicalInformationId(1L);
        PatientClinicalInformationDTO patientClinicalInformationDTO2 = new PatientClinicalInformationDTO();
        assertThat(patientClinicalInformationDTO1).isNotEqualTo(patientClinicalInformationDTO2);
        patientClinicalInformationDTO2.setPatientClinicalInformationId(patientClinicalInformationDTO1.getPatientClinicalInformationId());
        assertThat(patientClinicalInformationDTO1).isEqualTo(patientClinicalInformationDTO2);
        patientClinicalInformationDTO2.setPatientClinicalInformationId(2L);
        assertThat(patientClinicalInformationDTO1).isNotEqualTo(patientClinicalInformationDTO2);
        patientClinicalInformationDTO1.setPatientClinicalInformationId(null);
        assertThat(patientClinicalInformationDTO1).isNotEqualTo(patientClinicalInformationDTO2);
    }
}

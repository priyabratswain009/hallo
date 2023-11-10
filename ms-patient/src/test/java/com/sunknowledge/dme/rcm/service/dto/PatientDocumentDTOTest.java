package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocumentDTO.class);
        PatientDocumentDTO patientDocumentDTO1 = new PatientDocumentDTO();
        patientDocumentDTO1.setPatientDocumentId(1L);
        PatientDocumentDTO patientDocumentDTO2 = new PatientDocumentDTO();
        assertThat(patientDocumentDTO1).isNotEqualTo(patientDocumentDTO2);
        patientDocumentDTO2.setPatientDocumentId(patientDocumentDTO1.getPatientDocumentId());
        assertThat(patientDocumentDTO1).isEqualTo(patientDocumentDTO2);
        patientDocumentDTO2.setPatientDocumentId(2L);
        assertThat(patientDocumentDTO1).isNotEqualTo(patientDocumentDTO2);
        patientDocumentDTO1.setPatientDocumentId(null);
        assertThat(patientDocumentDTO1).isNotEqualTo(patientDocumentDTO2);
    }
}

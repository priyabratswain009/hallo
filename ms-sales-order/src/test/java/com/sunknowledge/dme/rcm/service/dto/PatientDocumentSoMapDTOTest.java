package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentSoMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocumentSoMapDTO.class);
        PatientDocumentSoMapDTO patientDocumentSoMapDTO1 = new PatientDocumentSoMapDTO();
        patientDocumentSoMapDTO1.setPatientDocumentSoMapId(1L);
        PatientDocumentSoMapDTO patientDocumentSoMapDTO2 = new PatientDocumentSoMapDTO();
        assertThat(patientDocumentSoMapDTO1).isNotEqualTo(patientDocumentSoMapDTO2);
        patientDocumentSoMapDTO2.setPatientDocumentSoMapId(patientDocumentSoMapDTO1.getPatientDocumentSoMapId());
        assertThat(patientDocumentSoMapDTO1).isEqualTo(patientDocumentSoMapDTO2);
        patientDocumentSoMapDTO2.setPatientDocumentSoMapId(2L);
        assertThat(patientDocumentSoMapDTO1).isNotEqualTo(patientDocumentSoMapDTO2);
        patientDocumentSoMapDTO1.setPatientDocumentSoMapId(null);
        assertThat(patientDocumentSoMapDTO1).isNotEqualTo(patientDocumentSoMapDTO2);
    }
}

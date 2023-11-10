package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocument.class);
        PatientDocument patientDocument1 = new PatientDocument();
        patientDocument1.setPatientDocumentId(1L);
        PatientDocument patientDocument2 = new PatientDocument();
        patientDocument2.setPatientDocumentId(patientDocument1.getPatientDocumentId());
        assertThat(patientDocument1).isEqualTo(patientDocument2);
        patientDocument2.setPatientDocumentId(2L);
        assertThat(patientDocument1).isNotEqualTo(patientDocument2);
        patientDocument1.setPatientDocumentId(null);
        assertThat(patientDocument1).isNotEqualTo(patientDocument2);
    }
}

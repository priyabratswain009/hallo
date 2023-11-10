package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientDocumentSoMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientDocumentSoMap.class);
        PatientDocumentSoMap patientDocumentSoMap1 = new PatientDocumentSoMap();
        patientDocumentSoMap1.setPatientDocumentSoMapId(1L);
        PatientDocumentSoMap patientDocumentSoMap2 = new PatientDocumentSoMap();
        patientDocumentSoMap2.setPatientDocumentSoMapId(patientDocumentSoMap1.getPatientDocumentSoMapId());
        assertThat(patientDocumentSoMap1).isEqualTo(patientDocumentSoMap2);
        patientDocumentSoMap2.setPatientDocumentSoMapId(2L);
        assertThat(patientDocumentSoMap1).isNotEqualTo(patientDocumentSoMap2);
        patientDocumentSoMap1.setPatientDocumentSoMapId(null);
        assertThat(patientDocumentSoMap1).isNotEqualTo(patientDocumentSoMap2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientMaster.class);
        PatientMaster patientMaster1 = new PatientMaster();
        patientMaster1.setPatientId(1L);
        PatientMaster patientMaster2 = new PatientMaster();
        patientMaster2.setPatientId(patientMaster1.getPatientId());
        assertThat(patientMaster1).isEqualTo(patientMaster2);
        patientMaster2.setPatientId(2L);
        assertThat(patientMaster1).isNotEqualTo(patientMaster2);
        patientMaster1.setPatientId(null);
        assertThat(patientMaster1).isNotEqualTo(patientMaster2);
    }
}

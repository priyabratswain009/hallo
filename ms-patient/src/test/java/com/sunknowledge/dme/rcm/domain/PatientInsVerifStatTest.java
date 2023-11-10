package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsVerifStatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsVerifStat.class);
        PatientInsVerifStat patientInsVerifStat1 = new PatientInsVerifStat();
        patientInsVerifStat1.setInsuranceVerifId(1L);
        PatientInsVerifStat patientInsVerifStat2 = new PatientInsVerifStat();
        patientInsVerifStat2.setInsuranceVerifId(patientInsVerifStat1.getInsuranceVerifId());
        assertThat(patientInsVerifStat1).isEqualTo(patientInsVerifStat2);
        patientInsVerifStat2.setInsuranceVerifId(2L);
        assertThat(patientInsVerifStat1).isNotEqualTo(patientInsVerifStat2);
        patientInsVerifStat1.setInsuranceVerifId(null);
        assertThat(patientInsVerifStat1).isNotEqualTo(patientInsVerifStat2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DoctorMedicalInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorMedicalInfo.class);
        DoctorMedicalInfo doctorMedicalInfo1 = new DoctorMedicalInfo();
        doctorMedicalInfo1.setMedicalInfoId(1L);
        DoctorMedicalInfo doctorMedicalInfo2 = new DoctorMedicalInfo();
        doctorMedicalInfo2.setMedicalInfoId(doctorMedicalInfo1.getMedicalInfoId());
        assertThat(doctorMedicalInfo1).isEqualTo(doctorMedicalInfo2);
        doctorMedicalInfo2.setMedicalInfoId(2L);
        assertThat(doctorMedicalInfo1).isNotEqualTo(doctorMedicalInfo2);
        doctorMedicalInfo1.setMedicalInfoId(null);
        assertThat(doctorMedicalInfo1).isNotEqualTo(doctorMedicalInfo2);
    }
}

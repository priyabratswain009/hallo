package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DoctorMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorMaster.class);
        DoctorMaster doctorMaster1 = new DoctorMaster();
        doctorMaster1.setDoctorId(1L);
        DoctorMaster doctorMaster2 = new DoctorMaster();
        doctorMaster2.setDoctorId(doctorMaster1.getDoctorId());
        assertThat(doctorMaster1).isEqualTo(doctorMaster2);
        doctorMaster2.setDoctorId(2L);
        assertThat(doctorMaster1).isNotEqualTo(doctorMaster2);
        doctorMaster1.setDoctorId(null);
        assertThat(doctorMaster1).isNotEqualTo(doctorMaster2);
    }
}

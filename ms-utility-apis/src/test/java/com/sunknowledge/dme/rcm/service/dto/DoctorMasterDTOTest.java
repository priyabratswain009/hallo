package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DoctorMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorMasterDTO.class);
        DoctorMasterDTO doctorMasterDTO1 = new DoctorMasterDTO();
        doctorMasterDTO1.setDoctorId(1L);
        DoctorMasterDTO doctorMasterDTO2 = new DoctorMasterDTO();
        assertThat(doctorMasterDTO1).isNotEqualTo(doctorMasterDTO2);
        doctorMasterDTO2.setDoctorId(doctorMasterDTO1.getDoctorId());
        assertThat(doctorMasterDTO1).isEqualTo(doctorMasterDTO2);
        doctorMasterDTO2.setDoctorId(2L);
        assertThat(doctorMasterDTO1).isNotEqualTo(doctorMasterDTO2);
        doctorMasterDTO1.setDoctorId(null);
        assertThat(doctorMasterDTO1).isNotEqualTo(doctorMasterDTO2);
    }
}

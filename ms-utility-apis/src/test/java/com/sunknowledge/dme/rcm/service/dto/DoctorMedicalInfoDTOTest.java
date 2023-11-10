package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DoctorMedicalInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorMedicalInfoDTO.class);
        DoctorMedicalInfoDTO doctorMedicalInfoDTO1 = new DoctorMedicalInfoDTO();
        doctorMedicalInfoDTO1.setMedicalInfoId(1L);
        DoctorMedicalInfoDTO doctorMedicalInfoDTO2 = new DoctorMedicalInfoDTO();
        assertThat(doctorMedicalInfoDTO1).isNotEqualTo(doctorMedicalInfoDTO2);
        doctorMedicalInfoDTO2.setMedicalInfoId(doctorMedicalInfoDTO1.getMedicalInfoId());
        assertThat(doctorMedicalInfoDTO1).isEqualTo(doctorMedicalInfoDTO2);
        doctorMedicalInfoDTO2.setMedicalInfoId(2L);
        assertThat(doctorMedicalInfoDTO1).isNotEqualTo(doctorMedicalInfoDTO2);
        doctorMedicalInfoDTO1.setMedicalInfoId(null);
        assertThat(doctorMedicalInfoDTO1).isNotEqualTo(doctorMedicalInfoDTO2);
    }
}

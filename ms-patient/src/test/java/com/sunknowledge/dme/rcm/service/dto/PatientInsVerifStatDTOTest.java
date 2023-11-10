package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsVerifStatDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsVerifStatDTO.class);
        PatientInsVerifStatDTO patientInsVerifStatDTO1 = new PatientInsVerifStatDTO();
        patientInsVerifStatDTO1.setInsuranceVerifId(1L);
        PatientInsVerifStatDTO patientInsVerifStatDTO2 = new PatientInsVerifStatDTO();
        assertThat(patientInsVerifStatDTO1).isNotEqualTo(patientInsVerifStatDTO2);
        patientInsVerifStatDTO2.setInsuranceVerifId(patientInsVerifStatDTO1.getInsuranceVerifId());
        assertThat(patientInsVerifStatDTO1).isEqualTo(patientInsVerifStatDTO2);
        patientInsVerifStatDTO2.setInsuranceVerifId(2L);
        assertThat(patientInsVerifStatDTO1).isNotEqualTo(patientInsVerifStatDTO2);
        patientInsVerifStatDTO1.setInsuranceVerifId(null);
        assertThat(patientInsVerifStatDTO1).isNotEqualTo(patientInsVerifStatDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceMasterDTO.class);
        InsuranceMasterDTO insuranceMasterDTO1 = new InsuranceMasterDTO();
        insuranceMasterDTO1.setInsuranceId(1L);
        InsuranceMasterDTO insuranceMasterDTO2 = new InsuranceMasterDTO();
        assertThat(insuranceMasterDTO1).isNotEqualTo(insuranceMasterDTO2);
        insuranceMasterDTO2.setInsuranceId(insuranceMasterDTO1.getInsuranceId());
        assertThat(insuranceMasterDTO1).isEqualTo(insuranceMasterDTO2);
        insuranceMasterDTO2.setInsuranceId(2L);
        assertThat(insuranceMasterDTO1).isNotEqualTo(insuranceMasterDTO2);
        insuranceMasterDTO1.setInsuranceId(null);
        assertThat(insuranceMasterDTO1).isNotEqualTo(insuranceMasterDTO2);
    }
}

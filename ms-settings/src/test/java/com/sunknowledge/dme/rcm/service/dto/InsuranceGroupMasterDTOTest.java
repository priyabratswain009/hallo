package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceGroupMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceGroupMasterDTO.class);
        InsuranceGroupMasterDTO insuranceGroupMasterDTO1 = new InsuranceGroupMasterDTO();
        insuranceGroupMasterDTO1.setInsuranceGroupMasterId(1L);
        InsuranceGroupMasterDTO insuranceGroupMasterDTO2 = new InsuranceGroupMasterDTO();
        assertThat(insuranceGroupMasterDTO1).isNotEqualTo(insuranceGroupMasterDTO2);
        insuranceGroupMasterDTO2.setInsuranceGroupMasterId(insuranceGroupMasterDTO1.getInsuranceGroupMasterId());
        assertThat(insuranceGroupMasterDTO1).isEqualTo(insuranceGroupMasterDTO2);
        insuranceGroupMasterDTO2.setInsuranceGroupMasterId(2L);
        assertThat(insuranceGroupMasterDTO1).isNotEqualTo(insuranceGroupMasterDTO2);
        insuranceGroupMasterDTO1.setInsuranceGroupMasterId(null);
        assertThat(insuranceGroupMasterDTO1).isNotEqualTo(insuranceGroupMasterDTO2);
    }
}

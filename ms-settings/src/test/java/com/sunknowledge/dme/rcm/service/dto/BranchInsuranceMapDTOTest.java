package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchInsuranceMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchInsuranceMapDTO.class);
        BranchInsuranceMapDTO branchInsuranceMapDTO1 = new BranchInsuranceMapDTO();
        branchInsuranceMapDTO1.setBranchInsuranceMapId(1L);
        BranchInsuranceMapDTO branchInsuranceMapDTO2 = new BranchInsuranceMapDTO();
        assertThat(branchInsuranceMapDTO1).isNotEqualTo(branchInsuranceMapDTO2);
        branchInsuranceMapDTO2.setBranchInsuranceMapId(branchInsuranceMapDTO1.getBranchInsuranceMapId());
        assertThat(branchInsuranceMapDTO1).isEqualTo(branchInsuranceMapDTO2);
        branchInsuranceMapDTO2.setBranchInsuranceMapId(2L);
        assertThat(branchInsuranceMapDTO1).isNotEqualTo(branchInsuranceMapDTO2);
        branchInsuranceMapDTO1.setBranchInsuranceMapId(null);
        assertThat(branchInsuranceMapDTO1).isNotEqualTo(branchInsuranceMapDTO2);
    }
}

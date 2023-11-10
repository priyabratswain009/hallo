package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchInsuranceMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchInsuranceMap.class);
        BranchInsuranceMap branchInsuranceMap1 = new BranchInsuranceMap();
        branchInsuranceMap1.setBranchInsuranceMapId(1L);
        BranchInsuranceMap branchInsuranceMap2 = new BranchInsuranceMap();
        branchInsuranceMap2.setBranchInsuranceMapId(branchInsuranceMap1.getBranchInsuranceMapId());
        assertThat(branchInsuranceMap1).isEqualTo(branchInsuranceMap2);
        branchInsuranceMap2.setBranchInsuranceMapId(2L);
        assertThat(branchInsuranceMap1).isNotEqualTo(branchInsuranceMap2);
        branchInsuranceMap1.setBranchInsuranceMapId(null);
        assertThat(branchInsuranceMap1).isNotEqualTo(branchInsuranceMap2);
    }
}

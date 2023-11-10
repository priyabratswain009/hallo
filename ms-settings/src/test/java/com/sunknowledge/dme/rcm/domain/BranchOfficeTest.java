package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchOfficeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchOffice.class);
        BranchOffice branchOffice1 = new BranchOffice();
        branchOffice1.setBranchId(1L);
        BranchOffice branchOffice2 = new BranchOffice();
        branchOffice2.setBranchId(branchOffice1.getBranchId());
        assertThat(branchOffice1).isEqualTo(branchOffice2);
        branchOffice2.setBranchId(2L);
        assertThat(branchOffice1).isNotEqualTo(branchOffice2);
        branchOffice1.setBranchId(null);
        assertThat(branchOffice1).isNotEqualTo(branchOffice2);
    }
}

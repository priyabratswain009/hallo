package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchOfficeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchOfficeDTO.class);
        BranchOfficeDTO branchOfficeDTO1 = new BranchOfficeDTO();
        branchOfficeDTO1.setBranchId(1L);
        BranchOfficeDTO branchOfficeDTO2 = new BranchOfficeDTO();
        assertThat(branchOfficeDTO1).isNotEqualTo(branchOfficeDTO2);
        branchOfficeDTO2.setBranchId(branchOfficeDTO1.getBranchId());
        assertThat(branchOfficeDTO1).isEqualTo(branchOfficeDTO2);
        branchOfficeDTO2.setBranchId(2L);
        assertThat(branchOfficeDTO1).isNotEqualTo(branchOfficeDTO2);
        branchOfficeDTO1.setBranchId(null);
        assertThat(branchOfficeDTO1).isNotEqualTo(branchOfficeDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchGroupDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchGroupDTO.class);
        BranchGroupDTO branchGroupDTO1 = new BranchGroupDTO();
        branchGroupDTO1.setBranchGroupId(1L);
        BranchGroupDTO branchGroupDTO2 = new BranchGroupDTO();
        assertThat(branchGroupDTO1).isNotEqualTo(branchGroupDTO2);
        branchGroupDTO2.setBranchGroupId(branchGroupDTO1.getBranchGroupId());
        assertThat(branchGroupDTO1).isEqualTo(branchGroupDTO2);
        branchGroupDTO2.setBranchGroupId(2L);
        assertThat(branchGroupDTO1).isNotEqualTo(branchGroupDTO2);
        branchGroupDTO1.setBranchGroupId(null);
        assertThat(branchGroupDTO1).isNotEqualTo(branchGroupDTO2);
    }
}

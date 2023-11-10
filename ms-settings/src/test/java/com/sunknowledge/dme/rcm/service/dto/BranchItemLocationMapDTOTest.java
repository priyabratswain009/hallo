package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchItemLocationMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchItemLocationMapDTO.class);
        BranchItemLocationMapDTO branchItemLocationMapDTO1 = new BranchItemLocationMapDTO();
        branchItemLocationMapDTO1.setBranchItemLocationMapId(1L);
        BranchItemLocationMapDTO branchItemLocationMapDTO2 = new BranchItemLocationMapDTO();
        assertThat(branchItemLocationMapDTO1).isNotEqualTo(branchItemLocationMapDTO2);
        branchItemLocationMapDTO2.setBranchItemLocationMapId(branchItemLocationMapDTO1.getBranchItemLocationMapId());
        assertThat(branchItemLocationMapDTO1).isEqualTo(branchItemLocationMapDTO2);
        branchItemLocationMapDTO2.setBranchItemLocationMapId(2L);
        assertThat(branchItemLocationMapDTO1).isNotEqualTo(branchItemLocationMapDTO2);
        branchItemLocationMapDTO1.setBranchItemLocationMapId(null);
        assertThat(branchItemLocationMapDTO1).isNotEqualTo(branchItemLocationMapDTO2);
    }
}

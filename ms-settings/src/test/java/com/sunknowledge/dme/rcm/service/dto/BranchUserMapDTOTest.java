package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchUserMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchUserMapDTO.class);
        BranchUserMapDTO branchUserMapDTO1 = new BranchUserMapDTO();
        branchUserMapDTO1.setMapId(1L);
        BranchUserMapDTO branchUserMapDTO2 = new BranchUserMapDTO();
        assertThat(branchUserMapDTO1).isNotEqualTo(branchUserMapDTO2);
        branchUserMapDTO2.setMapId(branchUserMapDTO1.getMapId());
        assertThat(branchUserMapDTO1).isEqualTo(branchUserMapDTO2);
        branchUserMapDTO2.setMapId(2L);
        assertThat(branchUserMapDTO1).isNotEqualTo(branchUserMapDTO2);
        branchUserMapDTO1.setMapId(null);
        assertThat(branchUserMapDTO1).isNotEqualTo(branchUserMapDTO2);
    }
}

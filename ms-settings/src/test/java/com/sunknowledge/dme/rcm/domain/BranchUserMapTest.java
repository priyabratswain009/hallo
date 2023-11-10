package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchUserMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchUserMap.class);
        BranchUserMap branchUserMap1 = new BranchUserMap();
        branchUserMap1.setMapId(1L);
        BranchUserMap branchUserMap2 = new BranchUserMap();
        branchUserMap2.setMapId(branchUserMap1.getMapId());
        assertThat(branchUserMap1).isEqualTo(branchUserMap2);
        branchUserMap2.setMapId(2L);
        assertThat(branchUserMap1).isNotEqualTo(branchUserMap2);
        branchUserMap1.setMapId(null);
        assertThat(branchUserMap1).isNotEqualTo(branchUserMap2);
    }
}

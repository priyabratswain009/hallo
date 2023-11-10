package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchItemLocationMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchItemLocationMap.class);
        BranchItemLocationMap branchItemLocationMap1 = new BranchItemLocationMap();
        branchItemLocationMap1.setBranchItemLocationMapId(1L);
        BranchItemLocationMap branchItemLocationMap2 = new BranchItemLocationMap();
        branchItemLocationMap2.setBranchItemLocationMapId(branchItemLocationMap1.getBranchItemLocationMapId());
        assertThat(branchItemLocationMap1).isEqualTo(branchItemLocationMap2);
        branchItemLocationMap2.setBranchItemLocationMapId(2L);
        assertThat(branchItemLocationMap1).isNotEqualTo(branchItemLocationMap2);
        branchItemLocationMap1.setBranchItemLocationMapId(null);
        assertThat(branchItemLocationMap1).isNotEqualTo(branchItemLocationMap2);
    }
}

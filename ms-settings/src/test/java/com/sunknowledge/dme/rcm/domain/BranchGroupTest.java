package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchGroup.class);
        BranchGroup branchGroup1 = new BranchGroup();
        branchGroup1.setBranchGroupId(1L);
        BranchGroup branchGroup2 = new BranchGroup();
        branchGroup2.setBranchGroupId(branchGroup1.getBranchGroupId());
        assertThat(branchGroup1).isEqualTo(branchGroup2);
        branchGroup2.setBranchGroupId(2L);
        assertThat(branchGroup1).isNotEqualTo(branchGroup2);
        branchGroup1.setBranchGroupId(null);
        assertThat(branchGroup1).isNotEqualTo(branchGroup2);
    }
}

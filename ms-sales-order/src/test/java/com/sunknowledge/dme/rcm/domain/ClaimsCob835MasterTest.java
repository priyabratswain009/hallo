package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCob835MasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCob835Master.class);
        ClaimsCob835Master claimsCob835Master1 = new ClaimsCob835Master();
        claimsCob835Master1.setClaimCob835MasterId(1L);
        ClaimsCob835Master claimsCob835Master2 = new ClaimsCob835Master();
        claimsCob835Master2.setClaimCob835MasterId(claimsCob835Master1.getClaimCob835MasterId());
        assertThat(claimsCob835Master1).isEqualTo(claimsCob835Master2);
        claimsCob835Master2.setClaimCob835MasterId(2L);
        assertThat(claimsCob835Master1).isNotEqualTo(claimsCob835Master2);
        claimsCob835Master1.setClaimCob835MasterId(null);
        assertThat(claimsCob835Master1).isNotEqualTo(claimsCob835Master2);
    }
}

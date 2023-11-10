package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCOB835MasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCOB835Master.class);
        ClaimsCOB835Master claimsCOB835Master1 = new ClaimsCOB835Master();
        claimsCOB835Master1.setClaimCob835MasterId(1L);
        ClaimsCOB835Master claimsCOB835Master2 = new ClaimsCOB835Master();
        claimsCOB835Master2.setClaimCob835MasterId(claimsCOB835Master1.getClaimCob835MasterId());
        assertThat(claimsCOB835Master1).isEqualTo(claimsCOB835Master2);
        claimsCOB835Master2.setClaimCob835MasterId(2L);
        assertThat(claimsCOB835Master1).isNotEqualTo(claimsCOB835Master2);
        claimsCOB835Master1.setClaimCob835MasterId(null);
        assertThat(claimsCOB835Master1).isNotEqualTo(claimsCOB835Master2);
    }
}

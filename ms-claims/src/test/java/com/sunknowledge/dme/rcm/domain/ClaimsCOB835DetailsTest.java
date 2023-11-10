package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCOB835DetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCOB835Details.class);
        ClaimsCOB835Details claimsCOB835Details1 = new ClaimsCOB835Details();
        claimsCOB835Details1.setClaimCob835DetailId(1L);
        ClaimsCOB835Details claimsCOB835Details2 = new ClaimsCOB835Details();
        claimsCOB835Details2.setClaimCob835DetailId(claimsCOB835Details1.getClaimCob835DetailId());
        assertThat(claimsCOB835Details1).isEqualTo(claimsCOB835Details2);
        claimsCOB835Details2.setClaimCob835DetailId(2L);
        assertThat(claimsCOB835Details1).isNotEqualTo(claimsCOB835Details2);
        claimsCOB835Details1.setClaimCob835DetailId(null);
        assertThat(claimsCOB835Details1).isNotEqualTo(claimsCOB835Details2);
    }
}

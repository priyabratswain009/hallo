package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCob835DetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCob835Details.class);
        ClaimsCob835Details claimsCob835Details1 = new ClaimsCob835Details();
        claimsCob835Details1.setClaimCob835DetailId(1L);
        ClaimsCob835Details claimsCob835Details2 = new ClaimsCob835Details();
        claimsCob835Details2.setClaimCob835DetailId(claimsCob835Details1.getClaimCob835DetailId());
        assertThat(claimsCob835Details1).isEqualTo(claimsCob835Details2);
        claimsCob835Details2.setClaimCob835DetailId(2L);
        assertThat(claimsCob835Details1).isNotEqualTo(claimsCob835Details2);
        claimsCob835Details1.setClaimCob835DetailId(null);
        assertThat(claimsCob835Details1).isNotEqualTo(claimsCob835Details2);
    }
}

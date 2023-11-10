package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsStatus277MasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsStatus277Master.class);
        ClaimsStatus277Master claimsStatus277Master1 = new ClaimsStatus277Master();
        claimsStatus277Master1.setClaimStatus277MasterId(1L);
        ClaimsStatus277Master claimsStatus277Master2 = new ClaimsStatus277Master();
        claimsStatus277Master2.setClaimStatus277MasterId(claimsStatus277Master1.getClaimStatus277MasterId());
        assertThat(claimsStatus277Master1).isEqualTo(claimsStatus277Master2);
        claimsStatus277Master2.setClaimStatus277MasterId(2L);
        assertThat(claimsStatus277Master1).isNotEqualTo(claimsStatus277Master2);
        claimsStatus277Master1.setClaimStatus277MasterId(null);
        assertThat(claimsStatus277Master1).isNotEqualTo(claimsStatus277Master2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimProgramMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimProgramMaster.class);
        ClaimProgramMaster claimProgramMaster1 = new ClaimProgramMaster();
        claimProgramMaster1.setClaimProgramMasterId(1L);
        ClaimProgramMaster claimProgramMaster2 = new ClaimProgramMaster();
        claimProgramMaster2.setClaimProgramMasterId(claimProgramMaster1.getClaimProgramMasterId());
        assertThat(claimProgramMaster1).isEqualTo(claimProgramMaster2);
        claimProgramMaster2.setClaimProgramMasterId(2L);
        assertThat(claimProgramMaster1).isNotEqualTo(claimProgramMaster2);
        claimProgramMaster1.setClaimProgramMasterId(null);
        assertThat(claimProgramMaster1).isNotEqualTo(claimProgramMaster2);
    }
}

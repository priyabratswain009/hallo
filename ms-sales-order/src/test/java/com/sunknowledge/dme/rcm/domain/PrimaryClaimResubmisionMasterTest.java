package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimResubmisionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimResubmisionMaster.class);
        PrimaryClaimResubmisionMaster primaryClaimResubmisionMaster1 = new PrimaryClaimResubmisionMaster();
        primaryClaimResubmisionMaster1.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimResubmisionMaster primaryClaimResubmisionMaster2 = new PrimaryClaimResubmisionMaster();
        primaryClaimResubmisionMaster2.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimResubmisionMaster1.getChangeHealthPrimaryResubmisionMasterId()
        );
        assertThat(primaryClaimResubmisionMaster1).isEqualTo(primaryClaimResubmisionMaster2);
        primaryClaimResubmisionMaster2.setChangeHealthPrimaryResubmisionMasterId(2L);
        assertThat(primaryClaimResubmisionMaster1).isNotEqualTo(primaryClaimResubmisionMaster2);
        primaryClaimResubmisionMaster1.setChangeHealthPrimaryResubmisionMasterId(null);
        assertThat(primaryClaimResubmisionMaster1).isNotEqualTo(primaryClaimResubmisionMaster2);
    }
}

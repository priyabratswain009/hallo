package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimSubmisionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimSubmisionMaster.class);
        PrimaryClaimSubmisionMaster primaryClaimSubmisionMaster1 = new PrimaryClaimSubmisionMaster();
        primaryClaimSubmisionMaster1.setChangeHealthPrimarySubmisionMasterId(1L);
        PrimaryClaimSubmisionMaster primaryClaimSubmisionMaster2 = new PrimaryClaimSubmisionMaster();
        primaryClaimSubmisionMaster2.setChangeHealthPrimarySubmisionMasterId(
            primaryClaimSubmisionMaster1.getChangeHealthPrimarySubmisionMasterId()
        );
        assertThat(primaryClaimSubmisionMaster1).isEqualTo(primaryClaimSubmisionMaster2);
        primaryClaimSubmisionMaster2.setChangeHealthPrimarySubmisionMasterId(2L);
        assertThat(primaryClaimSubmisionMaster1).isNotEqualTo(primaryClaimSubmisionMaster2);
        primaryClaimSubmisionMaster1.setChangeHealthPrimarySubmisionMasterId(null);
        assertThat(primaryClaimSubmisionMaster1).isNotEqualTo(primaryClaimSubmisionMaster2);
    }
}

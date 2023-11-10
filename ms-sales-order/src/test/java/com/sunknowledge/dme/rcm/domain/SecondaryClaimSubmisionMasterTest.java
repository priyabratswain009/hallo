package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionMaster.class);
        SecondaryClaimSubmisionMaster secondaryClaimSubmisionMaster1 = new SecondaryClaimSubmisionMaster();
        secondaryClaimSubmisionMaster1.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimSubmisionMaster secondaryClaimSubmisionMaster2 = new SecondaryClaimSubmisionMaster();
        secondaryClaimSubmisionMaster2.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimSubmisionMaster1.getChangeHealthSecondarySubmisionMasterId()
        );
        assertThat(secondaryClaimSubmisionMaster1).isEqualTo(secondaryClaimSubmisionMaster2);
        secondaryClaimSubmisionMaster2.setChangeHealthSecondarySubmisionMasterId(2L);
        assertThat(secondaryClaimSubmisionMaster1).isNotEqualTo(secondaryClaimSubmisionMaster2);
        secondaryClaimSubmisionMaster1.setChangeHealthSecondarySubmisionMasterId(null);
        assertThat(secondaryClaimSubmisionMaster1).isNotEqualTo(secondaryClaimSubmisionMaster2);
    }
}

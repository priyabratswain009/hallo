package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimsSubmissionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimsSubmissionMaster.class);
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster1 = new SecondaryClaimsSubmissionMaster();
        secondaryClaimsSubmissionMaster1.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster2 = new SecondaryClaimsSubmissionMaster();
        secondaryClaimsSubmissionMaster2.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimsSubmissionMaster1.getChangeHealthSecondarySubmisionMasterId()
        );
        assertThat(secondaryClaimsSubmissionMaster1).isEqualTo(secondaryClaimsSubmissionMaster2);
        secondaryClaimsSubmissionMaster2.setChangeHealthSecondarySubmisionMasterId(2L);
        assertThat(secondaryClaimsSubmissionMaster1).isNotEqualTo(secondaryClaimsSubmissionMaster2);
        secondaryClaimsSubmissionMaster1.setChangeHealthSecondarySubmisionMasterId(null);
        assertThat(secondaryClaimsSubmissionMaster1).isNotEqualTo(secondaryClaimsSubmissionMaster2);
    }
}

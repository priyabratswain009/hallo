package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimsReSubmissionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimsReSubmissionMaster.class);
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster1 = new PrimaryClaimsReSubmissionMaster();
        primaryClaimsReSubmissionMaster1.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster2 = new PrimaryClaimsReSubmissionMaster();
        primaryClaimsReSubmissionMaster2.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimsReSubmissionMaster1.getChangeHealthPrimaryResubmisionMasterId()
        );
        assertThat(primaryClaimsReSubmissionMaster1).isEqualTo(primaryClaimsReSubmissionMaster2);
        primaryClaimsReSubmissionMaster2.setChangeHealthPrimaryResubmisionMasterId(2L);
        assertThat(primaryClaimsReSubmissionMaster1).isNotEqualTo(primaryClaimsReSubmissionMaster2);
        primaryClaimsReSubmissionMaster1.setChangeHealthPrimaryResubmisionMasterId(null);
        assertThat(primaryClaimsReSubmissionMaster1).isNotEqualTo(primaryClaimsReSubmissionMaster2);
    }
}

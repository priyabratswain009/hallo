package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsSubmissionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsSubmissionMaster.class);
        ClaimsSubmissionMaster claimsSubmissionMaster1 = new ClaimsSubmissionMaster();
        claimsSubmissionMaster1.setChangeHealthPrimarySubmisionMasterId(1L);
        ClaimsSubmissionMaster claimsSubmissionMaster2 = new ClaimsSubmissionMaster();
        claimsSubmissionMaster2.setChangeHealthPrimarySubmisionMasterId(claimsSubmissionMaster1.getChangeHealthPrimarySubmisionMasterId());
        assertThat(claimsSubmissionMaster1).isEqualTo(claimsSubmissionMaster2);
        claimsSubmissionMaster2.setChangeHealthPrimarySubmisionMasterId(2L);
        assertThat(claimsSubmissionMaster1).isNotEqualTo(claimsSubmissionMaster2);
        claimsSubmissionMaster1.setChangeHealthPrimarySubmisionMasterId(null);
        assertThat(claimsSubmissionMaster1).isNotEqualTo(claimsSubmissionMaster2);
    }
}

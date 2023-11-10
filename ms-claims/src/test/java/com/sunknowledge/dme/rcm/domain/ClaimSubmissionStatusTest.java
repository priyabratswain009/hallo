package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimSubmissionStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimSubmissionStatus.class);
        ClaimSubmissionStatus claimSubmissionStatus1 = new ClaimSubmissionStatus();
        claimSubmissionStatus1.setClaimStatusId(1L);
        ClaimSubmissionStatus claimSubmissionStatus2 = new ClaimSubmissionStatus();
        claimSubmissionStatus2.setClaimStatusId(claimSubmissionStatus1.getClaimStatusId());
        assertThat(claimSubmissionStatus1).isEqualTo(claimSubmissionStatus2);
        claimSubmissionStatus2.setClaimStatusId(2L);
        assertThat(claimSubmissionStatus1).isNotEqualTo(claimSubmissionStatus2);
        claimSubmissionStatus1.setClaimStatusId(null);
        assertThat(claimSubmissionStatus1).isNotEqualTo(claimSubmissionStatus2);
    }
}

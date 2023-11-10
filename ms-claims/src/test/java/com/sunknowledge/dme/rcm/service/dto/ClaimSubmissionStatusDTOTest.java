package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimSubmissionStatusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimSubmissionStatusDTO.class);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO1 = new ClaimSubmissionStatusDTO();
        claimSubmissionStatusDTO1.setClaimStatusId(1L);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO2 = new ClaimSubmissionStatusDTO();
        assertThat(claimSubmissionStatusDTO1).isNotEqualTo(claimSubmissionStatusDTO2);
        claimSubmissionStatusDTO2.setClaimStatusId(claimSubmissionStatusDTO1.getClaimStatusId());
        assertThat(claimSubmissionStatusDTO1).isEqualTo(claimSubmissionStatusDTO2);
        claimSubmissionStatusDTO2.setClaimStatusId(2L);
        assertThat(claimSubmissionStatusDTO1).isNotEqualTo(claimSubmissionStatusDTO2);
        claimSubmissionStatusDTO1.setClaimStatusId(null);
        assertThat(claimSubmissionStatusDTO1).isNotEqualTo(claimSubmissionStatusDTO2);
    }
}

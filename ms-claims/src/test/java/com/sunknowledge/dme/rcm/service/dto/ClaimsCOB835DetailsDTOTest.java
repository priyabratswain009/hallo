package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCOB835DetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCOB835DetailsDTO.class);
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO1 = new ClaimsCOB835DetailsDTO();
        claimsCOB835DetailsDTO1.setClaimCob835DetailId(1L);
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO2 = new ClaimsCOB835DetailsDTO();
        assertThat(claimsCOB835DetailsDTO1).isNotEqualTo(claimsCOB835DetailsDTO2);
        claimsCOB835DetailsDTO2.setClaimCob835DetailId(claimsCOB835DetailsDTO1.getClaimCob835DetailId());
        assertThat(claimsCOB835DetailsDTO1).isEqualTo(claimsCOB835DetailsDTO2);
        claimsCOB835DetailsDTO2.setClaimCob835DetailId(2L);
        assertThat(claimsCOB835DetailsDTO1).isNotEqualTo(claimsCOB835DetailsDTO2);
        claimsCOB835DetailsDTO1.setClaimCob835DetailId(null);
        assertThat(claimsCOB835DetailsDTO1).isNotEqualTo(claimsCOB835DetailsDTO2);
    }
}

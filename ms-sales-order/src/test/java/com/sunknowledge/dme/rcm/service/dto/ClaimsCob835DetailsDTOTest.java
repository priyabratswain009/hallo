package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCob835DetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCob835DetailsDTO.class);
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO1 = new ClaimsCob835DetailsDTO();
        claimsCob835DetailsDTO1.setClaimCob835DetailId(1L);
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO2 = new ClaimsCob835DetailsDTO();
        assertThat(claimsCob835DetailsDTO1).isNotEqualTo(claimsCob835DetailsDTO2);
        claimsCob835DetailsDTO2.setClaimCob835DetailId(claimsCob835DetailsDTO1.getClaimCob835DetailId());
        assertThat(claimsCob835DetailsDTO1).isEqualTo(claimsCob835DetailsDTO2);
        claimsCob835DetailsDTO2.setClaimCob835DetailId(2L);
        assertThat(claimsCob835DetailsDTO1).isNotEqualTo(claimsCob835DetailsDTO2);
        claimsCob835DetailsDTO1.setClaimCob835DetailId(null);
        assertThat(claimsCob835DetailsDTO1).isNotEqualTo(claimsCob835DetailsDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsStatus277DetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsStatus277DetailsDTO.class);
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO1 = new ClaimsStatus277DetailsDTO();
        claimsStatus277DetailsDTO1.setClaimStatus277DetailId(1L);
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO2 = new ClaimsStatus277DetailsDTO();
        assertThat(claimsStatus277DetailsDTO1).isNotEqualTo(claimsStatus277DetailsDTO2);
        claimsStatus277DetailsDTO2.setClaimStatus277DetailId(claimsStatus277DetailsDTO1.getClaimStatus277DetailId());
        assertThat(claimsStatus277DetailsDTO1).isEqualTo(claimsStatus277DetailsDTO2);
        claimsStatus277DetailsDTO2.setClaimStatus277DetailId(2L);
        assertThat(claimsStatus277DetailsDTO1).isNotEqualTo(claimsStatus277DetailsDTO2);
        claimsStatus277DetailsDTO1.setClaimStatus277DetailId(null);
        assertThat(claimsStatus277DetailsDTO1).isNotEqualTo(claimsStatus277DetailsDTO2);
    }
}

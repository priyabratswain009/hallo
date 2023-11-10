package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsStatus277MasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsStatus277MasterDTO.class);
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO1 = new ClaimsStatus277MasterDTO();
        claimsStatus277MasterDTO1.setClaimStatus277MasterId(1L);
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO2 = new ClaimsStatus277MasterDTO();
        assertThat(claimsStatus277MasterDTO1).isNotEqualTo(claimsStatus277MasterDTO2);
        claimsStatus277MasterDTO2.setClaimStatus277MasterId(claimsStatus277MasterDTO1.getClaimStatus277MasterId());
        assertThat(claimsStatus277MasterDTO1).isEqualTo(claimsStatus277MasterDTO2);
        claimsStatus277MasterDTO2.setClaimStatus277MasterId(2L);
        assertThat(claimsStatus277MasterDTO1).isNotEqualTo(claimsStatus277MasterDTO2);
        claimsStatus277MasterDTO1.setClaimStatus277MasterId(null);
        assertThat(claimsStatus277MasterDTO1).isNotEqualTo(claimsStatus277MasterDTO2);
    }
}

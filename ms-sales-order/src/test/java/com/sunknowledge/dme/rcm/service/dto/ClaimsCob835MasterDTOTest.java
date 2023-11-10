package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCob835MasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCob835MasterDTO.class);
        ClaimsCob835MasterDTO claimsCob835MasterDTO1 = new ClaimsCob835MasterDTO();
        claimsCob835MasterDTO1.setClaimCob835MasterId(1L);
        ClaimsCob835MasterDTO claimsCob835MasterDTO2 = new ClaimsCob835MasterDTO();
        assertThat(claimsCob835MasterDTO1).isNotEqualTo(claimsCob835MasterDTO2);
        claimsCob835MasterDTO2.setClaimCob835MasterId(claimsCob835MasterDTO1.getClaimCob835MasterId());
        assertThat(claimsCob835MasterDTO1).isEqualTo(claimsCob835MasterDTO2);
        claimsCob835MasterDTO2.setClaimCob835MasterId(2L);
        assertThat(claimsCob835MasterDTO1).isNotEqualTo(claimsCob835MasterDTO2);
        claimsCob835MasterDTO1.setClaimCob835MasterId(null);
        assertThat(claimsCob835MasterDTO1).isNotEqualTo(claimsCob835MasterDTO2);
    }
}

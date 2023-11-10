package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimProgramMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimProgramMasterDTO.class);
        ClaimProgramMasterDTO claimProgramMasterDTO1 = new ClaimProgramMasterDTO();
        claimProgramMasterDTO1.setClaimProgramMasterId(1L);
        ClaimProgramMasterDTO claimProgramMasterDTO2 = new ClaimProgramMasterDTO();
        assertThat(claimProgramMasterDTO1).isNotEqualTo(claimProgramMasterDTO2);
        claimProgramMasterDTO2.setClaimProgramMasterId(claimProgramMasterDTO1.getClaimProgramMasterId());
        assertThat(claimProgramMasterDTO1).isEqualTo(claimProgramMasterDTO2);
        claimProgramMasterDTO2.setClaimProgramMasterId(2L);
        assertThat(claimProgramMasterDTO1).isNotEqualTo(claimProgramMasterDTO2);
        claimProgramMasterDTO1.setClaimProgramMasterId(null);
        assertThat(claimProgramMasterDTO1).isNotEqualTo(claimProgramMasterDTO2);
    }
}

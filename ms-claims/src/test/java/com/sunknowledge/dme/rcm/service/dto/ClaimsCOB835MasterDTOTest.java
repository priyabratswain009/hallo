package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsCOB835MasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsCOB835MasterDTO.class);
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO1 = new ClaimsCOB835MasterDTO();
        claimsCOB835MasterDTO1.setClaimCob835MasterId(1L);
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO2 = new ClaimsCOB835MasterDTO();
        assertThat(claimsCOB835MasterDTO1).isNotEqualTo(claimsCOB835MasterDTO2);
        claimsCOB835MasterDTO2.setClaimCob835MasterId(claimsCOB835MasterDTO1.getClaimCob835MasterId());
        assertThat(claimsCOB835MasterDTO1).isEqualTo(claimsCOB835MasterDTO2);
        claimsCOB835MasterDTO2.setClaimCob835MasterId(2L);
        assertThat(claimsCOB835MasterDTO1).isNotEqualTo(claimsCOB835MasterDTO2);
        claimsCOB835MasterDTO1.setClaimCob835MasterId(null);
        assertThat(claimsCOB835MasterDTO1).isNotEqualTo(claimsCOB835MasterDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimFormMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimFormMasterDTO.class);
        ClaimFormMasterDTO claimFormMasterDTO1 = new ClaimFormMasterDTO();
        claimFormMasterDTO1.setClaimFormId(1L);
        ClaimFormMasterDTO claimFormMasterDTO2 = new ClaimFormMasterDTO();
        assertThat(claimFormMasterDTO1).isNotEqualTo(claimFormMasterDTO2);
        claimFormMasterDTO2.setClaimFormId(claimFormMasterDTO1.getClaimFormId());
        assertThat(claimFormMasterDTO1).isEqualTo(claimFormMasterDTO2);
        claimFormMasterDTO2.setClaimFormId(2L);
        assertThat(claimFormMasterDTO1).isNotEqualTo(claimFormMasterDTO2);
        claimFormMasterDTO1.setClaimFormId(null);
        assertThat(claimFormMasterDTO1).isNotEqualTo(claimFormMasterDTO2);
    }
}

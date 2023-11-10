package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimResubmisionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimResubmisionMasterDTO.class);
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO1 = new PrimaryClaimResubmisionMasterDTO();
        primaryClaimResubmisionMasterDTO1.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO2 = new PrimaryClaimResubmisionMasterDTO();
        assertThat(primaryClaimResubmisionMasterDTO1).isNotEqualTo(primaryClaimResubmisionMasterDTO2);
        primaryClaimResubmisionMasterDTO2.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimResubmisionMasterDTO1.getChangeHealthPrimaryResubmisionMasterId()
        );
        assertThat(primaryClaimResubmisionMasterDTO1).isEqualTo(primaryClaimResubmisionMasterDTO2);
        primaryClaimResubmisionMasterDTO2.setChangeHealthPrimaryResubmisionMasterId(2L);
        assertThat(primaryClaimResubmisionMasterDTO1).isNotEqualTo(primaryClaimResubmisionMasterDTO2);
        primaryClaimResubmisionMasterDTO1.setChangeHealthPrimaryResubmisionMasterId(null);
        assertThat(primaryClaimResubmisionMasterDTO1).isNotEqualTo(primaryClaimResubmisionMasterDTO2);
    }
}

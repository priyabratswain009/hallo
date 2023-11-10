package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimSubmisionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimSubmisionMasterDTO.class);
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO1 = new PrimaryClaimSubmisionMasterDTO();
        primaryClaimSubmisionMasterDTO1.setChangeHealthPrimarySubmisionMasterId(1L);
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO2 = new PrimaryClaimSubmisionMasterDTO();
        assertThat(primaryClaimSubmisionMasterDTO1).isNotEqualTo(primaryClaimSubmisionMasterDTO2);
        primaryClaimSubmisionMasterDTO2.setChangeHealthPrimarySubmisionMasterId(
            primaryClaimSubmisionMasterDTO1.getChangeHealthPrimarySubmisionMasterId()
        );
        assertThat(primaryClaimSubmisionMasterDTO1).isEqualTo(primaryClaimSubmisionMasterDTO2);
        primaryClaimSubmisionMasterDTO2.setChangeHealthPrimarySubmisionMasterId(2L);
        assertThat(primaryClaimSubmisionMasterDTO1).isNotEqualTo(primaryClaimSubmisionMasterDTO2);
        primaryClaimSubmisionMasterDTO1.setChangeHealthPrimarySubmisionMasterId(null);
        assertThat(primaryClaimSubmisionMasterDTO1).isNotEqualTo(primaryClaimSubmisionMasterDTO2);
    }
}

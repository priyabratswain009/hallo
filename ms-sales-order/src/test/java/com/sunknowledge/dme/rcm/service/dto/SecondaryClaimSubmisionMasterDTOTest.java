package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionMasterDTO.class);
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO1 = new SecondaryClaimSubmisionMasterDTO();
        secondaryClaimSubmisionMasterDTO1.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO2 = new SecondaryClaimSubmisionMasterDTO();
        assertThat(secondaryClaimSubmisionMasterDTO1).isNotEqualTo(secondaryClaimSubmisionMasterDTO2);
        secondaryClaimSubmisionMasterDTO2.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimSubmisionMasterDTO1.getChangeHealthSecondarySubmisionMasterId()
        );
        assertThat(secondaryClaimSubmisionMasterDTO1).isEqualTo(secondaryClaimSubmisionMasterDTO2);
        secondaryClaimSubmisionMasterDTO2.setChangeHealthSecondarySubmisionMasterId(2L);
        assertThat(secondaryClaimSubmisionMasterDTO1).isNotEqualTo(secondaryClaimSubmisionMasterDTO2);
        secondaryClaimSubmisionMasterDTO1.setChangeHealthSecondarySubmisionMasterId(null);
        assertThat(secondaryClaimSubmisionMasterDTO1).isNotEqualTo(secondaryClaimSubmisionMasterDTO2);
    }
}

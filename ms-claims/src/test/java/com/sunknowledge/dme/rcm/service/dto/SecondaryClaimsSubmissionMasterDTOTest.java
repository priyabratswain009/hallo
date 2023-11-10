package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimsSubmissionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimsSubmissionMasterDTO.class);
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO1 = new SecondaryClaimsSubmissionMasterDTO();
        secondaryClaimsSubmissionMasterDTO1.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO2 = new SecondaryClaimsSubmissionMasterDTO();
        assertThat(secondaryClaimsSubmissionMasterDTO1).isNotEqualTo(secondaryClaimsSubmissionMasterDTO2);
        secondaryClaimsSubmissionMasterDTO2.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimsSubmissionMasterDTO1.getChangeHealthSecondarySubmisionMasterId()
        );
        assertThat(secondaryClaimsSubmissionMasterDTO1).isEqualTo(secondaryClaimsSubmissionMasterDTO2);
        secondaryClaimsSubmissionMasterDTO2.setChangeHealthSecondarySubmisionMasterId(2L);
        assertThat(secondaryClaimsSubmissionMasterDTO1).isNotEqualTo(secondaryClaimsSubmissionMasterDTO2);
        secondaryClaimsSubmissionMasterDTO1.setChangeHealthSecondarySubmisionMasterId(null);
        assertThat(secondaryClaimsSubmissionMasterDTO1).isNotEqualTo(secondaryClaimsSubmissionMasterDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsSubmissionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsSubmissionMasterDTO.class);
        ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO1 = new ClaimsSubmissionMasterDTO();
        claimsSubmissionMasterDTO1.setChangeHealthPrimarySubmisionMasterId(1L);
        ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO2 = new ClaimsSubmissionMasterDTO();
        assertThat(claimsSubmissionMasterDTO1).isNotEqualTo(claimsSubmissionMasterDTO2);
        claimsSubmissionMasterDTO2.setChangeHealthPrimarySubmisionMasterId(
            claimsSubmissionMasterDTO1.getChangeHealthPrimarySubmisionMasterId()
        );
        assertThat(claimsSubmissionMasterDTO1).isEqualTo(claimsSubmissionMasterDTO2);
        claimsSubmissionMasterDTO2.setChangeHealthPrimarySubmisionMasterId(2L);
        assertThat(claimsSubmissionMasterDTO1).isNotEqualTo(claimsSubmissionMasterDTO2);
        claimsSubmissionMasterDTO1.setChangeHealthPrimarySubmisionMasterId(null);
        assertThat(claimsSubmissionMasterDTO1).isNotEqualTo(claimsSubmissionMasterDTO2);
    }
}

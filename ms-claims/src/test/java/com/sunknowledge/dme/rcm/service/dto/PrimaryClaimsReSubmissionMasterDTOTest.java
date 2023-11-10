package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimsReSubmissionMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimsReSubmissionMasterDTO.class);
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO1 = new PrimaryClaimsReSubmissionMasterDTO();
        primaryClaimsReSubmissionMasterDTO1.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO2 = new PrimaryClaimsReSubmissionMasterDTO();
        assertThat(primaryClaimsReSubmissionMasterDTO1).isNotEqualTo(primaryClaimsReSubmissionMasterDTO2);
        primaryClaimsReSubmissionMasterDTO2.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimsReSubmissionMasterDTO1.getChangeHealthPrimaryResubmisionMasterId()
        );
        assertThat(primaryClaimsReSubmissionMasterDTO1).isEqualTo(primaryClaimsReSubmissionMasterDTO2);
        primaryClaimsReSubmissionMasterDTO2.setChangeHealthPrimaryResubmisionMasterId(2L);
        assertThat(primaryClaimsReSubmissionMasterDTO1).isNotEqualTo(primaryClaimsReSubmissionMasterDTO2);
        primaryClaimsReSubmissionMasterDTO1.setChangeHealthPrimaryResubmisionMasterId(null);
        assertThat(primaryClaimsReSubmissionMasterDTO1).isNotEqualTo(primaryClaimsReSubmissionMasterDTO2);
    }
}

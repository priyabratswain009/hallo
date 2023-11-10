package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimResubmisionServicelinesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimResubmisionServicelinesDTO.class);
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO1 = new PrimaryClaimResubmisionServicelinesDTO();
        primaryClaimResubmisionServicelinesDTO1.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO2 = new PrimaryClaimResubmisionServicelinesDTO();
        assertThat(primaryClaimResubmisionServicelinesDTO1).isNotEqualTo(primaryClaimResubmisionServicelinesDTO2);
        primaryClaimResubmisionServicelinesDTO2.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryClaimResubmisionServicelinesDTO1.getChangeHealthPrimaryResubmisionServicelinesId()
        );
        assertThat(primaryClaimResubmisionServicelinesDTO1).isEqualTo(primaryClaimResubmisionServicelinesDTO2);
        primaryClaimResubmisionServicelinesDTO2.setChangeHealthPrimaryResubmisionServicelinesId(2L);
        assertThat(primaryClaimResubmisionServicelinesDTO1).isNotEqualTo(primaryClaimResubmisionServicelinesDTO2);
        primaryClaimResubmisionServicelinesDTO1.setChangeHealthPrimaryResubmisionServicelinesId(null);
        assertThat(primaryClaimResubmisionServicelinesDTO1).isNotEqualTo(primaryClaimResubmisionServicelinesDTO2);
    }
}

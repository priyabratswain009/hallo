package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimSubmisionServicelinesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimSubmisionServicelinesDTO.class);
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO1 = new PrimaryClaimSubmisionServicelinesDTO();
        primaryClaimSubmisionServicelinesDTO1.setChangeHealthPrimarySubmisionServicelinesId(1L);
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO2 = new PrimaryClaimSubmisionServicelinesDTO();
        assertThat(primaryClaimSubmisionServicelinesDTO1).isNotEqualTo(primaryClaimSubmisionServicelinesDTO2);
        primaryClaimSubmisionServicelinesDTO2.setChangeHealthPrimarySubmisionServicelinesId(
            primaryClaimSubmisionServicelinesDTO1.getChangeHealthPrimarySubmisionServicelinesId()
        );
        assertThat(primaryClaimSubmisionServicelinesDTO1).isEqualTo(primaryClaimSubmisionServicelinesDTO2);
        primaryClaimSubmisionServicelinesDTO2.setChangeHealthPrimarySubmisionServicelinesId(2L);
        assertThat(primaryClaimSubmisionServicelinesDTO1).isNotEqualTo(primaryClaimSubmisionServicelinesDTO2);
        primaryClaimSubmisionServicelinesDTO1.setChangeHealthPrimarySubmisionServicelinesId(null);
        assertThat(primaryClaimSubmisionServicelinesDTO1).isNotEqualTo(primaryClaimSubmisionServicelinesDTO2);
    }
}

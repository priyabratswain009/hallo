package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionServicelinesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionServicelinesDTO.class);
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO1 = new SecondaryClaimSubmisionServicelinesDTO();
        secondaryClaimSubmisionServicelinesDTO1.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO2 = new SecondaryClaimSubmisionServicelinesDTO();
        assertThat(secondaryClaimSubmisionServicelinesDTO1).isNotEqualTo(secondaryClaimSubmisionServicelinesDTO2);
        secondaryClaimSubmisionServicelinesDTO2.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryClaimSubmisionServicelinesDTO1.getChangeHealthSecondarySubmisionServicelinesId()
        );
        assertThat(secondaryClaimSubmisionServicelinesDTO1).isEqualTo(secondaryClaimSubmisionServicelinesDTO2);
        secondaryClaimSubmisionServicelinesDTO2.setChangeHealthSecondarySubmisionServicelinesId(2L);
        assertThat(secondaryClaimSubmisionServicelinesDTO1).isNotEqualTo(secondaryClaimSubmisionServicelinesDTO2);
        secondaryClaimSubmisionServicelinesDTO1.setChangeHealthSecondarySubmisionServicelinesId(null);
        assertThat(secondaryClaimSubmisionServicelinesDTO1).isNotEqualTo(secondaryClaimSubmisionServicelinesDTO2);
    }
}

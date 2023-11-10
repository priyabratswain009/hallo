package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionServicelinesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionServicelines.class);
        SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelines1 = new SecondaryClaimSubmisionServicelines();
        secondaryClaimSubmisionServicelines1.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelines2 = new SecondaryClaimSubmisionServicelines();
        secondaryClaimSubmisionServicelines2.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryClaimSubmisionServicelines1.getChangeHealthSecondarySubmisionServicelinesId()
        );
        assertThat(secondaryClaimSubmisionServicelines1).isEqualTo(secondaryClaimSubmisionServicelines2);
        secondaryClaimSubmisionServicelines2.setChangeHealthSecondarySubmisionServicelinesId(2L);
        assertThat(secondaryClaimSubmisionServicelines1).isNotEqualTo(secondaryClaimSubmisionServicelines2);
        secondaryClaimSubmisionServicelines1.setChangeHealthSecondarySubmisionServicelinesId(null);
        assertThat(secondaryClaimSubmisionServicelines1).isNotEqualTo(secondaryClaimSubmisionServicelines2);
    }
}

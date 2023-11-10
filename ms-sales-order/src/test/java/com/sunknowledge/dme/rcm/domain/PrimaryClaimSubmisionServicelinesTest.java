package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimSubmisionServicelinesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimSubmisionServicelines.class);
        PrimaryClaimSubmisionServicelines primaryClaimSubmisionServicelines1 = new PrimaryClaimSubmisionServicelines();
        primaryClaimSubmisionServicelines1.setChangeHealthPrimarySubmisionServicelinesId(1L);
        PrimaryClaimSubmisionServicelines primaryClaimSubmisionServicelines2 = new PrimaryClaimSubmisionServicelines();
        primaryClaimSubmisionServicelines2.setChangeHealthPrimarySubmisionServicelinesId(
            primaryClaimSubmisionServicelines1.getChangeHealthPrimarySubmisionServicelinesId()
        );
        assertThat(primaryClaimSubmisionServicelines1).isEqualTo(primaryClaimSubmisionServicelines2);
        primaryClaimSubmisionServicelines2.setChangeHealthPrimarySubmisionServicelinesId(2L);
        assertThat(primaryClaimSubmisionServicelines1).isNotEqualTo(primaryClaimSubmisionServicelines2);
        primaryClaimSubmisionServicelines1.setChangeHealthPrimarySubmisionServicelinesId(null);
        assertThat(primaryClaimSubmisionServicelines1).isNotEqualTo(primaryClaimSubmisionServicelines2);
    }
}

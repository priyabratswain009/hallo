package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryClaimResubmisionServicelinesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryClaimResubmisionServicelines.class);
        PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelines1 = new PrimaryClaimResubmisionServicelines();
        primaryClaimResubmisionServicelines1.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelines2 = new PrimaryClaimResubmisionServicelines();
        primaryClaimResubmisionServicelines2.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryClaimResubmisionServicelines1.getChangeHealthPrimaryResubmisionServicelinesId()
        );
        assertThat(primaryClaimResubmisionServicelines1).isEqualTo(primaryClaimResubmisionServicelines2);
        primaryClaimResubmisionServicelines2.setChangeHealthPrimaryResubmisionServicelinesId(2L);
        assertThat(primaryClaimResubmisionServicelines1).isNotEqualTo(primaryClaimResubmisionServicelines2);
        primaryClaimResubmisionServicelines1.setChangeHealthPrimaryResubmisionServicelinesId(null);
        assertThat(primaryClaimResubmisionServicelines1).isNotEqualTo(primaryClaimResubmisionServicelines2);
    }
}

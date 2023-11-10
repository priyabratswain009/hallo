package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseBenefitinformationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponseBenefitinformation.class);
        ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformation1 = new ElligibilityResponseBenefitinformation();
        elligibilityResponseBenefitinformation1.setElligibilityResponseBenefitinformationId(1L);
        ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformation2 = new ElligibilityResponseBenefitinformation();
        elligibilityResponseBenefitinformation2.setElligibilityResponseBenefitinformationId(
            elligibilityResponseBenefitinformation1.getElligibilityResponseBenefitinformationId()
        );
        assertThat(elligibilityResponseBenefitinformation1).isEqualTo(elligibilityResponseBenefitinformation2);
        elligibilityResponseBenefitinformation2.setElligibilityResponseBenefitinformationId(2L);
        assertThat(elligibilityResponseBenefitinformation1).isNotEqualTo(elligibilityResponseBenefitinformation2);
        elligibilityResponseBenefitinformation1.setElligibilityResponseBenefitinformationId(null);
        assertThat(elligibilityResponseBenefitinformation1).isNotEqualTo(elligibilityResponseBenefitinformation2);
    }
}

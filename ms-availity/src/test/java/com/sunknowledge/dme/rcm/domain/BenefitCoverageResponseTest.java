package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BenefitCoverageResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenefitCoverageResponse.class);
        BenefitCoverageResponse benefitCoverageResponse1 = new BenefitCoverageResponse();
        benefitCoverageResponse1.setBenefitCoverageResponseId(1L);
        BenefitCoverageResponse benefitCoverageResponse2 = new BenefitCoverageResponse();
        benefitCoverageResponse2.setBenefitCoverageResponseId(benefitCoverageResponse1.getBenefitCoverageResponseId());
        assertThat(benefitCoverageResponse1).isEqualTo(benefitCoverageResponse2);
        benefitCoverageResponse2.setBenefitCoverageResponseId(2L);
        assertThat(benefitCoverageResponse1).isNotEqualTo(benefitCoverageResponse2);
        benefitCoverageResponse1.setBenefitCoverageResponseId(null);
        assertThat(benefitCoverageResponse1).isNotEqualTo(benefitCoverageResponse2);
    }
}

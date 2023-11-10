package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BenefitCoverageRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenefitCoverageRequest.class);
        BenefitCoverageRequest benefitCoverageRequest1 = new BenefitCoverageRequest();
        benefitCoverageRequest1.setBenefitCoverageRequestId(1L);
        BenefitCoverageRequest benefitCoverageRequest2 = new BenefitCoverageRequest();
        benefitCoverageRequest2.setBenefitCoverageRequestId(benefitCoverageRequest1.getBenefitCoverageRequestId());
        assertThat(benefitCoverageRequest1).isEqualTo(benefitCoverageRequest2);
        benefitCoverageRequest2.setBenefitCoverageRequestId(2L);
        assertThat(benefitCoverageRequest1).isNotEqualTo(benefitCoverageRequest2);
        benefitCoverageRequest1.setBenefitCoverageRequestId(null);
        assertThat(benefitCoverageRequest1).isNotEqualTo(benefitCoverageRequest2);
    }
}

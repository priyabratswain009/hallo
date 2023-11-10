package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BenefitCoverageRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenefitCoverageRequestDTO.class);
        BenefitCoverageRequestDTO benefitCoverageRequestDTO1 = new BenefitCoverageRequestDTO();
        benefitCoverageRequestDTO1.setBenefitCoverageRequestId(1L);
        BenefitCoverageRequestDTO benefitCoverageRequestDTO2 = new BenefitCoverageRequestDTO();
        assertThat(benefitCoverageRequestDTO1).isNotEqualTo(benefitCoverageRequestDTO2);
        benefitCoverageRequestDTO2.setBenefitCoverageRequestId(benefitCoverageRequestDTO1.getBenefitCoverageRequestId());
        assertThat(benefitCoverageRequestDTO1).isEqualTo(benefitCoverageRequestDTO2);
        benefitCoverageRequestDTO2.setBenefitCoverageRequestId(2L);
        assertThat(benefitCoverageRequestDTO1).isNotEqualTo(benefitCoverageRequestDTO2);
        benefitCoverageRequestDTO1.setBenefitCoverageRequestId(null);
        assertThat(benefitCoverageRequestDTO1).isNotEqualTo(benefitCoverageRequestDTO2);
    }
}

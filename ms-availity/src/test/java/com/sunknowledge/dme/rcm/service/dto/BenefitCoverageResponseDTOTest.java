package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BenefitCoverageResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenefitCoverageResponseDTO.class);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO1 = new BenefitCoverageResponseDTO();
        benefitCoverageResponseDTO1.setBenefitCoverageResponseId(1L);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO2 = new BenefitCoverageResponseDTO();
        assertThat(benefitCoverageResponseDTO1).isNotEqualTo(benefitCoverageResponseDTO2);
        benefitCoverageResponseDTO2.setBenefitCoverageResponseId(benefitCoverageResponseDTO1.getBenefitCoverageResponseId());
        assertThat(benefitCoverageResponseDTO1).isEqualTo(benefitCoverageResponseDTO2);
        benefitCoverageResponseDTO2.setBenefitCoverageResponseId(2L);
        assertThat(benefitCoverageResponseDTO1).isNotEqualTo(benefitCoverageResponseDTO2);
        benefitCoverageResponseDTO1.setBenefitCoverageResponseId(null);
        assertThat(benefitCoverageResponseDTO1).isNotEqualTo(benefitCoverageResponseDTO2);
    }
}

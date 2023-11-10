package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseBenefitinformationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponseBenefitinformationDTO.class);
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO1 = new ElligibilityResponseBenefitinformationDTO();
        elligibilityResponseBenefitinformationDTO1.setElligibilityResponseBenefitinformationId(1L);
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO2 = new ElligibilityResponseBenefitinformationDTO();
        assertThat(elligibilityResponseBenefitinformationDTO1).isNotEqualTo(elligibilityResponseBenefitinformationDTO2);
        elligibilityResponseBenefitinformationDTO2.setElligibilityResponseBenefitinformationId(
            elligibilityResponseBenefitinformationDTO1.getElligibilityResponseBenefitinformationId()
        );
        assertThat(elligibilityResponseBenefitinformationDTO1).isEqualTo(elligibilityResponseBenefitinformationDTO2);
        elligibilityResponseBenefitinformationDTO2.setElligibilityResponseBenefitinformationId(2L);
        assertThat(elligibilityResponseBenefitinformationDTO1).isNotEqualTo(elligibilityResponseBenefitinformationDTO2);
        elligibilityResponseBenefitinformationDTO1.setElligibilityResponseBenefitinformationId(null);
        assertThat(elligibilityResponseBenefitinformationDTO1).isNotEqualTo(elligibilityResponseBenefitinformationDTO2);
    }
}

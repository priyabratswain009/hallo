package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponseDTO.class);
        ElligibilityResponseDTO elligibilityResponseDTO1 = new ElligibilityResponseDTO();
        elligibilityResponseDTO1.setElligibilityResponseStatusId(1L);
        ElligibilityResponseDTO elligibilityResponseDTO2 = new ElligibilityResponseDTO();
        assertThat(elligibilityResponseDTO1).isNotEqualTo(elligibilityResponseDTO2);
        elligibilityResponseDTO2.setElligibilityResponseStatusId(elligibilityResponseDTO1.getElligibilityResponseStatusId());
        assertThat(elligibilityResponseDTO1).isEqualTo(elligibilityResponseDTO2);
        elligibilityResponseDTO2.setElligibilityResponseStatusId(2L);
        assertThat(elligibilityResponseDTO1).isNotEqualTo(elligibilityResponseDTO2);
        elligibilityResponseDTO1.setElligibilityResponseStatusId(null);
        assertThat(elligibilityResponseDTO1).isNotEqualTo(elligibilityResponseDTO2);
    }
}

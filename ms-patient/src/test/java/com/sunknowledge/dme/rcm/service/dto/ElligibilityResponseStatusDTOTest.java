package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseStatusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponseStatusDTO.class);
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO1 = new ElligibilityResponseStatusDTO();
        elligibilityResponseStatusDTO1.setElligibilityResponseStatusId(1L);
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO2 = new ElligibilityResponseStatusDTO();
        assertThat(elligibilityResponseStatusDTO1).isNotEqualTo(elligibilityResponseStatusDTO2);
        elligibilityResponseStatusDTO2.setElligibilityResponseStatusId(elligibilityResponseStatusDTO1.getElligibilityResponseStatusId());
        assertThat(elligibilityResponseStatusDTO1).isEqualTo(elligibilityResponseStatusDTO2);
        elligibilityResponseStatusDTO2.setElligibilityResponseStatusId(2L);
        assertThat(elligibilityResponseStatusDTO1).isNotEqualTo(elligibilityResponseStatusDTO2);
        elligibilityResponseStatusDTO1.setElligibilityResponseStatusId(null);
        assertThat(elligibilityResponseStatusDTO1).isNotEqualTo(elligibilityResponseStatusDTO2);
    }
}

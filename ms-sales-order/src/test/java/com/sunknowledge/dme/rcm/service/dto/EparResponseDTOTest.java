package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EparResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EparResponseDTO.class);
        EparResponseDTO eparResponseDTO1 = new EparResponseDTO();
        eparResponseDTO1.setEparResponseId(1L);
        EparResponseDTO eparResponseDTO2 = new EparResponseDTO();
        assertThat(eparResponseDTO1).isNotEqualTo(eparResponseDTO2);
        eparResponseDTO2.setEparResponseId(eparResponseDTO1.getEparResponseId());
        assertThat(eparResponseDTO1).isEqualTo(eparResponseDTO2);
        eparResponseDTO2.setEparResponseId(2L);
        assertThat(eparResponseDTO1).isNotEqualTo(eparResponseDTO2);
        eparResponseDTO1.setEparResponseId(null);
        assertThat(eparResponseDTO1).isNotEqualTo(eparResponseDTO2);
    }
}

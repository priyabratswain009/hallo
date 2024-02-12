package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EparRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EparRequestDTO.class);
        EparRequestDTO eparRequestDTO1 = new EparRequestDTO();
        eparRequestDTO1.setEparRequestId(1L);
        EparRequestDTO eparRequestDTO2 = new EparRequestDTO();
        assertThat(eparRequestDTO1).isNotEqualTo(eparRequestDTO2);
        eparRequestDTO2.setEparRequestId(eparRequestDTO1.getEparRequestId());
        assertThat(eparRequestDTO1).isEqualTo(eparRequestDTO2);
        eparRequestDTO2.setEparRequestId(2L);
        assertThat(eparRequestDTO1).isNotEqualTo(eparRequestDTO2);
        eparRequestDTO1.setEparRequestId(null);
        assertThat(eparRequestDTO1).isNotEqualTo(eparRequestDTO2);
    }
}

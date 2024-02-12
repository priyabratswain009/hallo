package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EfaxResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EfaxResponseDTO.class);
        EfaxResponseDTO efaxResponseDTO1 = new EfaxResponseDTO();
        efaxResponseDTO1.setEfaxResponseId(1L);
        EfaxResponseDTO efaxResponseDTO2 = new EfaxResponseDTO();
        assertThat(efaxResponseDTO1).isNotEqualTo(efaxResponseDTO2);
        efaxResponseDTO2.setEfaxResponseId(efaxResponseDTO1.getEfaxResponseId());
        assertThat(efaxResponseDTO1).isEqualTo(efaxResponseDTO2);
        efaxResponseDTO2.setEfaxResponseId(2L);
        assertThat(efaxResponseDTO1).isNotEqualTo(efaxResponseDTO2);
        efaxResponseDTO1.setEfaxResponseId(null);
        assertThat(efaxResponseDTO1).isNotEqualTo(efaxResponseDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParRequestDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParRequestDetailsDTO.class);
        ParRequestDetailsDTO parRequestDetailsDTO1 = new ParRequestDetailsDTO();
        parRequestDetailsDTO1.setParRequestDetailsId(1L);
        ParRequestDetailsDTO parRequestDetailsDTO2 = new ParRequestDetailsDTO();
        assertThat(parRequestDetailsDTO1).isNotEqualTo(parRequestDetailsDTO2);
        parRequestDetailsDTO2.setParRequestDetailsId(parRequestDetailsDTO1.getParRequestDetailsId());
        assertThat(parRequestDetailsDTO1).isEqualTo(parRequestDetailsDTO2);
        parRequestDetailsDTO2.setParRequestDetailsId(2L);
        assertThat(parRequestDetailsDTO1).isNotEqualTo(parRequestDetailsDTO2);
        parRequestDetailsDTO1.setParRequestDetailsId(null);
        assertThat(parRequestDetailsDTO1).isNotEqualTo(parRequestDetailsDTO2);
    }
}

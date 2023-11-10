package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParDetailsDTO.class);
        ParDetailsDTO parDetailsDTO1 = new ParDetailsDTO();
        parDetailsDTO1.setParDetailsId(1L);
        ParDetailsDTO parDetailsDTO2 = new ParDetailsDTO();
        assertThat(parDetailsDTO1).isNotEqualTo(parDetailsDTO2);
        parDetailsDTO2.setParDetailsId(parDetailsDTO1.getParDetailsId());
        assertThat(parDetailsDTO1).isEqualTo(parDetailsDTO2);
        parDetailsDTO2.setParDetailsId(2L);
        assertThat(parDetailsDTO1).isNotEqualTo(parDetailsDTO2);
        parDetailsDTO1.setParDetailsId(null);
        assertThat(parDetailsDTO1).isNotEqualTo(parDetailsDTO2);
    }
}

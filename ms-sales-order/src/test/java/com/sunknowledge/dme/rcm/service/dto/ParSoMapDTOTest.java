package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParSoMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParSoMapDTO.class);
        ParSoMapDTO parSoMapDTO1 = new ParSoMapDTO();
        parSoMapDTO1.setParSoId(1L);
        ParSoMapDTO parSoMapDTO2 = new ParSoMapDTO();
        assertThat(parSoMapDTO1).isNotEqualTo(parSoMapDTO2);
        parSoMapDTO2.setParSoId(parSoMapDTO1.getParSoId());
        assertThat(parSoMapDTO1).isEqualTo(parSoMapDTO2);
        parSoMapDTO2.setParSoId(2L);
        assertThat(parSoMapDTO1).isNotEqualTo(parSoMapDTO2);
        parSoMapDTO1.setParSoId(null);
        assertThat(parSoMapDTO1).isNotEqualTo(parSoMapDTO2);
    }
}

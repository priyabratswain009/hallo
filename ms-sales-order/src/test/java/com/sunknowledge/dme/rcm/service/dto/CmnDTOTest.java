package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmnDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmnDTO.class);
        CmnDTO cmnDTO1 = new CmnDTO();
        cmnDTO1.setCmnId(1L);
        CmnDTO cmnDTO2 = new CmnDTO();
        assertThat(cmnDTO1).isNotEqualTo(cmnDTO2);
        cmnDTO2.setCmnId(cmnDTO1.getCmnId());
        assertThat(cmnDTO1).isEqualTo(cmnDTO2);
        cmnDTO2.setCmnId(2L);
        assertThat(cmnDTO1).isNotEqualTo(cmnDTO2);
        cmnDTO1.setCmnId(null);
        assertThat(cmnDTO1).isNotEqualTo(cmnDTO2);
    }
}

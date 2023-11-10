package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PosMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosMasterDTO.class);
        PosMasterDTO posMasterDTO1 = new PosMasterDTO();
        posMasterDTO1.setPosId(1L);
        PosMasterDTO posMasterDTO2 = new PosMasterDTO();
        assertThat(posMasterDTO1).isNotEqualTo(posMasterDTO2);
        posMasterDTO2.setPosId(posMasterDTO1.getPosId());
        assertThat(posMasterDTO1).isEqualTo(posMasterDTO2);
        posMasterDTO2.setPosId(2L);
        assertThat(posMasterDTO1).isNotEqualTo(posMasterDTO2);
        posMasterDTO1.setPosId(null);
        assertThat(posMasterDTO1).isNotEqualTo(posMasterDTO2);
    }
}

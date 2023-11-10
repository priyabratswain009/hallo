package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParMasterDTO.class);
        ParMasterDTO parMasterDTO1 = new ParMasterDTO();
        parMasterDTO1.setParId(1L);
        ParMasterDTO parMasterDTO2 = new ParMasterDTO();
        assertThat(parMasterDTO1).isNotEqualTo(parMasterDTO2);
        parMasterDTO2.setParId(parMasterDTO1.getParId());
        assertThat(parMasterDTO1).isEqualTo(parMasterDTO2);
        parMasterDTO2.setParId(2L);
        assertThat(parMasterDTO1).isNotEqualTo(parMasterDTO2);
        parMasterDTO1.setParId(null);
        assertThat(parMasterDTO1).isNotEqualTo(parMasterDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleFunctionalityMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleFunctionalityMapDTO.class);
        RoleFunctionalityMapDTO roleFunctionalityMapDTO1 = new RoleFunctionalityMapDTO();
        roleFunctionalityMapDTO1.setRoleFunctionalityMapId(1L);
        RoleFunctionalityMapDTO roleFunctionalityMapDTO2 = new RoleFunctionalityMapDTO();
        assertThat(roleFunctionalityMapDTO1).isNotEqualTo(roleFunctionalityMapDTO2);
        roleFunctionalityMapDTO2.setRoleFunctionalityMapId(roleFunctionalityMapDTO1.getRoleFunctionalityMapId());
        assertThat(roleFunctionalityMapDTO1).isEqualTo(roleFunctionalityMapDTO2);
        roleFunctionalityMapDTO2.setRoleFunctionalityMapId(2L);
        assertThat(roleFunctionalityMapDTO1).isNotEqualTo(roleFunctionalityMapDTO2);
        roleFunctionalityMapDTO1.setRoleFunctionalityMapId(null);
        assertThat(roleFunctionalityMapDTO1).isNotEqualTo(roleFunctionalityMapDTO2);
    }
}

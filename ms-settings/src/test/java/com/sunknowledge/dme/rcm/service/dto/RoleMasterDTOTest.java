package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleMasterDTO.class);
        RoleMasterDTO roleMasterDTO1 = new RoleMasterDTO();
        roleMasterDTO1.setRoleId(1L);
        RoleMasterDTO roleMasterDTO2 = new RoleMasterDTO();
        assertThat(roleMasterDTO1).isNotEqualTo(roleMasterDTO2);
        roleMasterDTO2.setRoleId(roleMasterDTO1.getRoleId());
        assertThat(roleMasterDTO1).isEqualTo(roleMasterDTO2);
        roleMasterDTO2.setRoleId(2L);
        assertThat(roleMasterDTO1).isNotEqualTo(roleMasterDTO2);
        roleMasterDTO1.setRoleId(null);
        assertThat(roleMasterDTO1).isNotEqualTo(roleMasterDTO2);
    }
}

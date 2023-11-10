package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleUserMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleUserMapDTO.class);
        RoleUserMapDTO roleUserMapDTO1 = new RoleUserMapDTO();
        roleUserMapDTO1.setRoleUserMapId(1L);
        RoleUserMapDTO roleUserMapDTO2 = new RoleUserMapDTO();
        assertThat(roleUserMapDTO1).isNotEqualTo(roleUserMapDTO2);
        roleUserMapDTO2.setRoleUserMapId(roleUserMapDTO1.getRoleUserMapId());
        assertThat(roleUserMapDTO1).isEqualTo(roleUserMapDTO2);
        roleUserMapDTO2.setRoleUserMapId(2L);
        assertThat(roleUserMapDTO1).isNotEqualTo(roleUserMapDTO2);
        roleUserMapDTO1.setRoleUserMapId(null);
        assertThat(roleUserMapDTO1).isNotEqualTo(roleUserMapDTO2);
    }
}

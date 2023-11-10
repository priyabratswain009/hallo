package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleUserMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleUserMap.class);
        RoleUserMap roleUserMap1 = new RoleUserMap();
        roleUserMap1.setRoleUserMapId(1L);
        RoleUserMap roleUserMap2 = new RoleUserMap();
        roleUserMap2.setRoleUserMapId(roleUserMap1.getRoleUserMapId());
        assertThat(roleUserMap1).isEqualTo(roleUserMap2);
        roleUserMap2.setRoleUserMapId(2L);
        assertThat(roleUserMap1).isNotEqualTo(roleUserMap2);
        roleUserMap1.setRoleUserMapId(null);
        assertThat(roleUserMap1).isNotEqualTo(roleUserMap2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleMaster.class);
        RoleMaster roleMaster1 = new RoleMaster();
        roleMaster1.setRoleId(1L);
        RoleMaster roleMaster2 = new RoleMaster();
        roleMaster2.setRoleId(roleMaster1.getRoleId());
        assertThat(roleMaster1).isEqualTo(roleMaster2);
        roleMaster2.setRoleId(2L);
        assertThat(roleMaster1).isNotEqualTo(roleMaster2);
        roleMaster1.setRoleId(null);
        assertThat(roleMaster1).isNotEqualTo(roleMaster2);
    }
}

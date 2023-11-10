package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleFunctionalityMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleFunctionalityMap.class);
        RoleFunctionalityMap roleFunctionalityMap1 = new RoleFunctionalityMap();
        roleFunctionalityMap1.setRoleFunctionalityMapId(1L);
        RoleFunctionalityMap roleFunctionalityMap2 = new RoleFunctionalityMap();
        roleFunctionalityMap2.setRoleFunctionalityMapId(roleFunctionalityMap1.getRoleFunctionalityMapId());
        assertThat(roleFunctionalityMap1).isEqualTo(roleFunctionalityMap2);
        roleFunctionalityMap2.setRoleFunctionalityMapId(2L);
        assertThat(roleFunctionalityMap1).isNotEqualTo(roleFunctionalityMap2);
        roleFunctionalityMap1.setRoleFunctionalityMapId(null);
        assertThat(roleFunctionalityMap1).isNotEqualTo(roleFunctionalityMap2);
    }
}

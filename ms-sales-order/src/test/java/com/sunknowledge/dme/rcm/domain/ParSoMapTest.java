package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParSoMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParSoMap.class);
        ParSoMap parSoMap1 = new ParSoMap();
        parSoMap1.setParSoId(1L);
        ParSoMap parSoMap2 = new ParSoMap();
        parSoMap2.setParSoId(parSoMap1.getParSoId());
        assertThat(parSoMap1).isEqualTo(parSoMap2);
        parSoMap2.setParSoId(2L);
        assertThat(parSoMap1).isNotEqualTo(parSoMap2);
        parSoMap1.setParSoId(null);
        assertThat(parSoMap1).isNotEqualTo(parSoMap2);
    }
}

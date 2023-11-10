package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PosMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosMaster.class);
        PosMaster posMaster1 = new PosMaster();
        posMaster1.setPosId(1L);
        PosMaster posMaster2 = new PosMaster();
        posMaster2.setPosId(posMaster1.getPosId());
        assertThat(posMaster1).isEqualTo(posMaster2);
        posMaster2.setPosId(2L);
        assertThat(posMaster1).isNotEqualTo(posMaster2);
        posMaster1.setPosId(null);
        assertThat(posMaster1).isNotEqualTo(posMaster2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParMaster.class);
        ParMaster parMaster1 = new ParMaster();
        parMaster1.setParId(1L);
        ParMaster parMaster2 = new ParMaster();
        parMaster2.setParId(parMaster1.getParId());
        assertThat(parMaster1).isEqualTo(parMaster2);
        parMaster2.setParId(2L);
        assertThat(parMaster1).isNotEqualTo(parMaster2);
        parMaster1.setParId(null);
        assertThat(parMaster1).isNotEqualTo(parMaster2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmnTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cmn.class);
        Cmn cmn1 = new Cmn();
        cmn1.setCmnId(1L);
        Cmn cmn2 = new Cmn();
        cmn2.setCmnId(cmn1.getCmnId());
        assertThat(cmn1).isEqualTo(cmn2);
        cmn2.setCmnId(2L);
        assertThat(cmn1).isNotEqualTo(cmn2);
        cmn1.setCmnId(null);
        assertThat(cmn1).isNotEqualTo(cmn2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParDetails.class);
        ParDetails parDetails1 = new ParDetails();
        parDetails1.setParDetailsId(1L);
        ParDetails parDetails2 = new ParDetails();
        parDetails2.setParDetailsId(parDetails1.getParDetailsId());
        assertThat(parDetails1).isEqualTo(parDetails2);
        parDetails2.setParDetailsId(2L);
        assertThat(parDetails1).isNotEqualTo(parDetails2);
        parDetails1.setParDetailsId(null);
        assertThat(parDetails1).isNotEqualTo(parDetails2);
    }
}

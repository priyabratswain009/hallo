package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParRequestDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParRequestDetails.class);
        ParRequestDetails parRequestDetails1 = new ParRequestDetails();
        parRequestDetails1.setParRequestDetailsId(1L);
        ParRequestDetails parRequestDetails2 = new ParRequestDetails();
        parRequestDetails2.setParRequestDetailsId(parRequestDetails1.getParRequestDetailsId());
        assertThat(parRequestDetails1).isEqualTo(parRequestDetails2);
        parRequestDetails2.setParRequestDetailsId(2L);
        assertThat(parRequestDetails1).isNotEqualTo(parRequestDetails2);
        parRequestDetails1.setParRequestDetailsId(null);
        assertThat(parRequestDetails1).isNotEqualTo(parRequestDetails2);
    }
}

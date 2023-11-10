package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetails.class);
        PriceDetails priceDetails1 = new PriceDetails();
        priceDetails1.setPriceDetailsId(1L);
        PriceDetails priceDetails2 = new PriceDetails();
        priceDetails2.setPriceDetailsId(priceDetails1.getPriceDetailsId());
        assertThat(priceDetails1).isEqualTo(priceDetails2);
        priceDetails2.setPriceDetailsId(2L);
        assertThat(priceDetails1).isNotEqualTo(priceDetails2);
        priceDetails1.setPriceDetailsId(null);
        assertThat(priceDetails1).isNotEqualTo(priceDetails2);
    }
}

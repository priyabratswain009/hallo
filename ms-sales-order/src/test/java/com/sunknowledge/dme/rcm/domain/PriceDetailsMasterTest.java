package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetailsMaster.class);
        PriceDetailsMaster priceDetailsMaster1 = new PriceDetailsMaster();
        priceDetailsMaster1.setPriceDetailsId(1L);
        PriceDetailsMaster priceDetailsMaster2 = new PriceDetailsMaster();
        priceDetailsMaster2.setPriceDetailsId(priceDetailsMaster1.getPriceDetailsId());
        assertThat(priceDetailsMaster1).isEqualTo(priceDetailsMaster2);
        priceDetailsMaster2.setPriceDetailsId(2L);
        assertThat(priceDetailsMaster1).isNotEqualTo(priceDetailsMaster2);
        priceDetailsMaster1.setPriceDetailsId(null);
        assertThat(priceDetailsMaster1).isNotEqualTo(priceDetailsMaster2);
    }
}

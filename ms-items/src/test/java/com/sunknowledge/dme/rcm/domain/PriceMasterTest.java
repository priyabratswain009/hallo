package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceMaster.class);
        PriceMaster priceMaster1 = new PriceMaster();
        priceMaster1.setPriceTableId(1L);
        PriceMaster priceMaster2 = new PriceMaster();
        priceMaster2.setPriceTableId(priceMaster1.getPriceTableId());
        assertThat(priceMaster1).isEqualTo(priceMaster2);
        priceMaster2.setPriceTableId(2L);
        assertThat(priceMaster1).isNotEqualTo(priceMaster2);
        priceMaster1.setPriceTableId(null);
        assertThat(priceMaster1).isNotEqualTo(priceMaster2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxZoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxZone.class);
        TaxZone taxZone1 = new TaxZone();
        taxZone1.setTaxZoneId(1L);
        TaxZone taxZone2 = new TaxZone();
        taxZone2.setTaxZoneId(taxZone1.getTaxZoneId());
        assertThat(taxZone1).isEqualTo(taxZone2);
        taxZone2.setTaxZoneId(2L);
        assertThat(taxZone1).isNotEqualTo(taxZone2);
        taxZone1.setTaxZoneId(null);
        assertThat(taxZone1).isNotEqualTo(taxZone2);
    }
}

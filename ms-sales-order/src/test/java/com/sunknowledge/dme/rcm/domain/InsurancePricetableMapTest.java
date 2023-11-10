package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsurancePricetableMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsurancePricetableMap.class);
        InsurancePricetableMap insurancePricetableMap1 = new InsurancePricetableMap();
        insurancePricetableMap1.setInsurancePricetableMapId(1L);
        InsurancePricetableMap insurancePricetableMap2 = new InsurancePricetableMap();
        insurancePricetableMap2.setInsurancePricetableMapId(insurancePricetableMap1.getInsurancePricetableMapId());
        assertThat(insurancePricetableMap1).isEqualTo(insurancePricetableMap2);
        insurancePricetableMap2.setInsurancePricetableMapId(2L);
        assertThat(insurancePricetableMap1).isNotEqualTo(insurancePricetableMap2);
        insurancePricetableMap1.setInsurancePricetableMapId(null);
        assertThat(insurancePricetableMap1).isNotEqualTo(insurancePricetableMap2);
    }
}

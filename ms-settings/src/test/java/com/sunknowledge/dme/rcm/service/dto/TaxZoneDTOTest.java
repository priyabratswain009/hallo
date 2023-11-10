package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxZoneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxZoneDTO.class);
        TaxZoneDTO taxZoneDTO1 = new TaxZoneDTO();
        taxZoneDTO1.setTaxZoneId(1L);
        TaxZoneDTO taxZoneDTO2 = new TaxZoneDTO();
        assertThat(taxZoneDTO1).isNotEqualTo(taxZoneDTO2);
        taxZoneDTO2.setTaxZoneId(taxZoneDTO1.getTaxZoneId());
        assertThat(taxZoneDTO1).isEqualTo(taxZoneDTO2);
        taxZoneDTO2.setTaxZoneId(2L);
        assertThat(taxZoneDTO1).isNotEqualTo(taxZoneDTO2);
        taxZoneDTO1.setTaxZoneId(null);
        assertThat(taxZoneDTO1).isNotEqualTo(taxZoneDTO2);
    }
}

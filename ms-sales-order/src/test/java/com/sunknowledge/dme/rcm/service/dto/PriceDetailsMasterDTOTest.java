package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetailsMasterDTO.class);
        PriceDetailsMasterDTO priceDetailsMasterDTO1 = new PriceDetailsMasterDTO();
        priceDetailsMasterDTO1.setPriceDetailsId(1L);
        PriceDetailsMasterDTO priceDetailsMasterDTO2 = new PriceDetailsMasterDTO();
        assertThat(priceDetailsMasterDTO1).isNotEqualTo(priceDetailsMasterDTO2);
        priceDetailsMasterDTO2.setPriceDetailsId(priceDetailsMasterDTO1.getPriceDetailsId());
        assertThat(priceDetailsMasterDTO1).isEqualTo(priceDetailsMasterDTO2);
        priceDetailsMasterDTO2.setPriceDetailsId(2L);
        assertThat(priceDetailsMasterDTO1).isNotEqualTo(priceDetailsMasterDTO2);
        priceDetailsMasterDTO1.setPriceDetailsId(null);
        assertThat(priceDetailsMasterDTO1).isNotEqualTo(priceDetailsMasterDTO2);
    }
}

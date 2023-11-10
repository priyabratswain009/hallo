package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetailsDTO.class);
        PriceDetailsDTO priceDetailsDTO1 = new PriceDetailsDTO();
        priceDetailsDTO1.setPriceDetailsId(1L);
        PriceDetailsDTO priceDetailsDTO2 = new PriceDetailsDTO();
        assertThat(priceDetailsDTO1).isNotEqualTo(priceDetailsDTO2);
        priceDetailsDTO2.setPriceDetailsId(priceDetailsDTO1.getPriceDetailsId());
        assertThat(priceDetailsDTO1).isEqualTo(priceDetailsDTO2);
        priceDetailsDTO2.setPriceDetailsId(2L);
        assertThat(priceDetailsDTO1).isNotEqualTo(priceDetailsDTO2);
        priceDetailsDTO1.setPriceDetailsId(null);
        assertThat(priceDetailsDTO1).isNotEqualTo(priceDetailsDTO2);
    }
}

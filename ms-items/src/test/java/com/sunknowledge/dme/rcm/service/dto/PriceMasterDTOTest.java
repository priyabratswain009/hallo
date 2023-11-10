package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceMasterDTO.class);
        PriceMasterDTO priceMasterDTO1 = new PriceMasterDTO();
        priceMasterDTO1.setPriceTableId(1L);
        PriceMasterDTO priceMasterDTO2 = new PriceMasterDTO();
        assertThat(priceMasterDTO1).isNotEqualTo(priceMasterDTO2);
        priceMasterDTO2.setPriceTableId(priceMasterDTO1.getPriceTableId());
        assertThat(priceMasterDTO1).isEqualTo(priceMasterDTO2);
        priceMasterDTO2.setPriceTableId(2L);
        assertThat(priceMasterDTO1).isNotEqualTo(priceMasterDTO2);
        priceMasterDTO1.setPriceTableId(null);
        assertThat(priceMasterDTO1).isNotEqualTo(priceMasterDTO2);
    }
}

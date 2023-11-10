package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesRepDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesRepDTO.class);
        SalesRepDTO salesRepDTO1 = new SalesRepDTO();
        salesRepDTO1.setSalesRepId(1L);
        SalesRepDTO salesRepDTO2 = new SalesRepDTO();
        assertThat(salesRepDTO1).isNotEqualTo(salesRepDTO2);
        salesRepDTO2.setSalesRepId(salesRepDTO1.getSalesRepId());
        assertThat(salesRepDTO1).isEqualTo(salesRepDTO2);
        salesRepDTO2.setSalesRepId(2L);
        assertThat(salesRepDTO1).isNotEqualTo(salesRepDTO2);
        salesRepDTO1.setSalesRepId(null);
        assertThat(salesRepDTO1).isNotEqualTo(salesRepDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderItemDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderItemDetailsDTO.class);
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO1 = new SalesOrderItemDetailsDTO();
        salesOrderItemDetailsDTO1.setSalesOrderItemDetailsId(1L);
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO2 = new SalesOrderItemDetailsDTO();
        assertThat(salesOrderItemDetailsDTO1).isNotEqualTo(salesOrderItemDetailsDTO2);
        salesOrderItemDetailsDTO2.setSalesOrderItemDetailsId(salesOrderItemDetailsDTO1.getSalesOrderItemDetailsId());
        assertThat(salesOrderItemDetailsDTO1).isEqualTo(salesOrderItemDetailsDTO2);
        salesOrderItemDetailsDTO2.setSalesOrderItemDetailsId(2L);
        assertThat(salesOrderItemDetailsDTO1).isNotEqualTo(salesOrderItemDetailsDTO2);
        salesOrderItemDetailsDTO1.setSalesOrderItemDetailsId(null);
        assertThat(salesOrderItemDetailsDTO1).isNotEqualTo(salesOrderItemDetailsDTO2);
    }
}

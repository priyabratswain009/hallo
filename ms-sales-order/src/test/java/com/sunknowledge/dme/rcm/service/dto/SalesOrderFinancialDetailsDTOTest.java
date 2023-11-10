package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderFinancialDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderFinancialDetailsDTO.class);
        SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO1 = new SalesOrderFinancialDetailsDTO();
        salesOrderFinancialDetailsDTO1.setSalesOrderFinancialId(1L);
        SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO2 = new SalesOrderFinancialDetailsDTO();
        assertThat(salesOrderFinancialDetailsDTO1).isNotEqualTo(salesOrderFinancialDetailsDTO2);
        salesOrderFinancialDetailsDTO2.setSalesOrderFinancialId(salesOrderFinancialDetailsDTO1.getSalesOrderFinancialId());
        assertThat(salesOrderFinancialDetailsDTO1).isEqualTo(salesOrderFinancialDetailsDTO2);
        salesOrderFinancialDetailsDTO2.setSalesOrderFinancialId(2L);
        assertThat(salesOrderFinancialDetailsDTO1).isNotEqualTo(salesOrderFinancialDetailsDTO2);
        salesOrderFinancialDetailsDTO1.setSalesOrderFinancialId(null);
        assertThat(salesOrderFinancialDetailsDTO1).isNotEqualTo(salesOrderFinancialDetailsDTO2);
    }
}

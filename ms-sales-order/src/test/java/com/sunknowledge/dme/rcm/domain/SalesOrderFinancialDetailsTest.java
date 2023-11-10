package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderFinancialDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderFinancialDetails.class);
        SalesOrderFinancialDetails salesOrderFinancialDetails1 = new SalesOrderFinancialDetails();
        salesOrderFinancialDetails1.setSalesOrderFinancialId(1L);
        SalesOrderFinancialDetails salesOrderFinancialDetails2 = new SalesOrderFinancialDetails();
        salesOrderFinancialDetails2.setSalesOrderFinancialId(salesOrderFinancialDetails1.getSalesOrderFinancialId());
        assertThat(salesOrderFinancialDetails1).isEqualTo(salesOrderFinancialDetails2);
        salesOrderFinancialDetails2.setSalesOrderFinancialId(2L);
        assertThat(salesOrderFinancialDetails1).isNotEqualTo(salesOrderFinancialDetails2);
        salesOrderFinancialDetails1.setSalesOrderFinancialId(null);
        assertThat(salesOrderFinancialDetails1).isNotEqualTo(salesOrderFinancialDetails2);
    }
}

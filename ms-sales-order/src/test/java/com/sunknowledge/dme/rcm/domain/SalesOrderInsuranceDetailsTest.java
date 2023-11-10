package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderInsuranceDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderInsuranceDetails.class);
        SalesOrderInsuranceDetails salesOrderInsuranceDetails1 = new SalesOrderInsuranceDetails();
        salesOrderInsuranceDetails1.setSalesOrderInsuranceDetailsId(1L);
        SalesOrderInsuranceDetails salesOrderInsuranceDetails2 = new SalesOrderInsuranceDetails();
        salesOrderInsuranceDetails2.setSalesOrderInsuranceDetailsId(salesOrderInsuranceDetails1.getSalesOrderInsuranceDetailsId());
        assertThat(salesOrderInsuranceDetails1).isEqualTo(salesOrderInsuranceDetails2);
        salesOrderInsuranceDetails2.setSalesOrderInsuranceDetailsId(2L);
        assertThat(salesOrderInsuranceDetails1).isNotEqualTo(salesOrderInsuranceDetails2);
        salesOrderInsuranceDetails1.setSalesOrderInsuranceDetailsId(null);
        assertThat(salesOrderInsuranceDetails1).isNotEqualTo(salesOrderInsuranceDetails2);
    }
}

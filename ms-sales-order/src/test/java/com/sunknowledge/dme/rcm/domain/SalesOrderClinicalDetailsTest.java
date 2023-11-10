package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClinicalDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClinicalDetails.class);
        SalesOrderClinicalDetails salesOrderClinicalDetails1 = new SalesOrderClinicalDetails();
        salesOrderClinicalDetails1.setSalesOrderClinicalDetailsId(1L);
        SalesOrderClinicalDetails salesOrderClinicalDetails2 = new SalesOrderClinicalDetails();
        salesOrderClinicalDetails2.setSalesOrderClinicalDetailsId(salesOrderClinicalDetails1.getSalesOrderClinicalDetailsId());
        assertThat(salesOrderClinicalDetails1).isEqualTo(salesOrderClinicalDetails2);
        salesOrderClinicalDetails2.setSalesOrderClinicalDetailsId(2L);
        assertThat(salesOrderClinicalDetails1).isNotEqualTo(salesOrderClinicalDetails2);
        salesOrderClinicalDetails1.setSalesOrderClinicalDetailsId(null);
        assertThat(salesOrderClinicalDetails1).isNotEqualTo(salesOrderClinicalDetails2);
    }
}

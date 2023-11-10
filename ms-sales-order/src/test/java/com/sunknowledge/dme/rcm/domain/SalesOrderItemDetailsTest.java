package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderItemDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderItemDetails.class);
        SalesOrderItemDetails salesOrderItemDetails1 = new SalesOrderItemDetails();
        salesOrderItemDetails1.setSalesOrderItemDetailsId(1L);
        SalesOrderItemDetails salesOrderItemDetails2 = new SalesOrderItemDetails();
        salesOrderItemDetails2.setSalesOrderItemDetailsId(salesOrderItemDetails1.getSalesOrderItemDetailsId());
        assertThat(salesOrderItemDetails1).isEqualTo(salesOrderItemDetails2);
        salesOrderItemDetails2.setSalesOrderItemDetailsId(2L);
        assertThat(salesOrderItemDetails1).isNotEqualTo(salesOrderItemDetails2);
        salesOrderItemDetails1.setSalesOrderItemDetailsId(null);
        assertThat(salesOrderItemDetails1).isNotEqualTo(salesOrderItemDetails2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClassificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClassification.class);
        SalesOrderClassification salesOrderClassification1 = new SalesOrderClassification();
        salesOrderClassification1.setOrderClassificationId(1L);
        SalesOrderClassification salesOrderClassification2 = new SalesOrderClassification();
        salesOrderClassification2.setOrderClassificationId(salesOrderClassification1.getOrderClassificationId());
        assertThat(salesOrderClassification1).isEqualTo(salesOrderClassification2);
        salesOrderClassification2.setOrderClassificationId(2L);
        assertThat(salesOrderClassification1).isNotEqualTo(salesOrderClassification2);
        salesOrderClassification1.setOrderClassificationId(null);
        assertThat(salesOrderClassification1).isNotEqualTo(salesOrderClassification2);
    }
}

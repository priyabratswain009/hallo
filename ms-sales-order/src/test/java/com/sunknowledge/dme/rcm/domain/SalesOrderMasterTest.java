package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderMaster.class);
        SalesOrderMaster salesOrderMaster1 = new SalesOrderMaster();
        salesOrderMaster1.setSalesOrderId(1L);
        SalesOrderMaster salesOrderMaster2 = new SalesOrderMaster();
        salesOrderMaster2.setSalesOrderId(salesOrderMaster1.getSalesOrderId());
        assertThat(salesOrderMaster1).isEqualTo(salesOrderMaster2);
        salesOrderMaster2.setSalesOrderId(2L);
        assertThat(salesOrderMaster1).isNotEqualTo(salesOrderMaster2);
        salesOrderMaster1.setSalesOrderId(null);
        assertThat(salesOrderMaster1).isNotEqualTo(salesOrderMaster2);
    }
}

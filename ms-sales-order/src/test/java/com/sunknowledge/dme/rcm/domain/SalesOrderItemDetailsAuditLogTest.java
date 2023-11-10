package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderItemDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderItemDetailsAuditLog.class);
        SalesOrderItemDetailsAuditLog salesOrderItemDetailsAuditLog1 = new SalesOrderItemDetailsAuditLog();
        salesOrderItemDetailsAuditLog1.setId(1L);
        SalesOrderItemDetailsAuditLog salesOrderItemDetailsAuditLog2 = new SalesOrderItemDetailsAuditLog();
        salesOrderItemDetailsAuditLog2.setId(salesOrderItemDetailsAuditLog1.getId());
        assertThat(salesOrderItemDetailsAuditLog1).isEqualTo(salesOrderItemDetailsAuditLog2);
        salesOrderItemDetailsAuditLog2.setId(2L);
        assertThat(salesOrderItemDetailsAuditLog1).isNotEqualTo(salesOrderItemDetailsAuditLog2);
        salesOrderItemDetailsAuditLog1.setId(null);
        assertThat(salesOrderItemDetailsAuditLog1).isNotEqualTo(salesOrderItemDetailsAuditLog2);
    }
}

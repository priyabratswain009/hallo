package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClassificationAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClassificationAuditLog.class);
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog1 = new SalesOrderClassificationAuditLog();
        salesOrderClassificationAuditLog1.setId(1L);
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog2 = new SalesOrderClassificationAuditLog();
        salesOrderClassificationAuditLog2.setId(salesOrderClassificationAuditLog1.getId());
        assertThat(salesOrderClassificationAuditLog1).isEqualTo(salesOrderClassificationAuditLog2);
        salesOrderClassificationAuditLog2.setId(2L);
        assertThat(salesOrderClassificationAuditLog1).isNotEqualTo(salesOrderClassificationAuditLog2);
        salesOrderClassificationAuditLog1.setId(null);
        assertThat(salesOrderClassificationAuditLog1).isNotEqualTo(salesOrderClassificationAuditLog2);
    }
}

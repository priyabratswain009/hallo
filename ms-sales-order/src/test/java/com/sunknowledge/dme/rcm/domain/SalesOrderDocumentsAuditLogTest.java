package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderDocumentsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderDocumentsAuditLog.class);
        SalesOrderDocumentsAuditLog salesOrderDocumentsAuditLog1 = new SalesOrderDocumentsAuditLog();
        salesOrderDocumentsAuditLog1.setId(1L);
        SalesOrderDocumentsAuditLog salesOrderDocumentsAuditLog2 = new SalesOrderDocumentsAuditLog();
        salesOrderDocumentsAuditLog2.setId(salesOrderDocumentsAuditLog1.getId());
        assertThat(salesOrderDocumentsAuditLog1).isEqualTo(salesOrderDocumentsAuditLog2);
        salesOrderDocumentsAuditLog2.setId(2L);
        assertThat(salesOrderDocumentsAuditLog1).isNotEqualTo(salesOrderDocumentsAuditLog2);
        salesOrderDocumentsAuditLog1.setId(null);
        assertThat(salesOrderDocumentsAuditLog1).isNotEqualTo(salesOrderDocumentsAuditLog2);
    }
}

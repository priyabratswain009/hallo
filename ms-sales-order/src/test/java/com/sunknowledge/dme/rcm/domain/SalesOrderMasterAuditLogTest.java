package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderMasterAuditLog.class);
        SalesOrderMasterAuditLog salesOrderMasterAuditLog1 = new SalesOrderMasterAuditLog();
        salesOrderMasterAuditLog1.setId(1L);
        SalesOrderMasterAuditLog salesOrderMasterAuditLog2 = new SalesOrderMasterAuditLog();
        salesOrderMasterAuditLog2.setId(salesOrderMasterAuditLog1.getId());
        assertThat(salesOrderMasterAuditLog1).isEqualTo(salesOrderMasterAuditLog2);
        salesOrderMasterAuditLog2.setId(2L);
        assertThat(salesOrderMasterAuditLog1).isNotEqualTo(salesOrderMasterAuditLog2);
        salesOrderMasterAuditLog1.setId(null);
        assertThat(salesOrderMasterAuditLog1).isNotEqualTo(salesOrderMasterAuditLog2);
    }
}

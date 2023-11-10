package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderFinancialDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderFinancialDetailsAuditLog.class);
        SalesOrderFinancialDetailsAuditLog salesOrderFinancialDetailsAuditLog1 = new SalesOrderFinancialDetailsAuditLog();
        salesOrderFinancialDetailsAuditLog1.setId(1L);
        SalesOrderFinancialDetailsAuditLog salesOrderFinancialDetailsAuditLog2 = new SalesOrderFinancialDetailsAuditLog();
        salesOrderFinancialDetailsAuditLog2.setId(salesOrderFinancialDetailsAuditLog1.getId());
        assertThat(salesOrderFinancialDetailsAuditLog1).isEqualTo(salesOrderFinancialDetailsAuditLog2);
        salesOrderFinancialDetailsAuditLog2.setId(2L);
        assertThat(salesOrderFinancialDetailsAuditLog1).isNotEqualTo(salesOrderFinancialDetailsAuditLog2);
        salesOrderFinancialDetailsAuditLog1.setId(null);
        assertThat(salesOrderFinancialDetailsAuditLog1).isNotEqualTo(salesOrderFinancialDetailsAuditLog2);
    }
}

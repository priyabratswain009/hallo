package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderInsuranceDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderInsuranceDetailsAuditLog.class);
        SalesOrderInsuranceDetailsAuditLog salesOrderInsuranceDetailsAuditLog1 = new SalesOrderInsuranceDetailsAuditLog();
        salesOrderInsuranceDetailsAuditLog1.setId(1L);
        SalesOrderInsuranceDetailsAuditLog salesOrderInsuranceDetailsAuditLog2 = new SalesOrderInsuranceDetailsAuditLog();
        salesOrderInsuranceDetailsAuditLog2.setId(salesOrderInsuranceDetailsAuditLog1.getId());
        assertThat(salesOrderInsuranceDetailsAuditLog1).isEqualTo(salesOrderInsuranceDetailsAuditLog2);
        salesOrderInsuranceDetailsAuditLog2.setId(2L);
        assertThat(salesOrderInsuranceDetailsAuditLog1).isNotEqualTo(salesOrderInsuranceDetailsAuditLog2);
        salesOrderInsuranceDetailsAuditLog1.setId(null);
        assertThat(salesOrderInsuranceDetailsAuditLog1).isNotEqualTo(salesOrderInsuranceDetailsAuditLog2);
    }
}

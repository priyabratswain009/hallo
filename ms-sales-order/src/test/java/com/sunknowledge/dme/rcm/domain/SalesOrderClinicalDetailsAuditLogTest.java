package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClinicalDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClinicalDetailsAuditLog.class);
        SalesOrderClinicalDetailsAuditLog salesOrderClinicalDetailsAuditLog1 = new SalesOrderClinicalDetailsAuditLog();
        salesOrderClinicalDetailsAuditLog1.setId(1L);
        SalesOrderClinicalDetailsAuditLog salesOrderClinicalDetailsAuditLog2 = new SalesOrderClinicalDetailsAuditLog();
        salesOrderClinicalDetailsAuditLog2.setId(salesOrderClinicalDetailsAuditLog1.getId());
        assertThat(salesOrderClinicalDetailsAuditLog1).isEqualTo(salesOrderClinicalDetailsAuditLog2);
        salesOrderClinicalDetailsAuditLog2.setId(2L);
        assertThat(salesOrderClinicalDetailsAuditLog1).isNotEqualTo(salesOrderClinicalDetailsAuditLog2);
        salesOrderClinicalDetailsAuditLog1.setId(null);
        assertThat(salesOrderClinicalDetailsAuditLog1).isNotEqualTo(salesOrderClinicalDetailsAuditLog2);
    }
}

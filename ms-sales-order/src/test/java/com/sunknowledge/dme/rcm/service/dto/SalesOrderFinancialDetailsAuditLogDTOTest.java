package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderFinancialDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderFinancialDetailsAuditLogDTO.class);
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO1 = new SalesOrderFinancialDetailsAuditLogDTO();
        salesOrderFinancialDetailsAuditLogDTO1.setId(1L);
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO2 = new SalesOrderFinancialDetailsAuditLogDTO();
        assertThat(salesOrderFinancialDetailsAuditLogDTO1).isNotEqualTo(salesOrderFinancialDetailsAuditLogDTO2);
        salesOrderFinancialDetailsAuditLogDTO2.setId(salesOrderFinancialDetailsAuditLogDTO1.getId());
        assertThat(salesOrderFinancialDetailsAuditLogDTO1).isEqualTo(salesOrderFinancialDetailsAuditLogDTO2);
        salesOrderFinancialDetailsAuditLogDTO2.setId(2L);
        assertThat(salesOrderFinancialDetailsAuditLogDTO1).isNotEqualTo(salesOrderFinancialDetailsAuditLogDTO2);
        salesOrderFinancialDetailsAuditLogDTO1.setId(null);
        assertThat(salesOrderFinancialDetailsAuditLogDTO1).isNotEqualTo(salesOrderFinancialDetailsAuditLogDTO2);
    }
}

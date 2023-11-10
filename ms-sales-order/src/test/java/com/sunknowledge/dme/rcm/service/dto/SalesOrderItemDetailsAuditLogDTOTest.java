package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderItemDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderItemDetailsAuditLogDTO.class);
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO1 = new SalesOrderItemDetailsAuditLogDTO();
        salesOrderItemDetailsAuditLogDTO1.setId(1L);
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO2 = new SalesOrderItemDetailsAuditLogDTO();
        assertThat(salesOrderItemDetailsAuditLogDTO1).isNotEqualTo(salesOrderItemDetailsAuditLogDTO2);
        salesOrderItemDetailsAuditLogDTO2.setId(salesOrderItemDetailsAuditLogDTO1.getId());
        assertThat(salesOrderItemDetailsAuditLogDTO1).isEqualTo(salesOrderItemDetailsAuditLogDTO2);
        salesOrderItemDetailsAuditLogDTO2.setId(2L);
        assertThat(salesOrderItemDetailsAuditLogDTO1).isNotEqualTo(salesOrderItemDetailsAuditLogDTO2);
        salesOrderItemDetailsAuditLogDTO1.setId(null);
        assertThat(salesOrderItemDetailsAuditLogDTO1).isNotEqualTo(salesOrderItemDetailsAuditLogDTO2);
    }
}

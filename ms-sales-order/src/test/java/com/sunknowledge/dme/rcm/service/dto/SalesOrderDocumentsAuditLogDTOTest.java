package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderDocumentsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderDocumentsAuditLogDTO.class);
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO1 = new SalesOrderDocumentsAuditLogDTO();
        salesOrderDocumentsAuditLogDTO1.setId(1L);
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO2 = new SalesOrderDocumentsAuditLogDTO();
        assertThat(salesOrderDocumentsAuditLogDTO1).isNotEqualTo(salesOrderDocumentsAuditLogDTO2);
        salesOrderDocumentsAuditLogDTO2.setId(salesOrderDocumentsAuditLogDTO1.getId());
        assertThat(salesOrderDocumentsAuditLogDTO1).isEqualTo(salesOrderDocumentsAuditLogDTO2);
        salesOrderDocumentsAuditLogDTO2.setId(2L);
        assertThat(salesOrderDocumentsAuditLogDTO1).isNotEqualTo(salesOrderDocumentsAuditLogDTO2);
        salesOrderDocumentsAuditLogDTO1.setId(null);
        assertThat(salesOrderDocumentsAuditLogDTO1).isNotEqualTo(salesOrderDocumentsAuditLogDTO2);
    }
}

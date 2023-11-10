package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClassificationAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClassificationAuditLogDTO.class);
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO1 = new SalesOrderClassificationAuditLogDTO();
        salesOrderClassificationAuditLogDTO1.setId(1L);
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO2 = new SalesOrderClassificationAuditLogDTO();
        assertThat(salesOrderClassificationAuditLogDTO1).isNotEqualTo(salesOrderClassificationAuditLogDTO2);
        salesOrderClassificationAuditLogDTO2.setId(salesOrderClassificationAuditLogDTO1.getId());
        assertThat(salesOrderClassificationAuditLogDTO1).isEqualTo(salesOrderClassificationAuditLogDTO2);
        salesOrderClassificationAuditLogDTO2.setId(2L);
        assertThat(salesOrderClassificationAuditLogDTO1).isNotEqualTo(salesOrderClassificationAuditLogDTO2);
        salesOrderClassificationAuditLogDTO1.setId(null);
        assertThat(salesOrderClassificationAuditLogDTO1).isNotEqualTo(salesOrderClassificationAuditLogDTO2);
    }
}

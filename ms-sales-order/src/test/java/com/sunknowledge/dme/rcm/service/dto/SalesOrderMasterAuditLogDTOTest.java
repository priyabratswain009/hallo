package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderMasterAuditLogDTO.class);
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO1 = new SalesOrderMasterAuditLogDTO();
        salesOrderMasterAuditLogDTO1.setId(1L);
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO2 = new SalesOrderMasterAuditLogDTO();
        assertThat(salesOrderMasterAuditLogDTO1).isNotEqualTo(salesOrderMasterAuditLogDTO2);
        salesOrderMasterAuditLogDTO2.setId(salesOrderMasterAuditLogDTO1.getId());
        assertThat(salesOrderMasterAuditLogDTO1).isEqualTo(salesOrderMasterAuditLogDTO2);
        salesOrderMasterAuditLogDTO2.setId(2L);
        assertThat(salesOrderMasterAuditLogDTO1).isNotEqualTo(salesOrderMasterAuditLogDTO2);
        salesOrderMasterAuditLogDTO1.setId(null);
        assertThat(salesOrderMasterAuditLogDTO1).isNotEqualTo(salesOrderMasterAuditLogDTO2);
    }
}

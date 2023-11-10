package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockTransferAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockTransferAuditLogDTO.class);
        StockTransferAuditLogDTO stockTransferAuditLogDTO1 = new StockTransferAuditLogDTO();
        stockTransferAuditLogDTO1.setId(1L);
        StockTransferAuditLogDTO stockTransferAuditLogDTO2 = new StockTransferAuditLogDTO();
        assertThat(stockTransferAuditLogDTO1).isNotEqualTo(stockTransferAuditLogDTO2);
        stockTransferAuditLogDTO2.setId(stockTransferAuditLogDTO1.getId());
        assertThat(stockTransferAuditLogDTO1).isEqualTo(stockTransferAuditLogDTO2);
        stockTransferAuditLogDTO2.setId(2L);
        assertThat(stockTransferAuditLogDTO1).isNotEqualTo(stockTransferAuditLogDTO2);
        stockTransferAuditLogDTO1.setId(null);
        assertThat(stockTransferAuditLogDTO1).isNotEqualTo(stockTransferAuditLogDTO2);
    }
}

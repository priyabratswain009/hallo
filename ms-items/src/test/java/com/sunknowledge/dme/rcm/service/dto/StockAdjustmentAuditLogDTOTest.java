package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockAdjustmentAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockAdjustmentAuditLogDTO.class);
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO1 = new StockAdjustmentAuditLogDTO();
        stockAdjustmentAuditLogDTO1.setId(1L);
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO2 = new StockAdjustmentAuditLogDTO();
        assertThat(stockAdjustmentAuditLogDTO1).isNotEqualTo(stockAdjustmentAuditLogDTO2);
        stockAdjustmentAuditLogDTO2.setId(stockAdjustmentAuditLogDTO1.getId());
        assertThat(stockAdjustmentAuditLogDTO1).isEqualTo(stockAdjustmentAuditLogDTO2);
        stockAdjustmentAuditLogDTO2.setId(2L);
        assertThat(stockAdjustmentAuditLogDTO1).isNotEqualTo(stockAdjustmentAuditLogDTO2);
        stockAdjustmentAuditLogDTO1.setId(null);
        assertThat(stockAdjustmentAuditLogDTO1).isNotEqualTo(stockAdjustmentAuditLogDTO2);
    }
}

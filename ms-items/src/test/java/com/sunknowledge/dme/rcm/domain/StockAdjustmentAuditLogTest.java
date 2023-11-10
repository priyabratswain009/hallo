package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockAdjustmentAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockAdjustmentAuditLog.class);
        StockAdjustmentAuditLog stockAdjustmentAuditLog1 = new StockAdjustmentAuditLog();
        stockAdjustmentAuditLog1.setId(1L);
        StockAdjustmentAuditLog stockAdjustmentAuditLog2 = new StockAdjustmentAuditLog();
        stockAdjustmentAuditLog2.setId(stockAdjustmentAuditLog1.getId());
        assertThat(stockAdjustmentAuditLog1).isEqualTo(stockAdjustmentAuditLog2);
        stockAdjustmentAuditLog2.setId(2L);
        assertThat(stockAdjustmentAuditLog1).isNotEqualTo(stockAdjustmentAuditLog2);
        stockAdjustmentAuditLog1.setId(null);
        assertThat(stockAdjustmentAuditLog1).isNotEqualTo(stockAdjustmentAuditLog2);
    }
}

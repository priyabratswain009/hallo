package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockTransferAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockTransferAuditLog.class);
        StockTransferAuditLog stockTransferAuditLog1 = new StockTransferAuditLog();
        stockTransferAuditLog1.setId(1L);
        StockTransferAuditLog stockTransferAuditLog2 = new StockTransferAuditLog();
        stockTransferAuditLog2.setId(stockTransferAuditLog1.getId());
        assertThat(stockTransferAuditLog1).isEqualTo(stockTransferAuditLog2);
        stockTransferAuditLog2.setId(2L);
        assertThat(stockTransferAuditLog1).isNotEqualTo(stockTransferAuditLog2);
        stockTransferAuditLog1.setId(null);
        assertThat(stockTransferAuditLog1).isNotEqualTo(stockTransferAuditLog2);
    }
}

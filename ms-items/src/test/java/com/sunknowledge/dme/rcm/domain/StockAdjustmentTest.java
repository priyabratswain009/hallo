package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockAdjustmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockAdjustment.class);
        StockAdjustment stockAdjustment1 = new StockAdjustment();
        stockAdjustment1.setStockAdjustmentId(1L);
        StockAdjustment stockAdjustment2 = new StockAdjustment();
        stockAdjustment2.setStockAdjustmentId(stockAdjustment1.getStockAdjustmentId());
        assertThat(stockAdjustment1).isEqualTo(stockAdjustment2);
        stockAdjustment2.setStockAdjustmentId(2L);
        assertThat(stockAdjustment1).isNotEqualTo(stockAdjustment2);
        stockAdjustment1.setStockAdjustmentId(null);
        assertThat(stockAdjustment1).isNotEqualTo(stockAdjustment2);
    }
}

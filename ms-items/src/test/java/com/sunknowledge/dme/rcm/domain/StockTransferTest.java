package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockTransferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockTransfer.class);
        StockTransfer stockTransfer1 = new StockTransfer();
        stockTransfer1.setStockTransferId(1L);
        StockTransfer stockTransfer2 = new StockTransfer();
        stockTransfer2.setStockTransferId(stockTransfer1.getStockTransferId());
        assertThat(stockTransfer1).isEqualTo(stockTransfer2);
        stockTransfer2.setStockTransferId(2L);
        assertThat(stockTransfer1).isNotEqualTo(stockTransfer2);
        stockTransfer1.setStockTransferId(null);
        assertThat(stockTransfer1).isNotEqualTo(stockTransfer2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockAdjustmentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockAdjustmentDTO.class);
        StockAdjustmentDTO stockAdjustmentDTO1 = new StockAdjustmentDTO();
        stockAdjustmentDTO1.setStockAdjustmentId(1L);
        StockAdjustmentDTO stockAdjustmentDTO2 = new StockAdjustmentDTO();
        assertThat(stockAdjustmentDTO1).isNotEqualTo(stockAdjustmentDTO2);
        stockAdjustmentDTO2.setStockAdjustmentId(stockAdjustmentDTO1.getStockAdjustmentId());
        assertThat(stockAdjustmentDTO1).isEqualTo(stockAdjustmentDTO2);
        stockAdjustmentDTO2.setStockAdjustmentId(2L);
        assertThat(stockAdjustmentDTO1).isNotEqualTo(stockAdjustmentDTO2);
        stockAdjustmentDTO1.setStockAdjustmentId(null);
        assertThat(stockAdjustmentDTO1).isNotEqualTo(stockAdjustmentDTO2);
    }
}

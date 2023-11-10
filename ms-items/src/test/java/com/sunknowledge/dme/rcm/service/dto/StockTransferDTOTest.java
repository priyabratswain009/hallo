package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StockTransferDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockTransferDTO.class);
        StockTransferDTO stockTransferDTO1 = new StockTransferDTO();
        stockTransferDTO1.setStockTransferId(1L);
        StockTransferDTO stockTransferDTO2 = new StockTransferDTO();
        assertThat(stockTransferDTO1).isNotEqualTo(stockTransferDTO2);
        stockTransferDTO2.setStockTransferId(stockTransferDTO1.getStockTransferId());
        assertThat(stockTransferDTO1).isEqualTo(stockTransferDTO2);
        stockTransferDTO2.setStockTransferId(2L);
        assertThat(stockTransferDTO1).isNotEqualTo(stockTransferDTO2);
        stockTransferDTO1.setStockTransferId(null);
        assertThat(stockTransferDTO1).isNotEqualTo(stockTransferDTO2);
    }
}

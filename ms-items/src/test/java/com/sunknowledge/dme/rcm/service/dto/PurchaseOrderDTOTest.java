package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderDTO.class);
        PurchaseOrderDTO purchaseOrderDTO1 = new PurchaseOrderDTO();
        purchaseOrderDTO1.setPoId(1L);
        PurchaseOrderDTO purchaseOrderDTO2 = new PurchaseOrderDTO();
        assertThat(purchaseOrderDTO1).isNotEqualTo(purchaseOrderDTO2);
        purchaseOrderDTO2.setPoId(purchaseOrderDTO1.getPoId());
        assertThat(purchaseOrderDTO1).isEqualTo(purchaseOrderDTO2);
        purchaseOrderDTO2.setPoId(2L);
        assertThat(purchaseOrderDTO1).isNotEqualTo(purchaseOrderDTO2);
        purchaseOrderDTO1.setPoId(null);
        assertThat(purchaseOrderDTO1).isNotEqualTo(purchaseOrderDTO2);
    }
}

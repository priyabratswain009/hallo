package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsDTO.class);
        PurchaseOrderItemsDTO purchaseOrderItemsDTO1 = new PurchaseOrderItemsDTO();
        purchaseOrderItemsDTO1.setPoItemsId(1L);
        PurchaseOrderItemsDTO purchaseOrderItemsDTO2 = new PurchaseOrderItemsDTO();
        assertThat(purchaseOrderItemsDTO1).isNotEqualTo(purchaseOrderItemsDTO2);
        purchaseOrderItemsDTO2.setPoItemsId(purchaseOrderItemsDTO1.getPoItemsId());
        assertThat(purchaseOrderItemsDTO1).isEqualTo(purchaseOrderItemsDTO2);
        purchaseOrderItemsDTO2.setPoItemsId(2L);
        assertThat(purchaseOrderItemsDTO1).isNotEqualTo(purchaseOrderItemsDTO2);
        purchaseOrderItemsDTO1.setPoItemsId(null);
        assertThat(purchaseOrderItemsDTO1).isNotEqualTo(purchaseOrderItemsDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsReceivedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsReceivedDTO.class);
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO1 = new PurchaseOrderItemsReceivedDTO();
        purchaseOrderItemsReceivedDTO1.setPoItemReceivedId(1L);
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO2 = new PurchaseOrderItemsReceivedDTO();
        assertThat(purchaseOrderItemsReceivedDTO1).isNotEqualTo(purchaseOrderItemsReceivedDTO2);
        purchaseOrderItemsReceivedDTO2.setPoItemReceivedId(purchaseOrderItemsReceivedDTO1.getPoItemReceivedId());
        assertThat(purchaseOrderItemsReceivedDTO1).isEqualTo(purchaseOrderItemsReceivedDTO2);
        purchaseOrderItemsReceivedDTO2.setPoItemReceivedId(2L);
        assertThat(purchaseOrderItemsReceivedDTO1).isNotEqualTo(purchaseOrderItemsReceivedDTO2);
        purchaseOrderItemsReceivedDTO1.setPoItemReceivedId(null);
        assertThat(purchaseOrderItemsReceivedDTO1).isNotEqualTo(purchaseOrderItemsReceivedDTO2);
    }
}

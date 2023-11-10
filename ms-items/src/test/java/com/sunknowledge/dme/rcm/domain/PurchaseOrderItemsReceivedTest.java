package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsReceivedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsReceived.class);
        PurchaseOrderItemsReceived purchaseOrderItemsReceived1 = new PurchaseOrderItemsReceived();
        purchaseOrderItemsReceived1.setPoItemReceivedId(1L);
        PurchaseOrderItemsReceived purchaseOrderItemsReceived2 = new PurchaseOrderItemsReceived();
        purchaseOrderItemsReceived2.setPoItemReceivedId(purchaseOrderItemsReceived1.getPoItemReceivedId());
        assertThat(purchaseOrderItemsReceived1).isEqualTo(purchaseOrderItemsReceived2);
        purchaseOrderItemsReceived2.setPoItemReceivedId(2L);
        assertThat(purchaseOrderItemsReceived1).isNotEqualTo(purchaseOrderItemsReceived2);
        purchaseOrderItemsReceived1.setPoItemReceivedId(null);
        assertThat(purchaseOrderItemsReceived1).isNotEqualTo(purchaseOrderItemsReceived2);
    }
}

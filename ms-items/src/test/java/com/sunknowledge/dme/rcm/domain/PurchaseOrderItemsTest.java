package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItems.class);
        PurchaseOrderItems purchaseOrderItems1 = new PurchaseOrderItems();
        purchaseOrderItems1.setPoItemsId(1L);
        PurchaseOrderItems purchaseOrderItems2 = new PurchaseOrderItems();
        purchaseOrderItems2.setPoItemsId(purchaseOrderItems1.getPoItemsId());
        assertThat(purchaseOrderItems1).isEqualTo(purchaseOrderItems2);
        purchaseOrderItems2.setPoItemsId(2L);
        assertThat(purchaseOrderItems1).isNotEqualTo(purchaseOrderItems2);
        purchaseOrderItems1.setPoItemsId(null);
        assertThat(purchaseOrderItems1).isNotEqualTo(purchaseOrderItems2);
    }
}

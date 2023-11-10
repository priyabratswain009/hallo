package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsAuditLog.class);
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog1 = new PurchaseOrderItemsAuditLog();
        purchaseOrderItemsAuditLog1.setId(1L);
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog2 = new PurchaseOrderItemsAuditLog();
        purchaseOrderItemsAuditLog2.setId(purchaseOrderItemsAuditLog1.getId());
        assertThat(purchaseOrderItemsAuditLog1).isEqualTo(purchaseOrderItemsAuditLog2);
        purchaseOrderItemsAuditLog2.setId(2L);
        assertThat(purchaseOrderItemsAuditLog1).isNotEqualTo(purchaseOrderItemsAuditLog2);
        purchaseOrderItemsAuditLog1.setId(null);
        assertThat(purchaseOrderItemsAuditLog1).isNotEqualTo(purchaseOrderItemsAuditLog2);
    }
}

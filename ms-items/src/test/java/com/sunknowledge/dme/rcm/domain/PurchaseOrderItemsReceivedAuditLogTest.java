package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsReceivedAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsReceivedAuditLog.class);
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog1 = new PurchaseOrderItemsReceivedAuditLog();
        purchaseOrderItemsReceivedAuditLog1.setId(1L);
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog2 = new PurchaseOrderItemsReceivedAuditLog();
        purchaseOrderItemsReceivedAuditLog2.setId(purchaseOrderItemsReceivedAuditLog1.getId());
        assertThat(purchaseOrderItemsReceivedAuditLog1).isEqualTo(purchaseOrderItemsReceivedAuditLog2);
        purchaseOrderItemsReceivedAuditLog2.setId(2L);
        assertThat(purchaseOrderItemsReceivedAuditLog1).isNotEqualTo(purchaseOrderItemsReceivedAuditLog2);
        purchaseOrderItemsReceivedAuditLog1.setId(null);
        assertThat(purchaseOrderItemsReceivedAuditLog1).isNotEqualTo(purchaseOrderItemsReceivedAuditLog2);
    }
}

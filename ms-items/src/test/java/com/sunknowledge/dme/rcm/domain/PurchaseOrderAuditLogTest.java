package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderAuditLog.class);
        PurchaseOrderAuditLog purchaseOrderAuditLog1 = new PurchaseOrderAuditLog();
        purchaseOrderAuditLog1.setId(1L);
        PurchaseOrderAuditLog purchaseOrderAuditLog2 = new PurchaseOrderAuditLog();
        purchaseOrderAuditLog2.setId(purchaseOrderAuditLog1.getId());
        assertThat(purchaseOrderAuditLog1).isEqualTo(purchaseOrderAuditLog2);
        purchaseOrderAuditLog2.setId(2L);
        assertThat(purchaseOrderAuditLog1).isNotEqualTo(purchaseOrderAuditLog2);
        purchaseOrderAuditLog1.setId(null);
        assertThat(purchaseOrderAuditLog1).isNotEqualTo(purchaseOrderAuditLog2);
    }
}

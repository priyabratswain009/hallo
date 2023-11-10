package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsAuditLogDTO.class);
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO1 = new PurchaseOrderItemsAuditLogDTO();
        purchaseOrderItemsAuditLogDTO1.setId(1L);
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO2 = new PurchaseOrderItemsAuditLogDTO();
        assertThat(purchaseOrderItemsAuditLogDTO1).isNotEqualTo(purchaseOrderItemsAuditLogDTO2);
        purchaseOrderItemsAuditLogDTO2.setId(purchaseOrderItemsAuditLogDTO1.getId());
        assertThat(purchaseOrderItemsAuditLogDTO1).isEqualTo(purchaseOrderItemsAuditLogDTO2);
        purchaseOrderItemsAuditLogDTO2.setId(2L);
        assertThat(purchaseOrderItemsAuditLogDTO1).isNotEqualTo(purchaseOrderItemsAuditLogDTO2);
        purchaseOrderItemsAuditLogDTO1.setId(null);
        assertThat(purchaseOrderItemsAuditLogDTO1).isNotEqualTo(purchaseOrderItemsAuditLogDTO2);
    }
}

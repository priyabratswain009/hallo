package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderItemsReceivedAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderItemsReceivedAuditLogDTO.class);
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO1 = new PurchaseOrderItemsReceivedAuditLogDTO();
        purchaseOrderItemsReceivedAuditLogDTO1.setId(1L);
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO2 = new PurchaseOrderItemsReceivedAuditLogDTO();
        assertThat(purchaseOrderItemsReceivedAuditLogDTO1).isNotEqualTo(purchaseOrderItemsReceivedAuditLogDTO2);
        purchaseOrderItemsReceivedAuditLogDTO2.setId(purchaseOrderItemsReceivedAuditLogDTO1.getId());
        assertThat(purchaseOrderItemsReceivedAuditLogDTO1).isEqualTo(purchaseOrderItemsReceivedAuditLogDTO2);
        purchaseOrderItemsReceivedAuditLogDTO2.setId(2L);
        assertThat(purchaseOrderItemsReceivedAuditLogDTO1).isNotEqualTo(purchaseOrderItemsReceivedAuditLogDTO2);
        purchaseOrderItemsReceivedAuditLogDTO1.setId(null);
        assertThat(purchaseOrderItemsReceivedAuditLogDTO1).isNotEqualTo(purchaseOrderItemsReceivedAuditLogDTO2);
    }
}

package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseOrderAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderAuditLogDTO.class);
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO1 = new PurchaseOrderAuditLogDTO();
        purchaseOrderAuditLogDTO1.setId(1L);
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO2 = new PurchaseOrderAuditLogDTO();
        assertThat(purchaseOrderAuditLogDTO1).isNotEqualTo(purchaseOrderAuditLogDTO2);
        purchaseOrderAuditLogDTO2.setId(purchaseOrderAuditLogDTO1.getId());
        assertThat(purchaseOrderAuditLogDTO1).isEqualTo(purchaseOrderAuditLogDTO2);
        purchaseOrderAuditLogDTO2.setId(2L);
        assertThat(purchaseOrderAuditLogDTO1).isNotEqualTo(purchaseOrderAuditLogDTO2);
        purchaseOrderAuditLogDTO1.setId(null);
        assertThat(purchaseOrderAuditLogDTO1).isNotEqualTo(purchaseOrderAuditLogDTO2);
    }
}

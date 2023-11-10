package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceLineItemDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceLineItemDetailsAuditLogDTO.class);
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO1 = new InvoiceLineItemDetailsAuditLogDTO();
        invoiceLineItemDetailsAuditLogDTO1.setInvceLinItmDetilId(1L);
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO2 = new InvoiceLineItemDetailsAuditLogDTO();
        assertThat(invoiceLineItemDetailsAuditLogDTO1).isNotEqualTo(invoiceLineItemDetailsAuditLogDTO2);
        invoiceLineItemDetailsAuditLogDTO2.setInvceLinItmDetilId(invoiceLineItemDetailsAuditLogDTO1.getInvceLinItmDetilId());
        assertThat(invoiceLineItemDetailsAuditLogDTO1).isEqualTo(invoiceLineItemDetailsAuditLogDTO2);
        invoiceLineItemDetailsAuditLogDTO2.setInvceLinItmDetilId(2L);
        assertThat(invoiceLineItemDetailsAuditLogDTO1).isNotEqualTo(invoiceLineItemDetailsAuditLogDTO2);
        invoiceLineItemDetailsAuditLogDTO1.setInvceLinItmDetilId(null);
        assertThat(invoiceLineItemDetailsAuditLogDTO1).isNotEqualTo(invoiceLineItemDetailsAuditLogDTO2);
    }
}

package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceLineItemDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceLineItemDetailsAuditLog.class);
        InvoiceLineItemDetailsAuditLog invoiceLineItemDetailsAuditLog1 = new InvoiceLineItemDetailsAuditLog();
        invoiceLineItemDetailsAuditLog1.setInvceLinItmDetilId(1L);
        InvoiceLineItemDetailsAuditLog invoiceLineItemDetailsAuditLog2 = new InvoiceLineItemDetailsAuditLog();
        invoiceLineItemDetailsAuditLog2.setInvceLinItmDetilId(invoiceLineItemDetailsAuditLog1.getInvceLinItmDetilId());
        assertThat(invoiceLineItemDetailsAuditLog1).isEqualTo(invoiceLineItemDetailsAuditLog2);
        invoiceLineItemDetailsAuditLog2.setInvceLinItmDetilId(2L);
        assertThat(invoiceLineItemDetailsAuditLog1).isNotEqualTo(invoiceLineItemDetailsAuditLog2);
        invoiceLineItemDetailsAuditLog1.setInvceLinItmDetilId(null);
        assertThat(invoiceLineItemDetailsAuditLog1).isNotEqualTo(invoiceLineItemDetailsAuditLog2);
    }
}

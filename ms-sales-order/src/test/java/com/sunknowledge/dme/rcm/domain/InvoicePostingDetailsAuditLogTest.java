package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePostingDetailsAuditLog.class);
        InvoicePostingDetailsAuditLog invoicePostingDetailsAuditLog1 = new InvoicePostingDetailsAuditLog();
        invoicePostingDetailsAuditLog1.setInvLineItmPstingId(1L);
        InvoicePostingDetailsAuditLog invoicePostingDetailsAuditLog2 = new InvoicePostingDetailsAuditLog();
        invoicePostingDetailsAuditLog2.setInvLineItmPstingId(invoicePostingDetailsAuditLog1.getInvLineItmPstingId());
        assertThat(invoicePostingDetailsAuditLog1).isEqualTo(invoicePostingDetailsAuditLog2);
        invoicePostingDetailsAuditLog2.setInvLineItmPstingId(2L);
        assertThat(invoicePostingDetailsAuditLog1).isNotEqualTo(invoicePostingDetailsAuditLog2);
        invoicePostingDetailsAuditLog1.setInvLineItmPstingId(null);
        assertThat(invoicePostingDetailsAuditLog1).isNotEqualTo(invoicePostingDetailsAuditLog2);
    }
}

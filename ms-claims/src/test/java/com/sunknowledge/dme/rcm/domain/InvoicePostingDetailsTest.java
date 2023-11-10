package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePostingDetails.class);
        InvoicePostingDetails invoicePostingDetails1 = new InvoicePostingDetails();
        invoicePostingDetails1.setInvoiceLineItemPostingId(1L);
        InvoicePostingDetails invoicePostingDetails2 = new InvoicePostingDetails();
        invoicePostingDetails2.setInvoiceLineItemPostingId(invoicePostingDetails1.getInvoiceLineItemPostingId());
        assertThat(invoicePostingDetails1).isEqualTo(invoicePostingDetails2);
        invoicePostingDetails2.setInvoiceLineItemPostingId(2L);
        assertThat(invoicePostingDetails1).isNotEqualTo(invoicePostingDetails2);
        invoicePostingDetails1.setInvoiceLineItemPostingId(null);
        assertThat(invoicePostingDetails1).isNotEqualTo(invoicePostingDetails2);
    }
}

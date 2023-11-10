package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceLineItemDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceLineItemDetails.class);
        InvoiceLineItemDetails invoiceLineItemDetails1 = new InvoiceLineItemDetails();
        invoiceLineItemDetails1.setInvoiceLineItemDetailsId(1L);
        InvoiceLineItemDetails invoiceLineItemDetails2 = new InvoiceLineItemDetails();
        invoiceLineItemDetails2.setInvoiceLineItemDetailsId(invoiceLineItemDetails1.getInvoiceLineItemDetailsId());
        assertThat(invoiceLineItemDetails1).isEqualTo(invoiceLineItemDetails2);
        invoiceLineItemDetails2.setInvoiceLineItemDetailsId(2L);
        assertThat(invoiceLineItemDetails1).isNotEqualTo(invoiceLineItemDetails2);
        invoiceLineItemDetails1.setInvoiceLineItemDetailsId(null);
        assertThat(invoiceLineItemDetails1).isNotEqualTo(invoiceLineItemDetails2);
    }
}

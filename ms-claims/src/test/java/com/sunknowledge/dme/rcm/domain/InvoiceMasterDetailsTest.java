package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceMasterDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceMasterDetails.class);
        InvoiceMasterDetails invoiceMasterDetails1 = new InvoiceMasterDetails();
        invoiceMasterDetails1.setInvoiceId(1L);
        InvoiceMasterDetails invoiceMasterDetails2 = new InvoiceMasterDetails();
        invoiceMasterDetails2.setInvoiceId(invoiceMasterDetails1.getInvoiceId());
        assertThat(invoiceMasterDetails1).isEqualTo(invoiceMasterDetails2);
        invoiceMasterDetails2.setInvoiceId(2L);
        assertThat(invoiceMasterDetails1).isNotEqualTo(invoiceMasterDetails2);
        invoiceMasterDetails1.setInvoiceId(null);
        assertThat(invoiceMasterDetails1).isNotEqualTo(invoiceMasterDetails2);
    }
}

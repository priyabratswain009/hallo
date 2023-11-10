package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceLineItemDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceLineItemDetailsDTO.class);
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO1 = new InvoiceLineItemDetailsDTO();
        invoiceLineItemDetailsDTO1.setInvoiceLineItemDetailsId(1L);
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO2 = new InvoiceLineItemDetailsDTO();
        assertThat(invoiceLineItemDetailsDTO1).isNotEqualTo(invoiceLineItemDetailsDTO2);
        invoiceLineItemDetailsDTO2.setInvoiceLineItemDetailsId(invoiceLineItemDetailsDTO1.getInvoiceLineItemDetailsId());
        assertThat(invoiceLineItemDetailsDTO1).isEqualTo(invoiceLineItemDetailsDTO2);
        invoiceLineItemDetailsDTO2.setInvoiceLineItemDetailsId(2L);
        assertThat(invoiceLineItemDetailsDTO1).isNotEqualTo(invoiceLineItemDetailsDTO2);
        invoiceLineItemDetailsDTO1.setInvoiceLineItemDetailsId(null);
        assertThat(invoiceLineItemDetailsDTO1).isNotEqualTo(invoiceLineItemDetailsDTO2);
    }
}

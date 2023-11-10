package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePostingDetailsDTO.class);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO1 = new InvoicePostingDetailsDTO();
        invoicePostingDetailsDTO1.setInvoiceLineItemPostingId(1L);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO2 = new InvoicePostingDetailsDTO();
        assertThat(invoicePostingDetailsDTO1).isNotEqualTo(invoicePostingDetailsDTO2);
        invoicePostingDetailsDTO2.setInvoiceLineItemPostingId(invoicePostingDetailsDTO1.getInvoiceLineItemPostingId());
        assertThat(invoicePostingDetailsDTO1).isEqualTo(invoicePostingDetailsDTO2);
        invoicePostingDetailsDTO2.setInvoiceLineItemPostingId(2L);
        assertThat(invoicePostingDetailsDTO1).isNotEqualTo(invoicePostingDetailsDTO2);
        invoicePostingDetailsDTO1.setInvoiceLineItemPostingId(null);
        assertThat(invoicePostingDetailsDTO1).isNotEqualTo(invoicePostingDetailsDTO2);
    }
}

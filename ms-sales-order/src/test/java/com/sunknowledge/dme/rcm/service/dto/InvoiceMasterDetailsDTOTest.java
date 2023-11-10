package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceMasterDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceMasterDetailsDTO.class);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO1 = new InvoiceMasterDetailsDTO();
        invoiceMasterDetailsDTO1.setInvoiceId(1L);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO2 = new InvoiceMasterDetailsDTO();
        assertThat(invoiceMasterDetailsDTO1).isNotEqualTo(invoiceMasterDetailsDTO2);
        invoiceMasterDetailsDTO2.setInvoiceId(invoiceMasterDetailsDTO1.getInvoiceId());
        assertThat(invoiceMasterDetailsDTO1).isEqualTo(invoiceMasterDetailsDTO2);
        invoiceMasterDetailsDTO2.setInvoiceId(2L);
        assertThat(invoiceMasterDetailsDTO1).isNotEqualTo(invoiceMasterDetailsDTO2);
        invoiceMasterDetailsDTO1.setInvoiceId(null);
        assertThat(invoiceMasterDetailsDTO1).isNotEqualTo(invoiceMasterDetailsDTO2);
    }
}

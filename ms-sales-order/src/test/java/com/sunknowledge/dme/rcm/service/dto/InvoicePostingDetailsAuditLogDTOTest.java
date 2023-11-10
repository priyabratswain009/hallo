package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoicePostingDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePostingDetailsAuditLogDTO.class);
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO1 = new InvoicePostingDetailsAuditLogDTO();
        invoicePostingDetailsAuditLogDTO1.setInvLineItmPstingId(1L);
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO2 = new InvoicePostingDetailsAuditLogDTO();
        assertThat(invoicePostingDetailsAuditLogDTO1).isNotEqualTo(invoicePostingDetailsAuditLogDTO2);
        invoicePostingDetailsAuditLogDTO2.setInvLineItmPstingId(invoicePostingDetailsAuditLogDTO1.getInvLineItmPstingId());
        assertThat(invoicePostingDetailsAuditLogDTO1).isEqualTo(invoicePostingDetailsAuditLogDTO2);
        invoicePostingDetailsAuditLogDTO2.setInvLineItmPstingId(2L);
        assertThat(invoicePostingDetailsAuditLogDTO1).isNotEqualTo(invoicePostingDetailsAuditLogDTO2);
        invoicePostingDetailsAuditLogDTO1.setInvLineItmPstingId(null);
        assertThat(invoicePostingDetailsAuditLogDTO1).isNotEqualTo(invoicePostingDetailsAuditLogDTO2);
    }
}

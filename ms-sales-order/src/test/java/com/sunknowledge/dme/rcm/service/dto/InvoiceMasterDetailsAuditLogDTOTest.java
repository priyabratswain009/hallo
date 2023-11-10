package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceMasterDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceMasterDetailsAuditLogDTO.class);
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO1 = new InvoiceMasterDetailsAuditLogDTO();
        invoiceMasterDetailsAuditLogDTO1.setInvceId(1L);
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO2 = new InvoiceMasterDetailsAuditLogDTO();
        assertThat(invoiceMasterDetailsAuditLogDTO1).isNotEqualTo(invoiceMasterDetailsAuditLogDTO2);
        invoiceMasterDetailsAuditLogDTO2.setInvceId(invoiceMasterDetailsAuditLogDTO1.getInvceId());
        assertThat(invoiceMasterDetailsAuditLogDTO1).isEqualTo(invoiceMasterDetailsAuditLogDTO2);
        invoiceMasterDetailsAuditLogDTO2.setInvceId(2L);
        assertThat(invoiceMasterDetailsAuditLogDTO1).isNotEqualTo(invoiceMasterDetailsAuditLogDTO2);
        invoiceMasterDetailsAuditLogDTO1.setInvceId(null);
        assertThat(invoiceMasterDetailsAuditLogDTO1).isNotEqualTo(invoiceMasterDetailsAuditLogDTO2);
    }
}

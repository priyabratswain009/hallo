package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceMasterDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceMasterDetailsAuditLog.class);
        InvoiceMasterDetailsAuditLog invoiceMasterDetailsAuditLog1 = new InvoiceMasterDetailsAuditLog();
        invoiceMasterDetailsAuditLog1.setInvceId(1L);
        InvoiceMasterDetailsAuditLog invoiceMasterDetailsAuditLog2 = new InvoiceMasterDetailsAuditLog();
        invoiceMasterDetailsAuditLog2.setInvceId(invoiceMasterDetailsAuditLog1.getInvceId());
        assertThat(invoiceMasterDetailsAuditLog1).isEqualTo(invoiceMasterDetailsAuditLog2);
        invoiceMasterDetailsAuditLog2.setInvceId(2L);
        assertThat(invoiceMasterDetailsAuditLog1).isNotEqualTo(invoiceMasterDetailsAuditLog2);
        invoiceMasterDetailsAuditLog1.setInvceId(null);
        assertThat(invoiceMasterDetailsAuditLog1).isNotEqualTo(invoiceMasterDetailsAuditLog2);
    }
}

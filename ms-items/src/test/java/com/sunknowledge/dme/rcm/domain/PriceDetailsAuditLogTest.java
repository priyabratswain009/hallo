package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetailsAuditLog.class);
        PriceDetailsAuditLog priceDetailsAuditLog1 = new PriceDetailsAuditLog();
        priceDetailsAuditLog1.setId(1L);
        PriceDetailsAuditLog priceDetailsAuditLog2 = new PriceDetailsAuditLog();
        priceDetailsAuditLog2.setId(priceDetailsAuditLog1.getId());
        assertThat(priceDetailsAuditLog1).isEqualTo(priceDetailsAuditLog2);
        priceDetailsAuditLog2.setId(2L);
        assertThat(priceDetailsAuditLog1).isNotEqualTo(priceDetailsAuditLog2);
        priceDetailsAuditLog1.setId(null);
        assertThat(priceDetailsAuditLog1).isNotEqualTo(priceDetailsAuditLog2);
    }
}

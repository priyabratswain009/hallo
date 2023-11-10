package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxZoneAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxZoneAuditLog.class);
        TaxZoneAuditLog taxZoneAuditLog1 = new TaxZoneAuditLog();
        taxZoneAuditLog1.setId(1L);
        TaxZoneAuditLog taxZoneAuditLog2 = new TaxZoneAuditLog();
        taxZoneAuditLog2.setId(taxZoneAuditLog1.getId());
        assertThat(taxZoneAuditLog1).isEqualTo(taxZoneAuditLog2);
        taxZoneAuditLog2.setId(2L);
        assertThat(taxZoneAuditLog1).isNotEqualTo(taxZoneAuditLog2);
        taxZoneAuditLog1.setId(null);
        assertThat(taxZoneAuditLog1).isNotEqualTo(taxZoneAuditLog2);
    }
}
